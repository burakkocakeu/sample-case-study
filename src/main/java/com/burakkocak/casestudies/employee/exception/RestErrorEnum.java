package com.burakkocak.casestudies.employee.exception;

import lombok.Getter;
import lombok.ToString;

/**
 * @author Burak KOCAK
 * @date 7/30/2021
 */
@Getter @ToString
public enum RestErrorEnum {

    UNKNOWN_EMPLOYEE(1000, "Employee could not be found!"),
    INVALID_DATA(1001, "Invalid data provided at field, '%'."),
    INVALID_REQUEST_DATA(1002, "Invalid data structure provided in the request body!"),
    CONSTRAINT_VIOLATION(1003, "Incoming message caused a ConstraintViolationException, please make sure related field is unique that should not exist already!"),
    DEPARTMENT_NOT_FOUND(1004, "Department not found!"),
    LOCATION_NOT_FOUND(1005, "Location not found!"),
    NO_EMPLOYEE_FOUND(1006, "No employee found!"),
    EMPTY_LIST(1007, "Empty list!"),
    INVALID_DATA_FORMAT(1008, "Incoming message has invalid data format!");

    private final int code;
    private final String description;

    RestErrorEnum(int code, String description) {
        this.code = code;
        this.description = description;
    }

}
