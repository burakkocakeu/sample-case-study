package com.burakkocak.casestudies.employee.repository;

import com.burakkocak.casestudies.employee.entity.PrizeWinnersHistory;
import com.burakkocak.casestudies.employee.resource.IPrizeWinnersHistoryDto;
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
public interface PrizeWinnersHistoryRepository extends CrudRepository<PrizeWinnersHistory, Long> {

    @Query("SELECT p FROM PrizeWinnersHistory p " +
            "WHERE FUNCTION('MONTH', p.date) = :month " +
            "AND FUNCTION('YEAR', p.date) = :year")
    Optional<PrizeWinnersHistory> findByDateMonth(@Param("year") int year,
                                                  @Param("month") int month);

    @Query("SELECT e.identity as identity, e.name as name, e.surname as surname, p.date as date " +
            "FROM PrizeWinnersHistory p " +
            "JOIN Employee e ON e.id = p.employeeId.id " +
            "ORDER BY p.date DESC")
    List<IPrizeWinnersHistoryDto> findLast12(Pageable pageable);

}
