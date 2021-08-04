package com.burakkocak.casestudies.employee;

import com.burakkocak.casestudies.employee.service.EmployeeService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EmployeeApplication implements CommandLineRunner {

    private EmployeeService employeeService;

    public EmployeeApplication(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    public static void main(String[] args) {
        SpringApplication.run(EmployeeApplication.class, args);
    }

    @Override
    public void run(String... args) {

        employeeService.getAllEmployeeList();
        employeeService.getAllEmployeeInformationList();
        employeeService.getAllDepartmentList();
        employeeService.getAllOfficeLocationList();

    }
}
