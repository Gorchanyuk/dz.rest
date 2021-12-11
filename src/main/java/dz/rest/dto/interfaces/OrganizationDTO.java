package dz.rest.dto.interfaces;

import dz.rest.models.Organization;

import java.util.List;

public interface OrganizationDTO {
    List<Organization> index();

    Organization show(long id);

    void save(Organization organization);

    void update(long id, Organization updateOrganization);

    void delete(long id);
}
