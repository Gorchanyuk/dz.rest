package dz.rest.service.interfaces;

import dz.rest.DTO.EmployeeDTO;
import dz.rest.models.Employee;

import java.util.List;

public interface EmployeeService {
    List<EmployeeDTO> index();

    EmployeeDTO show(long id);

    void save(EmployeeDTO employeeDTO);

    void update(long id, EmployeeDTO employeeDTO);

    void delete(long id);
}
