package br.com.userapi.userapi.presentation.exception;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @Autowired
    private UnprocessableEntityException unprocessableEntityException;

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(Exception ex) {
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(),
                ex.getMessage(), new ArrayList<>());
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(PersonNotFoundException.class)
    public ResponseEntity<ErrorResponse> handlePersonNotFoundException(PersonNotFoundException ex) {
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.NOT_FOUND.value(), "Person not found",
                new ArrayList<>());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
            HttpHeaders headers, HttpStatus status, WebRequest request) {
        return unprocessableEntityException.handleValidationException(ex, headers, status, request);
    }

    @ExceptionHandler(ConflictException.class)
    public ResponseEntity<Object> handleDataIntegrityViolationException(ConflictException ex) {
        List<DetailResponse> errors = new ArrayList<>();
        errors.add(DetailResponse.builder()
                .field(ex.getField())
                .value(ex.getValue())
                .message(ex.getMessage())
                .build());

        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.CONFLICT.value(), "Data integrity violation",
                errors);

        return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
    }
}
