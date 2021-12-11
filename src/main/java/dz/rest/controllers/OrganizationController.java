package dz.rest.controllers;

import dz.rest.DTO.OrganizationDTO;
import dz.rest.service.interfaces.OrganizationService;
import dz.rest.models.Organization;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/organizations")
public class OrganizationController {

    private final OrganizationService organizationService;

    public OrganizationController(@Qualifier("organizationServiceHibernate") OrganizationService organizationService) {
        this.organizationService = organizationService;
    }

    @GetMapping()
    public List<OrganizationDTO> index() {
        return organizationService.index();
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") long id) {
        return organizationService.show(id).toString();
    }

    @PostMapping()
    public void create(@Valid @RequestBody OrganizationDTO request) {
        organizationService.save(request);
    }

    @PutMapping("/{id}")
    public void update(@PathVariable("id") long id,
                       @Valid @RequestBody OrganizationDTO request) {
        organizationService.update(id, request);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") long id) {
        organizationService.delete(id);
    }
}
