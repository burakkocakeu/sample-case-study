package com.burakkocak.casestudies.employee.resource;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author Burak KOCAK
 * @date 7/30/2021
 */
@Getter
@Setter
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
@AllArgsConstructor
public class EmployeeDto extends EmployeeInformationDto {

    @NotNull
    private Long identity;

    @NotEmpty
    @Size(min = 2, max = 55)
    private String name;

    @NotEmpty
    @Size(min = 2, max = 55)
    private String surname;

    private String tn;

    private String email;

}
