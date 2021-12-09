package dz.rest.dto.forList;

import dz.rest.dto.interfaces.OrganizationDTO;
import dz.rest.models.Employee;
import dz.rest.models.Organization;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class OrganizationDTOList implements OrganizationDTO {

    private List<Organization> organizations;
    private static int ORGANIZATION_COUNT;

    {
        organizations = new ArrayList<>();
        organizations.add(new Organization(++ORGANIZATION_COUNT, "Apple", "apple.com"));
        organizations.add(new Organization(++ORGANIZATION_COUNT, "Intel", "intel.com"));
        organizations.add(new Organization(++ORGANIZATION_COUNT, "Qualcom", "qualcom.com"));
        organizations.add(new Organization(++ORGANIZATION_COUNT, "Zoom", "zoom.com"));
    }

    @Override
    public List<Organization> index() {
        return organizations;
    }

    @Override
    public Organization show(int id) {
        return organizations.stream()
                .filter(x -> x.getId() == id)
                .findAny()
                .orElse(null);
    }

    @Override
    public void save(Organization newOrganization) {
        newOrganization.setId(++ORGANIZATION_COUNT);
        organizations.add(newOrganization);
    }

    @Override
    public void update(int id, Organization updateOrganization) {
        Organization organizationToBeUpdated = show(id);
        organizationToBeUpdated.setName(updateOrganization.getName());
        organizationToBeUpdated.setUrl((updateOrganization.getUrl()));
    }

    @Override
    public void delete(int id) {
        organizations.removeIf(x -> x.getId() == id);
    }
}
