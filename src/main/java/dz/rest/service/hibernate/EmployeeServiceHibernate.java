package dz.rest.service.hibernate;

import dz.rest.DTO.EmployeeDTO;
import dz.rest.service.interfaces.EmployeeService;
import dz.rest.repository.EmployeeRepository;
import dz.rest.models.Employee;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Component
public class EmployeeServiceHibernate implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeServiceHibernate(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public List<EmployeeDTO> index (){
        List<Employee> employees =  employeeRepository.findAll();
        List<EmployeeDTO> employeeDTOList = new ArrayList<>();
        for(Employee employee : employees){
            employeeDTOList.add(employee.getEmployeeDTOByEmployee());
        }
        return employeeDTOList;

    }

    @Override
    public EmployeeDTO show(long id) {
        EmployeeDTO employeeDTO = new EmployeeDTO();
        Employee employee = getEmployeeById(id);
        if(employee != null){
            employeeDTO = employee.getEmployeeDTOByEmployee();
        }
        return employeeDTO;
    }

    private Employee getEmployeeById(long id){
        return employeeRepository.findById(id)
                .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @Override
    public void save(EmployeeDTO employeeDTO) {
        Employee employee = new Employee();
        employee.setEmployeeByEmployeeDTO(employeeDTO);
        employeeRepository.save(employee);
    }

    @Override
    public void update(long id, EmployeeDTO employeeDTO) {
        Employee employee = getEmployeeById(id);
        employee.setEmployeeByEmployeeDTO(employeeDTO);
        employeeRepository.save(employee);
    }

    @Override
    public void delete(long id) {
        employeeRepository.deleteById(id);
    }

}
