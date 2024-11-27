package com.crm.exception;

import com.crm.payload.ErrorHandle;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@ControllerAdvice
public class HandleException {
    @ExceptionHandler(ResourceNotFound.class)

    public ResponseEntity<ErrorHandle> handleResourceNotFound(ResourceNotFound e, WebRequest request) {

        ErrorHandle errorHandle = new ErrorHandle(

                new Date(),
                e.getMessage(),
                request.getDescription(true)
        );
        return new ResponseEntity<>(errorHandle, HttpStatus.INTERNAL_SERVER_ERROR);
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorHandle> handleResourceNotFound(Exception e, WebRequest request) {

        ErrorHandle errorHandle = new ErrorHandle(

                new Date(),
                e.getMessage(),
                       request.getDescription(true));
        return new ResponseEntity<>(errorHandle, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
