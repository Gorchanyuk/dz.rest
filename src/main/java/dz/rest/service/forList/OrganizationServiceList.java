package dz.rest.service.forList;

import dz.rest.DTO.OrganizationDTO;
import dz.rest.service.interfaces.OrganizationService;
import dz.rest.models.Organization;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class OrganizationServiceList implements OrganizationService {

    @Autowired
    private ModelMapper modelMapper;
    private List<Organization> organizations;
    private static long ORGANIZATION_COUNT;

    {
        organizations = new ArrayList<>();
        organizations.add(new Organization(++ORGANIZATION_COUNT, "Apple", "apple.com"));
        organizations.add(new Organization(++ORGANIZATION_COUNT, "Intel", "intel.com"));
        organizations.add(new Organization(++ORGANIZATION_COUNT, "Qualcom", "qualcom.com"));
        organizations.add(new Organization(++ORGANIZATION_COUNT, "Zoom", "zoom.com"));
    }

    //Подключаем маппер для приведения entity k DTO
    private OrganizationDTO convertToOrganizationDTO(Organization organization) {
        return modelMapper.map(organization, OrganizationDTO.class);
    }

    @Override
    public List<OrganizationDTO> index() {
        List<OrganizationDTO> organizationDTOList = new ArrayList<>();
        for (Organization organization : organizations) {
            organizationDTOList.add(convertToOrganizationDTO(organization));
        }
        return organizationDTOList;
    }

    @Override
    public OrganizationDTO show(long id) {
        Organization organization = getOrganization(id);
        return convertToOrganizationDTO(organization);
    }

    private Organization getOrganization(long id){
        return organizations.stream()
                .filter(x -> x.getId() == id)
                .findAny()
                .orElse(null);
    }

    @Override
    public void save(OrganizationDTO organizationDTO) {
        Organization organization = new Organization();
        organization.setId(++ORGANIZATION_COUNT);
        organization.setName(organizationDTO.getName());
        organization.setUrl(organizationDTO.getUrl());
        organizations.add(organization);
    }

    @Override
    public void update(long id, OrganizationDTO organizationDTO) {
        Organization organization = getOrganization(id);
        organization.setName(organizationDTO.getName());
        organization.setUrl((organizationDTO.getUrl()));
    }

    @Override
    public void delete(long id) {
        organizations.removeIf(x -> x.getId() == id);
    }
}
