package com.burakkocak.casestudies.employee.entity;

import com.burakkocak.casestudies.employee.listener.EntityCrudListener;
import lombok.*;

import javax.persistence.*;

/**
 * @author Burak KOCAK
 * @date 8/4/2021
 */
@Entity
@Table(name = "OFFICE_LOCATION", uniqueConstraints = {
        @UniqueConstraint(columnNames = "location")
})
@EntityListeners(EntityCrudListener.class)
@Getter @Setter @ToString
@AllArgsConstructor
@NoArgsConstructor
public class OfficeLocation {

    @Id
    private Long id;

    @Column(name = "LOCATION")
    private String location;

}
