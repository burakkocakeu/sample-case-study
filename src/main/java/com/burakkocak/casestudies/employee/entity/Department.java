package com.burakkocak.casestudies.employee.entity;

import com.burakkocak.casestudies.employee.listener.EntityCrudListener;
import lombok.*;

import javax.persistence.*;

/**
 * @author Burak KOCAK
 * @date 8/4/2021
 */
@Entity
@Table(name = "DEPARTMENT", uniqueConstraints = {
        @UniqueConstraint(columnNames = "department")
})
@EntityListeners(EntityCrudListener.class)
@Getter @Setter @ToString
@AllArgsConstructor
@NoArgsConstructor
public class Department {

    @Id
    private Long id;

    @Column(name = "DEPARTMENT")
    private String department;

}
