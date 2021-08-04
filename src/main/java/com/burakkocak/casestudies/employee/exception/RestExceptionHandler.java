package com.burakkocak.casestudies.employee.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Burak KOCAK
 * @date 7/30/2021
 */
@ControllerAdvice @Slf4j
public class RestExceptionHandler {

    @ExceptionHandler(RestException.class)
    protected ResponseEntity handleRestServiceExceptions(Exception e) {
        List<String> errors = new ArrayList<>();

        StackTraceElement error = e.getStackTrace()[0];
        errors.add(error.getClassName() + "." + error.getMethodName() + "()" + "[L:" + error.getLineNumber() + "]");

        RestErrorMessage errorMessage = new RestErrorMessage(HttpStatus.BAD_REQUEST, e.getLocalizedMessage(), errors);

        return ResponseEntity.status(errorMessage.getStatus())
                .body(errorMessage);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    protected ResponseEntity handleHttpMessageNotReadableExceptions(Exception e) {
        RestErrorMessage errorMessage = new RestErrorMessage(HttpStatus.BAD_REQUEST, RestErrorEnum.INVALID_REQUEST_DATA);

        return ResponseEntity.status(errorMessage.getStatus())
                .body(errorMessage);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    protected ResponseEntity handleDataIntegrityViolationExceptions(Exception e) {
        RestErrorMessage errorMessage = new RestErrorMessage(HttpStatus.BAD_REQUEST, RestErrorEnum.CONSTRAINT_VIOLATION);

        return ResponseEntity.status(errorMessage.getStatus())
                .body(errorMessage);
    }

    @ExceptionHandler(ParseException.class)
    protected ResponseEntity handleParseExceptions(Exception e) {
        RestErrorMessage errorMessage = new RestErrorMessage(HttpStatus.BAD_REQUEST, RestErrorEnum.INVALID_DATA_FORMAT);

        return ResponseEntity.status(errorMessage.getStatus())
                .body(errorMessage);
    }

    @ExceptionHandler(Exception.class)
    protected ResponseEntity handleUnhandledExceptions(Exception e) {
        List<String> errors = new ArrayList<>();

        StackTraceElement error = e.getStackTrace()[0];
        errors.add(error.getClassName() + "." + error.getMethodName() + "()" + "[L:" + error.getLineNumber() + "]");

        RestErrorMessage errorMessage = new RestErrorMessage(HttpStatus.INTERNAL_SERVER_ERROR, e.getLocalizedMessage(), errors, e.getClass().getSimpleName());

        return ResponseEntity.status(errorMessage.getStatus())
                .body(errorMessage);
    }

}
