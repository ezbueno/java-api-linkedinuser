package com.buenoezandro.linkedIn.api.exceptions;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;
import java.util.List;

@JsonInclude(value = JsonInclude.Include.NON_NULL)
@Getter
@Setter
public class ErrorResponse {

    private Integer status;
    private OffsetDateTime dateTime;
    private String message;
    private List<FieldErrors> errors;


    @Getter
    @AllArgsConstructor
    public static class FieldErrors {

        private String fieldName;
        private String message;

    }
}