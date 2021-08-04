package com.burakkocak.casestudies.employee.service;

import com.burakkocak.casestudies.employee.entity.Department;
import com.burakkocak.casestudies.employee.entity.Employee;
import com.burakkocak.casestudies.employee.entity.EmployeeInformation;
import com.burakkocak.casestudies.employee.entity.OfficeLocation;
import com.burakkocak.casestudies.employee.exception.RestException;
import com.burakkocak.casestudies.employee.repository.*;
import com.burakkocak.casestudies.employee.resource.EmployeeDto;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;


/**
 * @author Burak KOCAK
 * @date 8/4/2021
 */
@ExtendWith(MockitoExtension.class)
class EmployeeServiceTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @Mock
    private EmployeePayrollRepository employeePayrollRepository;

    @Mock
    private EmployeeInformationRepository employeeInformationRepository;

    @Mock
    private DepartmentRepository departmentRepository;

    @Mock
    private OfficeLocationRepository officeLocationRepository;

    @Mock
    private PrizeWinnersHistoryRepository prizeWinnersHistoryRepository;

    private EmployeeService employeeService;

    private ObjectMapper objectMapper = new ObjectMapper()
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

    @BeforeEach
    void setUp() {
        employeeService = new EmployeeService(
                employeeRepository,
                employeePayrollRepository,
                employeeInformationRepository,
                departmentRepository,
                officeLocationRepository,
                prizeWinnersHistoryRepository);
    }

    @Test
    void getAllEmployeeList() {
        // when
        List<Employee> employeeList = employeeService.getAllEmployeeList();

        // then
        verify(employeeRepository).findAll();
        assertThat(employeeList).asList();
    }

    @Test
    void getAllEmployeeInformationList() {
        // when
        List<EmployeeInformation> employeeInformationList = employeeService.getAllEmployeeInformationList();

        // then
        verify(employeeInformationRepository).findAll();
        assertThat(employeeInformationList).asList();
    }

    @Test
    void getAllDepartmentList() {
        // when
        List<Department> departmentList = employeeService.getAllDepartmentList();

        // then
        verify(departmentRepository).findAll();
        assertThat(departmentList).asList();
    }

    @Test
    void getAllOfficeLocationList() {
        // when
        List<OfficeLocation> officeLocationList = employeeService.getAllOfficeLocationList();

        // then
        verify(officeLocationRepository).findAll();
        assertThat(officeLocationList).asList();
    }

    @Test
    void getAllEmployeeListAsPageable() {
    }

    @Test
    void getAllEmployeeListByDateAndSalaryAsPageable() {
    }

    @Test
    void getAllEmployeeListByDepartmentIdAsPageable() {
    }

    @Test
    void saveAllEmployeeList() throws RestException {
        // given
        List<EmployeeDto> employeeDtoList = new ArrayList<>();

        employeeDtoList.add(new EmployeeDto(87543377832L, "Josh", "Smith", "54432262214", "josh.smith@business.com"));
        employeeDtoList.add(new EmployeeDto(33214423223L, "Cristina", "Gonzales", "4431555323", "cristina.gonzales@business.com"));

        // when
        employeeService.saveAllEmployeeList(employeeDtoList);

        // then
        ArgumentCaptor<List<Employee>> employeeListArgumentCaptor = ArgumentCaptor.forClass(List.class);
        ArgumentCaptor<List<EmployeeInformation>> employeeInformationListArgumentCaptor = ArgumentCaptor.forClass(List.class);

        verify(employeeRepository).saveAll(employeeListArgumentCaptor.capture());
        verify(employeeInformationRepository).saveAll(employeeInformationListArgumentCaptor.capture());

        List<Employee> capturedEmployeeList = employeeListArgumentCaptor.getValue();
        List<EmployeeInformation> capturedEmployeeInformationList = employeeInformationListArgumentCaptor.getValue();

        assertThat(capturedEmployeeList.size()).isEqualTo(employeeDtoList.size());
        assertThat(capturedEmployeeInformationList.size()).isEqualTo(employeeDtoList.size());

        // when
        // then
        assertThatThrownBy(() -> employeeService.saveAllEmployeeList(null))
                .isInstanceOf(RestException.class)
                .hasMessageContaining("Empty list!");

        verify(employeeRepository, times(1)).saveAll(any());
    }

    @Test
    void updateAllLocationsByDepartmentById() throws RestException {
        // given
        Long departmentId = 1L;
        Long locationId = 1L;

        OfficeLocation officeLocation = new OfficeLocation(1L, "ISTANBUL");
        Department department = new Department(1L, "FINANCE");

        // when
        // then
        given(officeLocationRepository.findById(locationId))
                .willReturn(Optional.of(officeLocation));

        given(departmentRepository.findById(departmentId))
                .willReturn(Optional.of(department));

        employeeService.updateAllLocationsByDepartmentById(departmentId.intValue(), locationId.intValue());

        verify(officeLocationRepository, times(1)).findById(anyLong());
        verify(departmentRepository, times(1)).findById(anyLong());

        // when
        // then
        given(officeLocationRepository.findById(locationId))
                .willReturn(Optional.empty());

        assertThatThrownBy(() -> employeeService.updateAllLocationsByDepartmentById(departmentId.intValue(), locationId.intValue()))
                .isInstanceOf(RestException.class)
                .hasMessageContaining("Location not found!");

        // when
        // then
        given(officeLocationRepository.findById(locationId))
                .willReturn(Optional.of(officeLocation));

        given(departmentRepository.findById(locationId))
                .willReturn(Optional.empty());

        assertThatThrownBy(() -> employeeService.updateAllLocationsByDepartmentById(departmentId.intValue(), locationId.intValue()))
                .isInstanceOf(RestException.class)
                .hasMessageContaining("Department not found!");

        verify(officeLocationRepository, times(3)).findById(anyLong());
        verify(departmentRepository, times(2)).findById(anyLong());
    }

    @Test
    void selectPrizeWinnerAndReturnAllWinnerList() {
    }
}