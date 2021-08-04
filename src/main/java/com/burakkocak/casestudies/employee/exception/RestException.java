package com.burakkocak.casestudies.employee.exception;

/**
 * @author Burak KOCAK
 * @date 7/30/2021
 */
public class RestException extends Exception {

    public RestException(RestErrorEnum error) {
        super(error.getCode() + ": '" + error.getDescription() + "'");
    }

}
