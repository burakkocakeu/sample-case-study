package com.burakkocak.casestudies.employee.entity;

import com.burakkocak.casestudies.employee.listener.EntityCrudListener;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

/**
 * @author Burak KOCAK
 * @date 8/4/2021
 */
@Entity
@Table(name = "PRIZE_WINNER_HISTORY")
@EntityListeners(EntityCrudListener.class)
@Getter @Setter @ToString
@AllArgsConstructor
@NoArgsConstructor
public class PrizeWinnersHistory {

    @Id
    @Column(name = "DATE")
    @Temporal(TemporalType.DATE)
    private Date date;

    @ManyToOne
    @JoinColumn(name="employee_id", nullable=false)
    private Employee employeeId;

}
