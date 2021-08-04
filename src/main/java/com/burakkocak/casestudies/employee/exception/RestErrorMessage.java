package com.burakkocak.casestudies.employee.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.sun.istack.Nullable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.util.Arrays;
import java.util.List;

/**
 * @author Burak KOCAK
 * @date 7/30/2021
 */
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter @Setter
public class RestErrorMessage {

    private HttpStatus status;

    private String message;

    private List<String> errors;

    @Nullable
    @JsonProperty("exception")
    private String exceptionName;

    public RestErrorMessage(HttpStatus status, RestErrorEnum error) {
        this.status = status;
        this.message = error.getDescription();
        this.errors = Arrays.asList(String.valueOf(error.getCode()));
    }

    public RestErrorMessage(HttpStatus status, String message, String error) {
        this.status = status;
        this.message = message;
        this.errors = Arrays.asList(error);
    }

    public RestErrorMessage(HttpStatus status, String message, List<String> errors) {
        this.status = status;
        this.message = message;
        this.errors = errors;
    }
}
