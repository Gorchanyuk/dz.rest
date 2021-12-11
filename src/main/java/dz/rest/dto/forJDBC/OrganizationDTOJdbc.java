package dz.rest.dto.forJDBC;

import dz.rest.dto.interfaces.OrganizationDTO;
import dz.rest.models.Organization;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class OrganizationDTOJdbc implements OrganizationDTO {

    private final JdbcTemplate jdbcTemplate;

    public OrganizationDTOJdbc(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Organization> index() {
        return jdbcTemplate.query("SELECT * FROM Organization", new BeanPropertyRowMapper<>(Organization.class));
    }

    @Override
    public Organization show(long id) {
        return jdbcTemplate.query("SELECT * FROM Organization WHERE id=?",
                new BeanPropertyRowMapper<>(Organization.class),
                id).stream().findAny().orElse(null);
    }

    @Override
    public void save(Organization organization) {
        jdbcTemplate.update("INSERT INTO Organization(name, url) VALUES(?, ?)",
                organization.getName(), organization.getUrl());
    }

    @Override
    public void update(long id, Organization updateOrganization) {
        jdbcTemplate.update("UPDATE Organization SET name=?, url=? WHERE id=?",
                updateOrganization.getName(),
                updateOrganization.getUrl(),
                id);
    }

    @Override
    public void delete(long id) {
        jdbcTemplate.update("DELETE FROM Organization WHERE id=?", id);
    }
}
