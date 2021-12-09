package dz.rest.controllers;

import dz.rest.dto.interfaces.EmployeeDTO;
import dz.rest.dto.interfaces.OrganizationDTO;
import dz.rest.models.Employee;
import dz.rest.models.Organization;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/organizations")
public class OrganizationController {

    private final OrganizationDTO organizationDTO;

    public OrganizationController(@Qualifier("organizationDTOJdbc") OrganizationDTO organizationDTO) {
        this.organizationDTO = organizationDTO;
    }

    @GetMapping()
    public List<Organization> index() {
        return organizationDTO.index();
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id) {
        return organizationDTO.show(id).toString();
    }

    @PostMapping()
    public void create(@RequestBody Organization request) {
        organizationDTO.save(request);
    }

    @PutMapping("/{id}")
    public void update(@PathVariable("id") int id,
                       @RequestBody Organization request) {
        organizationDTO.update(id, request);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") int id) {
        organizationDTO.delete(id);
    }
}
