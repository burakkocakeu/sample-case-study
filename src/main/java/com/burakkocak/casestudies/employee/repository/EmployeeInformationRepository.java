package com.burakkocak.casestudies.employee.repository;

import com.burakkocak.casestudies.employee.entity.EmployeeInformation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * @author Burak KOCAK
 * @date 7/30/2021
 */
@Repository
public interface EmployeeInformationRepository extends CrudRepository<EmployeeInformation, Long> {

    @Query("UPDATE EmployeeInformation i " +
            "SET i.locationId = :locationId " +
            "WHERE i.departmentId = :departmentId")
    void updateAllLocationsByDepartmentById(@Param("departmentId") Long departmentId,
                                            @Param("locationId") Long locationId);

    Optional<Page<EmployeeInformation>> findAll(Pageable pageable);

    List<EmployeeInformation> findAll();

}
