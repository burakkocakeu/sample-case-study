package com.burakkocak.casestudies.employee.entity;

import com.burakkocak.casestudies.employee.listener.EntityCrudListener;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

/**
 * @author Burak KOCAK
 * @date 7/30/2021
 */
@Entity
@Table(name = "EMPLOYEE", uniqueConstraints = {
        @UniqueConstraint(columnNames = "identity")
})
@EntityListeners(EntityCrudListener.class)
@Getter @Setter @ToString
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "IDENTITY", nullable = false)
    private Long identity;

    @Column(name = "NAME")
    private String name;

    @Column(name = "SURNAME")
    private String surname;

    @Column(name = "TN")
    private String tn;

    @Column(name = "EMAIL")
    private String email;

}
