package com.burakkocak.casestudies.employee.controller;

import com.burakkocak.casestudies.employee.annotation.TrackExecutionTime;
import com.burakkocak.casestudies.employee.entity.Employee;
import com.burakkocak.casestudies.employee.exception.RestException;
import com.burakkocak.casestudies.employee.resource.EmployeeDto;
import com.burakkocak.casestudies.employee.service.EmployeeService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

/**
 * @author Burak KOCAK
 * @date 7/30/2021
 *
 * Employee Record Application:
 *
 * Write a service that holds employee records.
 * It should hold employment details, payroll and their personal information.
 *
 * The service must have rest endpoints to run crud functions (Create, Read Update, Delete)
 *
 * Apart from crud endpoints following endpoints are required:
 *  •An endpoint that returns all the employees that started after specific date and their income is greater than specific amount.
 *  •An endpoint that updates office location of all the employees of specific department
 *  •A service method that returns a random employee for monthly prize draw every month at specific time and saves it to the database. With an endpoint to call the winner every month
 *
 * Note:
 *
 * Format and fields of database table(s) is in your hand just choose them in a way that meets requirements of the test
 *
 * Use in memory database
 *
 * Use of Spring boot, JPA, Spring Data, Hibernate, Java 8 lambda expression (where required) are preferred
 *
 * Use Maven for dependency injection
 *
 * Please include test for your application (Integration test and unit test)
 *
 * Use Git for version control and make your project accessible on Github
 */
@RestController
@RequestMapping("/employee")
public class EmployeeController {

    private EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    @TrackExecutionTime
    public ResponseEntity getEmployeeList(@RequestParam(required = false) Integer page, @RequestParam(required = false) Integer size) {
        Page<Employee> employeePage =
                employeeService.getAllEmployeeListAsPageable(page, size);

        return new ResponseEntity(employeePage, HttpStatus.OK);
    }

    @GetMapping("/lookup/{date}/{salary}")
    @TrackExecutionTime
    public ResponseEntity getEmployeeListByDateAndSalary(@PathVariable String date, @PathVariable Integer salary) throws ParseException {
        Page<Employee> employeePage =
                employeeService.getAllEmployeeListByDateAndSalaryAsPageable(date, salary);

        return new ResponseEntity(employeePage, HttpStatus.OK);
    }

    @GetMapping("/department/{departmentId}")
    @TrackExecutionTime
    public ResponseEntity getEmployeeListByDepartmentId(@PathVariable Long departmentId) {
        Page<Employee> employeePage =
                employeeService.getAllEmployeeListByDepartmentIdAsPageable(departmentId);

        return new ResponseEntity(employeePage, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity saveEmployeeList(@Validated @RequestBody List<EmployeeDto> employeeDtoList) throws RestException {
        employeeService.saveAllEmployeeList(employeeDtoList);

        return new ResponseEntity(employeeDtoList, HttpStatus.OK);
    }

    @PutMapping("/update/location/{departmentId}/{locationId}")
    public ResponseEntity updateLocationsByDepartmentById(@PathVariable Integer departmentId,
                                                          @PathVariable Integer locationId) throws RestException {
        employeeService.updateAllLocationsByDepartmentById(departmentId, locationId);

        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/prize-winner")
    public ResponseEntity selectPrizeWinnerAndReturnWinnerList() throws RestException, JsonProcessingException {

        return new ResponseEntity(employeeService.selectPrizeWinnerAndReturnAllWinnerList(), HttpStatus.OK);
    }

}
