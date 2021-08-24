package com.buenoezandro.linkedIn.api.exceptions;

import com.buenoezandro.linkedIn.api.services.exceptions.UsuarioNaoEncontradoException;
import lombok.AllArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@AllArgsConstructor
@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    private final MessageSource messageSource;

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers, HttpStatus status,
                                                                  WebRequest request) {
        List<ErrorResponse.FieldErrors> fieldErrors = new ArrayList<>();

        ex.getBindingResult().getAllErrors().forEach(error -> {
            String name = ((FieldError) error).getField();
            String message = this.messageSource.getMessage(error, LocaleContextHolder.getLocale());

            fieldErrors.add(new ErrorResponse.FieldErrors(name, message));
        });

        var errorResponse = new ErrorResponse();
        errorResponse.setStatus(status.value());
        errorResponse.setDateTime(OffsetDateTime.now());
        errorResponse.setMessage("Um ou mais campos estão inválidos! Faça o preenchimento correto e tente novamente.");
        errorResponse.setErrors(fieldErrors);

        return handleExceptionInternal(ex, errorResponse, headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException exception,
                                                                  HttpHeaders headers, HttpStatus status, WebRequest request) {
        return this.buildResponseEntity(HttpStatus.BAD_REQUEST, "Malformed JSON body and/or field error",
                Collections.singletonList(exception.getLocalizedMessage()));
    }

    @ExceptionHandler(UsuarioNaoEncontradoException.class)
    private ResponseEntity<Object> handleUsuarioNaoEncontrado(UsuarioNaoEncontradoException ex, WebRequest request) {
        HttpStatus status = HttpStatus.NOT_FOUND;

        var errorResponse = new ErrorResponse();
        errorResponse.setStatus(status.value());
        errorResponse.setDateTime(OffsetDateTime.now());
        errorResponse.setMessage(ex.getMessage());

        return handleExceptionInternal(ex, errorResponse, new HttpHeaders(), status, request);

    }

    private ResponseEntity<Object> buildResponseEntity(HttpStatus httpStatus, String message, List<String> errors) {
        var error = JsonParseError.builder().code(httpStatus.value()).status(httpStatus.getReasonPhrase()).message(message)
                .errors(errors).timestamp(OffsetDateTime.now()).build();
        return ResponseEntity.status(httpStatus).body(error);
    }
}