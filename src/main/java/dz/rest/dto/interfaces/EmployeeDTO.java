package dz.rest.dto.interfaces;

import dz.rest.models.Employee;

import java.util.List;

public interface EmployeeDTO {
    List<Employee> index();

    Employee show(int id);

    void save(Employee employee);

    void update(int id, Employee updateEmployee);

    void delete(int id);
}
