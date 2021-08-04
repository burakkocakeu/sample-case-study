package com.burakkocak.casestudies.employee.entity;

import com.burakkocak.casestudies.employee.listener.EntityCrudListener;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author Burak KOCAK
 * @date 7/30/2021
 */
@Entity
@Table(name = "EMPLOYEE_PAYROLL", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"identity", "date"})
})
@EntityListeners(EntityCrudListener.class)
@Getter @Setter @ToString
public class EmployeePayroll {

    @Id
    @Column(name = "IDENTITY")
    private Long identity;

    @Column(name = "DATE")
    @Temporal(TemporalType.DATE)
    private Date date;

    @Column(name = "PAYROLL")
    private BigDecimal payroll;

}
