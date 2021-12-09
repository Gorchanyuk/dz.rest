package dz.rest.dto.forList;

import dz.rest.dto.interfaces.EmployeeDTO;
import dz.rest.models.Employee;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class EmployeeDTOList implements EmployeeDTO {

    private List<Employee> employees;
    private static int EMPLOYEE_COUNT;

    {
        employees = new ArrayList<>();
        employees.add(new Employee(++EMPLOYEE_COUNT, "Tom", "Klinton", 24, "89093453456"));
        employees.add(new Employee(++EMPLOYEE_COUNT, "Mike", "Ruzvelt", 45, "89956784567"));
        employees.add(new Employee(++EMPLOYEE_COUNT, "Nial", "Tramp", 34, "89993456789"));
        employees.add(new Employee(++EMPLOYEE_COUNT, "Bob", "Obama", 37, "89377890909"));
    }

    @Override
    public List<Employee> index() {
        return employees;
    }

    @Override
    public Employee show(int id) {
        return employees.stream()
                .filter(x -> x.getId() == id)
                .findAny()
                .orElse(null);
    }

    @Override
    public void save(Employee newEmployee) {
        newEmployee.setId(++EMPLOYEE_COUNT);
        employees.add(newEmployee);
    }

    @Override
    public void update(int id, Employee updateEmployee) {
        Employee employeeToBeUpdated = show(id);
        employeeToBeUpdated.setName(updateEmployee.getName());
        employeeToBeUpdated.setSurname(updateEmployee.getSurname());
        employeeToBeUpdated.setAge(updateEmployee.getAge());
        employeeToBeUpdated.setPhoneNumber(updateEmployee.getPhoneNumber());
    }

    @Override
    public void delete(int id) {
        employees.removeIf(x -> x.getId() == id);
    }
}
