package dz.rest.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDTO {

    @NotBlank
    private String name;
    @NotBlank
    private String surname;
    @NotNull
    @Min(value = 18)
    private int age;
    @NotBlank
    private String phoneNumber;
}
