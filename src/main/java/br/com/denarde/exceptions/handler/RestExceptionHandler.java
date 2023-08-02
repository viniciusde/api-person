package br.com.denarde.exceptions.handler;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RestControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    public final ResponseEntity<ExceptionResponse> handleBusinessException(BusinessException exception) {
        ExceptionResponse response = ExceptionResponse.builder()
                .code(exception.getMessage())
                .message(exception.getMessage())
                .details(exception.getDetails())
                .build();
        return ResponseEntity.status(exception.getStatus()).body(response);
    }

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<ExceptionResponse> handleException(Exception exception) {
        ExceptionResponse response = ExceptionResponse.builder()
                .code(HttpStatus.INTERNAL_SERVER_ERROR.name())
                .message(HttpStatus.INTERNAL_SERVER_ERROR.name())
                .details(Collections.singletonList(exception.getMessage()))
                .build();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        List<String> errors = ex.getBindingResult().getFieldErrors().stream()
                .map(fieldError -> String.format("%s: %s", fieldError.getField(), fieldError.getDefaultMessage()))
                .collect(Collectors.toList());
        ExceptionResponse response = ExceptionResponse.builder()
                .code(HttpStatus.BAD_REQUEST.name())
                .message(HttpStatus.BAD_REQUEST.toString())
                .details(errors)
                .build();
        return ResponseEntity.status(status.value()).body(response);
    }

    @Override
    protected ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
       String error = Objects.requireNonNull(ex.getMessage()).split("for")[0].trim();
        ExceptionResponse response = ExceptionResponse.builder()
                .code(HttpStatus.BAD_REQUEST.name())
                .message(HttpStatus.BAD_REQUEST.toString())
                .details(Collections.singletonList(error))
                .build();
        return ResponseEntity.status(status.value()).body(response);
    }
}
