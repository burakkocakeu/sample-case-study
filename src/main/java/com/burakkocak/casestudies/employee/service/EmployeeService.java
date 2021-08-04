package com.burakkocak.casestudies.employee.service;

import com.burakkocak.casestudies.employee.entity.*;
import com.burakkocak.casestudies.employee.exception.RestErrorEnum;
import com.burakkocak.casestudies.employee.exception.RestException;
import com.burakkocak.casestudies.employee.repository.*;
import com.burakkocak.casestudies.employee.resource.EmployeeDto;
import com.burakkocak.casestudies.employee.resource.IPrizeWinnersHistoryDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author Burak KOCAK
 * @date 7/30/2021
 *
 * a. ConfigurableBeanFactory.SCOPE_PROTOTYPE = A bean with prototype scope will return a different instance every time it is requested from the container.
 * b. ScopedProxyMode.TARGET_CLASS = If there is no active request. Spring will create a proxy to be injected as a dependency,
 *    and instantiate the target bean when it is needed in a request.
 */
@Slf4j
@Service
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class EmployeeService {

    private EmployeeRepository employeeRepository;
    private EmployeePayrollRepository employeePayrollRepository;
    private EmployeeInformationRepository employeeInformationRepository;
    private DepartmentRepository departmentRepository;
    private OfficeLocationRepository officeLocationRepository;
    private PrizeWinnersHistoryRepository prizeWinnersHistoryRepository;

    private ModelMapper modelMapper = new ModelMapper();
    private ObjectMapper objectMapper = new ObjectMapper()
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public EmployeeService(EmployeeRepository employeeRepository,
                           EmployeePayrollRepository employeePayrollRepository,
                           EmployeeInformationRepository employeeInformationRepository,
                           DepartmentRepository departmentRepository,
                           OfficeLocationRepository officeLocationRepository,
                           PrizeWinnersHistoryRepository prizeWinnersHistoryRepository) {

        this.employeeRepository = employeeRepository;
        this.employeePayrollRepository = employeePayrollRepository;
        this.employeeInformationRepository = employeeInformationRepository;

        this.departmentRepository = departmentRepository;
        this.officeLocationRepository = officeLocationRepository;
        this.prizeWinnersHistoryRepository = prizeWinnersHistoryRepository;
    }

    private Pageable handlePageDefaults(Integer page, Integer size) {
        if (page == null) {
            page = 0;
        }

        if (size == null) {
            size = 50;
        }

        return PageRequest.of(page, size);
    }

    public List<Employee> getAllEmployeeList() {

        return employeeRepository.findAll();

    }

    public List<EmployeeInformation> getAllEmployeeInformationList() {

        return employeeInformationRepository.findAll();

    }

    public List<Department> getAllDepartmentList() {

        return departmentRepository.findAll();

    }

    public List<OfficeLocation> getAllOfficeLocationList() {

        return officeLocationRepository.findAll();

    }

    public Page<Employee> getAllEmployeeListAsPageable(Integer page, Integer size) {

        return employeeRepository.findAll(handlePageDefaults(page, size)).get();

    }

    public Page<Employee> getAllEmployeeListByDateAndSalaryAsPageable(String date, Integer salary) throws ParseException {

        return employeeRepository.findAllByMinDateAndMinSalary(
                dateFormat.parse(date),
                BigDecimal.valueOf(salary),
                handlePageDefaults(null, null)).get();

    }

    public Page<Employee> getAllEmployeeListByDepartmentIdAsPageable(Long departmentId) {

        return employeeRepository.findAllByDepartmentId(
                departmentId,
                handlePageDefaults(null, null)).get();

    }

    @Transactional
    public void saveAllEmployeeList(List<EmployeeDto> employeeDtoList) throws RestException {
        if (null == employeeDtoList || employeeDtoList.isEmpty()) {
            throw new RestException(RestErrorEnum.EMPTY_LIST);
        }

        log.info("Employee List: " + employeeDtoList);

        List<Employee> employeeList = employeeDtoList.stream()
                .map(dto -> objectMapper.convertValue(dto, Employee.class))
                .collect(Collectors.toList());

        List<EmployeeInformation> employeeInformationList = employeeDtoList.stream()
                .map(dto -> {
                    EmployeeInformation e = modelMapper.map(dto, EmployeeInformation.class);
                    e.setDateStarted(new Date());
                    e.setRemainedPaidLeave(14L);
                    return e;
                })
                .collect(Collectors.toList());

        employeeRepository.saveAll(employeeList);

        employeeInformationRepository.saveAll(employeeInformationList);
    }

    public void updateAllLocationsByDepartmentById(Integer departmentId, Integer locationId) throws RestException {
        Optional<OfficeLocation> officeLocation = officeLocationRepository.findById(Long.valueOf(locationId));

        if (officeLocation.isEmpty()) {
            throw new RestException(RestErrorEnum.LOCATION_NOT_FOUND);
        }

        Optional<Department> department = departmentRepository.findById(Long.valueOf(departmentId));

        if (department.isEmpty()) {
            throw new RestException(RestErrorEnum.DEPARTMENT_NOT_FOUND);
        }

        employeeInformationRepository.updateAllLocationsByDepartmentById(Long.valueOf(departmentId), officeLocation.get().getId());
    }

    public List<IPrizeWinnersHistoryDto> selectPrizeWinnerAndReturnAllWinnerList() throws RestException, JsonProcessingException {
        Optional<PrizeWinnersHistory> winnersHistory =
                prizeWinnersHistoryRepository.findByDateMonth(
                        Calendar.getInstance().get(Calendar.YEAR),
                        Calendar.getInstance().get(Calendar.MONTH) + 1);

        if (winnersHistory.isEmpty()) {
            log.info("Selecting a prize winner of the current month...");
            List<Employee> employeeList = employeeRepository.selectPrizeWinner(PageRequest.of(0,1));

            if (employeeList.isEmpty()) {
                log.error("No active employee found! A prize winner of the current month could not be selected.");
                throw new RestException(RestErrorEnum.NO_EMPLOYEE_FOUND);
            }

            PrizeWinnersHistory newWinner = new PrizeWinnersHistory(
                    new Date(),
                    employeeList.get(0));
            prizeWinnersHistoryRepository.save(newWinner);
            log.info("Prize winner is: {}", objectMapper.writeValueAsString(newWinner));
        }

        return prizeWinnersHistoryRepository.findLast12(PageRequest.of(0,12));
    }

}
