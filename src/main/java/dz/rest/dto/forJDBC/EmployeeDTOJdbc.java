package dz.rest.dto.forJDBC;

import dz.rest.dto.interfaces.EmployeeDTO;
import dz.rest.models.Employee;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;


import java.util.List;

@Component
public class EmployeeDTOJdbc implements EmployeeDTO {

    private final JdbcTemplate jdbcTemplate;

    public EmployeeDTOJdbc(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Employee> index() {
        return jdbcTemplate.query("SELECT * FROM Employee", new BeanPropertyRowMapper<>(Employee.class));
    }

    @Override
    public Employee show(int id) {
        return jdbcTemplate.query("SELECT * FROM Employee WHERE id=?",
                new BeanPropertyRowMapper<>(Employee.class),
                id).stream().findAny().orElse(null);
    }

    @Override
    public void save(Employee employee) {
        jdbcTemplate.update("INSERT INTO Employee(name, surname, age, phoneNumber) VALUES(?, ?, ?, ?)",
                employee.getName(), employee.getSurname(), employee.getAge(), employee.getPhoneNumber());
    }

    @Override
    public void update(int id, Employee updateEmployee) {
        jdbcTemplate.update("UPDATE Employee SET name=?, surname=?, age=?, phoneNumber=? WHERE id=?",
                updateEmployee.getName(),
                updateEmployee.getSurname(),
                updateEmployee.getAge(),
                updateEmployee.getPhoneNumber(),
                id);
    }

    @Override
    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM Employee WHERE id=?", id);
    }
}
