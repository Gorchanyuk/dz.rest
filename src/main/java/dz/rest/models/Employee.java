package dz.rest.models;

import dz.rest.DTO.EmployeeDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Employee {

    @Id
//    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "my_gen")
//    @SequenceGenerator(name = "my_gen", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "my_gen")
    private long id;
    private String name;
    private String surname;
    private int age;
    private String phoneNumber;

    public void setEmployeeByEmployeeDTO(EmployeeDTO employeeDTO) {
        this.setName(employeeDTO.getName());
        this.setSurname(employeeDTO.getSurname());
        this.setAge(employeeDTO.getAge());
        this.setPhoneNumber(employeeDTO.getPhoneNumber());
    }

    public EmployeeDTO getEmployeeDTOByEmployee() {
        EmployeeDTO employeeDTO = new EmployeeDTO();
        employeeDTO.setName(this.getName());
        employeeDTO.setSurname(this.getSurname());
        employeeDTO.setAge(this.getAge());
        employeeDTO.setPhoneNumber(this.getPhoneNumber());
        return employeeDTO;
    }


    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", age=" + age +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }
}
