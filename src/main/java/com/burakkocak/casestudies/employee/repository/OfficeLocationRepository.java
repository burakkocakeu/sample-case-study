package com.burakkocak.casestudies.employee.repository;

import com.burakkocak.casestudies.employee.entity.OfficeLocation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * @author Burak KOCAK
 * @date 7/30/2021
 */
@Repository
public interface OfficeLocationRepository extends CrudRepository<OfficeLocation, Long> {

    Optional<Page<OfficeLocation>> findAll(Pageable pageable);

    List<OfficeLocation> findAll();

}
