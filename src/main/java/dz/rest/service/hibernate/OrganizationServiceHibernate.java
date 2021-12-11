package dz.rest.service.hibernate;

import dz.rest.DTO.EmployeeDTO;
import dz.rest.DTO.OrganizationDTO;
import dz.rest.models.Employee;
import dz.rest.models.Organization;
import dz.rest.repository.EmployeeRepository;
import dz.rest.repository.OrganizationRepository;
import dz.rest.service.interfaces.OrganizationService;
import org.aspectj.weaver.ast.Or;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class OrganizationServiceHibernate implements OrganizationService {

    private final OrganizationRepository organizationRepository;
    private final ModelMapper modelMapper;

    public OrganizationServiceHibernate(OrganizationRepository organizationRepository, ModelMapper modelMapper) {
        this.organizationRepository = organizationRepository;
        this.modelMapper = modelMapper;
    }

    //Сщздаем конвертер для приведения entity k DTO
    private OrganizationDTO convertToOrganizationDTO(Organization organization) {
        return modelMapper.map(organization, OrganizationDTO.class);
    }

    //Сщздаем конвертер для приведения DTO k entity
    private Organization convertToOrganization(OrganizationDTO organizationDTO) {
        return modelMapper.map(organizationDTO, Organization.class);
    }

    @Override
    public List<OrganizationDTO> index() {
        List<Organization> organizations =  organizationRepository.findAll();
        List<OrganizationDTO> organizationDTOList = new ArrayList<>();
        return organizations.stream().map(this::convertToOrganizationDTO).collect(Collectors.toList());
//        for (Organization organization : organizations) {
//            convertToOrganizationDTO(organization);
//        }
//        return organizationDTOList;
    }

    @Override
    public OrganizationDTO show(long id) {
        Organization organization = getOrganizationById(id);
        return convertToOrganizationDTO(organization);
    }

    private Organization getOrganizationById(long id){
        return organizationRepository.findById(id)
                .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @Override
    public void save(OrganizationDTO organizationDTO) {
        organizationRepository.save(convertToOrganization(organizationDTO));
    }

    @Override
    public void update(long id, OrganizationDTO organizationDTO) {
        Organization organization = convertToOrganization(organizationDTO);
        organization.setId(id);//????????????????????????????????????????????????????????????????????????????
        organizationRepository.save(organization);
    }

    @Override
    public void delete(long id) {
        organizationRepository.deleteById(id);
    }
}
