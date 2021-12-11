package dz.rest.dto.interfaces;

import dz.rest.models.Employee;

import java.util.List;

public interface EmployeeDTO {
    List<Employee> index();

    Employee show(long id);

    void save(Employee employee);

    void update(long id, Employee updateEmployee);

    void delete(long id);
}
