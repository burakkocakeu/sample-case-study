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
@Table(name = "EMPLOYEE_INFORMATION")
@EntityListeners(EntityCrudListener.class)
@Getter @Setter @ToString
public class EmployeeInformation {

    @Id
    @Column(name = "IDENTITY")
    private Long identity;

    @Column(name = "DATE_STARTED")
    @Temporal(TemporalType.DATE)
    private Date dateStarted;

    @Column(name = "TITLE")
    private String title;

    @ManyToOne
    @JoinColumn(name="department_id", nullable=false)
    private Department departmentId;

    @ManyToOne
    @JoinColumn(name="location_id", nullable=false)
    private OfficeLocation locationId;

    @Column(name = "SALARY")
    private BigDecimal currentSalary;

    @Column(name = "REMAINED_PAID_LEAVE")
    private Long remainedPaidLeave;

    @Column(name = "ADDRESS")
    private String address;

    @Column(name = "BORN")
    private Long dateOfBirth;

}
