package dz.rest.service.interfaces;

import dz.rest.DTO.OrganizationDTO;

import java.util.List;

public interface OrganizationService {
    List<OrganizationDTO> index();

    OrganizationDTO show(long id);

    void save(OrganizationDTO organizationDTO);

    void update(long id, OrganizationDTO organizationDTO);

    void delete(long id);
}
