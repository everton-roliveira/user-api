package br.com.userapi.userapi.presentation.exception;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.context.request.WebRequest;

@Component
public class UnprocessableEntityException {

    public ResponseEntity<Object> handleValidationException(MethodArgumentNotValidException ex,
            HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<DetailResponse> errors = new ArrayList<DetailResponse>();
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            DetailResponse detail = DetailResponse.builder()
            .field(error.getField())
            .message(error.getDefaultMessage())
            .build();

            if (error.getRejectedValue() instanceof String) {
                detail.setValue((String)error.getRejectedValue());
            }

            errors.add(detail);
        }
        for (ObjectError error : ex.getBindingResult().getGlobalErrors()) {
            
            errors.add(DetailResponse.builder()
                    .field(null)
                    .value(null)
                    .message(error.getDefaultMessage())
                    .build());
        }

        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.UNPROCESSABLE_ENTITY.value(),
                "Validation failed", errors);

        return new ResponseEntity<>(errorResponse, HttpStatus.UNPROCESSABLE_ENTITY);
    }
}