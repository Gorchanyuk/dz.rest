package dz.rest.controllers;

import dz.rest.DTO.EmployeeDTO;
import dz.rest.service.interfaces.EmployeeService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(@Qualifier("employeeServiceHibernate") EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping()
    public List<EmployeeDTO> index() {
        return employeeService.index();
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") long id) {
        return employeeService.show(id).toString();
    }

    @PostMapping()
    public void create(@Valid @RequestBody EmployeeDTO request) {
        employeeService.save(request);
    }

    @PutMapping("/{id}")
    public void update(@PathVariable("id") long id,
                       @Valid @RequestBody EmployeeDTO request) {
        employeeService.update(id, request);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") long id) {
        employeeService.delete(id);
    }
}
