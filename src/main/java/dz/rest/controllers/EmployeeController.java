package dz.rest.controllers;

import dz.rest.dto.forList.EmployeeDTOList;
import dz.rest.dto.interfaces.EmployeeDTO;
import dz.rest.models.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    private final EmployeeDTO employeeDTO;

    public EmployeeController(@Qualifier("employeeHibernate") EmployeeDTO employeeDTO) {
        this.employeeDTO = employeeDTO;
    }

    @GetMapping()
    public List<Employee> index(){
        return employeeDTO.index();
    }

    @GetMapping("/{id}")
    public String show (@PathVariable("id") long id){
        return employeeDTO.show(id).toString();
    }

    @PostMapping()
    public void create(@RequestBody Employee request){
        employeeDTO.save(request);
    }

    @PutMapping("/{id}")
    public void update (@PathVariable("id") long id,
                        @RequestBody Employee request){
        employeeDTO.update(id, request);
    }

    @DeleteMapping("/{id}")
    public void delete (@PathVariable("id") long id){
        employeeDTO.delete(id);
    }
}
