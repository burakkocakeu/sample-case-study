package com.burakkocak.casestudies.employee.repository;

import com.burakkocak.casestudies.employee.entity.EmployeePayroll;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Burak KOCAK
 * @date 7/30/2021
 */
@Repository
public interface EmployeePayrollRepository extends CrudRepository<EmployeePayroll, Long> {
}
