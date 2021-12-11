package dz.rest.dto.hibernate;

import dz.rest.dto.interfaces.EmployeeDTO;
import dz.rest.dto.interfaces.EmployeeRepository;
import dz.rest.models.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class EmployeeHibernate implements EmployeeDTO {
    @Autowired
    private EmployeeRepository employeeRepository;

    public List<Employee> index (){
        return employeeRepository.findAll();
    }

    @Override
    public Employee show(long id) {
        return null;
    }

    @Override
    public void save(Employee employee) {

    }

    @Override
    public void update(long id, Employee updateEmployee) {

    }

    @Override
    public void delete(long id) {

    }

}
