package dz.rest.dto.interfaces;

import dz.rest.models.Organization;

import java.util.List;

public interface OrganizationDTO {
    List<Organization> index();

    Organization show(int id);

    void save(Organization organization);

    void update(int id, Organization updateOrganization);

    void delete(int id);
}
