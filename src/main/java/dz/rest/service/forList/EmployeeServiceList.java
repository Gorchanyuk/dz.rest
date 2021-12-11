package dz.rest.service.forList;

import dz.rest.DTO.EmployeeDTO;
import dz.rest.service.interfaces.EmployeeService;
import dz.rest.models.Employee;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class EmployeeServiceList implements EmployeeService {

    private List<Employee> employees;
    private static long EMPLOYEE_COUNT;

    {
        employees = new ArrayList<>();
        employees.add(new Employee(++EMPLOYEE_COUNT, "Tom", "Klinton", 24, "89093453456"));
        employees.add(new Employee(++EMPLOYEE_COUNT, "Mike", "Ruzvelt", 45, "89956784567"));
        employees.add(new Employee(++EMPLOYEE_COUNT, "Nial", "Tramp", 34, "89993456789"));
        employees.add(new Employee(++EMPLOYEE_COUNT, "Bob", "Obama", 37, "89377890909"));
    }

    @Override
    public List<EmployeeDTO> index() {
        List<EmployeeDTO> employeeDTOList = new ArrayList<>();
        for(Employee employee : employees){
            employeeDTOList.add(employee.getEmployeeDTOByEmployee());
        }
        return employeeDTOList;
    }

    @Override
    public EmployeeDTO show(long id) {
        EmployeeDTO employeeDTO = new EmployeeDTO();
        Employee employee =  employees.stream()
                .filter(x -> x.getId() == id)
                .findAny()
                .orElse(null);
        if(employee != null){
            employeeDTO = employee.getEmployeeDTOByEmployee();
        }
        return employeeDTO;
    }

    @Override
    public void save(EmployeeDTO employeeDTO) {
        Employee employee = new Employee();
        employee.setEmployeeByEmployeeDTO(employeeDTO);
        employee.setId(++EMPLOYEE_COUNT);
        employees.add(employee);
    }

    @Override
    public void update(long id, EmployeeDTO employeeDTO) {
        Employee employee = employees.stream()
                .filter(x -> x.getId() == id)
                .findAny()
                .orElse(null);
        if (employee != null) {
            employee.setName(employeeDTO.getName());
            employee.setSurname(employeeDTO.getSurname());
            employee.setAge(employeeDTO.getAge());
            employee.setPhoneNumber(employeeDTO.getPhoneNumber());
        }

    }

    @Override
    public void delete(long id) {
        employees.removeIf(x -> x.getId() == id);
    }
}
