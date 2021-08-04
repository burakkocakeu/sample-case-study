package com.burakkocak.casestudies.employee.repository;

import com.burakkocak.casestudies.employee.entity.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * @author Burak KOCAK
 * @date 7/30/2021
 */
@Repository
public interface EmployeeRepository extends CrudRepository<Employee, Long> {

    Optional<Page<Employee>> findAll(Pageable pageable);

    List<Employee> findAll();

    @Query("SELECT e, i FROM Employee e " +
            "JOIN EmployeeInformation i " +
            "ON e.identity = i.identity " +
            "WHERE i.dateStarted >= :date " +
            "AND i.currentSalary >= :salary")
    Optional<Page<Employee>> findAllByMinDateAndMinSalary(@Param("date") Date date,
                                                          @Param("salary") BigDecimal salary,
                                                          Pageable pageable);

    @Query("SELECT e, i FROM Employee e " +
            "JOIN EmployeeInformation i " +
            "ON e.identity = i.identity " +
            "WHERE i.departmentId.id = :departmentId")
    Optional<Page<Employee>> findAllByDepartmentId(@Param("departmentId") Long departmentId,
                                                   Pageable pageable);

    @Query("SELECT e FROM Employee e ORDER BY FUNCTION('RANDOM')")
    List<Employee> selectPrizeWinner(Pageable pageable);

    Optional<Employee> findByIdentity(Long identity);

    Optional<Page<Employee>> findByNameIn(List<String> nameList,
                                          Pageable pageable);

}
