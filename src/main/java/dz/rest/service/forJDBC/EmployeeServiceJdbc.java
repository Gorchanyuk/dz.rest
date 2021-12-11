package dz.rest.service.forJDBC;

import dz.rest.DTO.EmployeeDTO;
import dz.rest.service.interfaces.EmployeeService;
import dz.rest.models.Employee;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;


import java.util.ArrayList;
import java.util.List;

@Component
public class EmployeeServiceJdbc implements EmployeeService {

    private final JdbcTemplate jdbcTemplate;

    public EmployeeServiceJdbc(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<EmployeeDTO> index() {
        List<Employee> employees = jdbcTemplate.query("SELECT * FROM Employee", new BeanPropertyRowMapper<>(Employee.class));
        List<EmployeeDTO> employeeDTOList = new ArrayList<>();
        for(Employee employee : employees){
            employeeDTOList.add(employee.getEmployeeDTOByEmployee());
        }
        return employeeDTOList;
    }

    @Override
    public EmployeeDTO show(long id) {
        EmployeeDTO employeeDTO = new EmployeeDTO();
        Employee employee = jdbcTemplate.query("SELECT * FROM Employee WHERE id=?",
                new BeanPropertyRowMapper<>(Employee.class),
                id).stream().findAny().orElse(null);
        if(employee != null){
            employeeDTO = employee.getEmployeeDTOByEmployee();
        }
        return employeeDTO;
    }


    @Override
    public void save(EmployeeDTO employee) {
        jdbcTemplate.update("INSERT INTO Employee(name, surname, age, phoneNumber) VALUES(?, ?, ?, ?)",
                employee.getName(), employee.getSurname(), employee.getAge(), employee.getPhoneNumber());
    }

    @Override
    public void update(long id, EmployeeDTO employeeDTO) {
        jdbcTemplate.update("UPDATE Employee SET name=?, surname=?, age=?, phoneNumber=? WHERE id=?",
                employeeDTO.getName(),
                employeeDTO.getSurname(),
                employeeDTO.getAge(),
                employeeDTO.getPhoneNumber(),
                id);
    }

    @Override
    public void delete(long id) {
        jdbcTemplate.update("DELETE FROM Employee WHERE id=?", id);
    }
}
