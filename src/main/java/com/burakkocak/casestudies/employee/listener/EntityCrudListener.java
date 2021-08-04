package com.burakkocak.casestudies.employee.listener;

import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;

/**
 * @author Burak KOCAK
 * @date 7/30/2021
 */
@Slf4j
public class EntityCrudListener {

    @PrePersist
    @PreUpdate
    @PreRemove
    private void preUpdateListener(Object o) {
        log.debug("Entity on Pre 'Persist/Update/Remove': " + o.toString());
    }

    @PostPersist
    @PostUpdate
    @PostRemove
    private void postUpdateListener(Object o) {
        log.debug("Entity on Post 'Persist/Update/Remove': " + o.toString());
    }

    @PostLoad
    private void postLoadListener(Object o) {
        log.debug("Entity on 'Load': " + o.toString());
    }

}
