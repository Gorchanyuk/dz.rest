package dz.rest.service.forJDBC;

import dz.rest.DTO.EmployeeDTO;
import dz.rest.DTO.OrganizationDTO;
import dz.rest.models.Employee;
import dz.rest.service.interfaces.OrganizationService;
import dz.rest.models.Organization;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class OrganizationServiceJdbc implements OrganizationService {

    private final ModelMapper modelMapper;
    private final JdbcTemplate jdbcTemplate;

    public OrganizationServiceJdbc(JdbcTemplate jdbcTemplate, ModelMapper modelMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.modelMapper = modelMapper;
    }
    //Подключаем маппер для приведения entity k DTO
    private OrganizationDTO convertToOrganizationDTO(Organization organization) {
        return modelMapper.map(organization, OrganizationDTO.class);
    }

    @Override
    public List<OrganizationDTO> index() {
        List<OrganizationDTO> organizationDTOList = new ArrayList<>();
        List<Organization> organizations = jdbcTemplate.query("SELECT * FROM Organization", new BeanPropertyRowMapper<>(Organization.class));
        for (Organization organization : organizations) {
            organizationDTOList.add(convertToOrganizationDTO(organization));
        }
        return organizationDTOList;
    }

    @Override
    public OrganizationDTO show(long id) {
        Organization organization = jdbcTemplate.query("SELECT * FROM Organization WHERE id=?",
                new BeanPropertyRowMapper<>(Organization.class),
                id).stream().findAny().orElse(null);
        return convertToOrganizationDTO(organization);
    }

    @Override
    public void save(OrganizationDTO organization) {
        jdbcTemplate.update("INSERT INTO Organization(name, url) VALUES(?, ?)",
                organization.getName(), organization.getUrl());
    }

    @Override
    public void update(long id, OrganizationDTO organizationDTO) {
        jdbcTemplate.update("UPDATE Organization SET name=?, url=? WHERE id=?",
                organizationDTO.getName(),
                organizationDTO.getUrl(),
                id);
    }

    @Override
    public void delete(long id) {
        jdbcTemplate.update("DELETE FROM Organization WHERE id=?", id);
    }
}
