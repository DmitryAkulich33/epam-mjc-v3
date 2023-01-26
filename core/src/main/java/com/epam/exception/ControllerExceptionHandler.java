package com.epam.exception;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolationException;

@AllArgsConstructor
@ControllerAdvice
@Slf4j
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler {
    private final LocaleTranslator localeTranslator;

    @ExceptionHandler(PaginationException.class)
    public ResponseEntity<Object> handlePaginationException(PaginationException exception) {
        String errorCode = String.format("%s%s", HttpStatus.BAD_REQUEST.value(), ErrorCode.VALIDATION_ERROR_CODE.getErrorCode());
        return getResponseEntity(exception, errorCode, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Object> handleConstraintViolationException(ConstraintViolationException exception) {
        String errorCode = String.format("%s%s", HttpStatus.BAD_REQUEST.value(), ErrorCode.VALIDATION_ERROR_CODE.getErrorCode());
        return getResponseEntityWithCommonMessage(exception, errorCode, HttpStatus.BAD_REQUEST, "data.not.valid");
    }

    @ExceptionHandler(TagNotFoundException.class)
    public ResponseEntity<Object> handleTagNotFoundException(TagNotFoundException exception) {
        String errorCode = String.format("%s%s", HttpStatus.NOT_FOUND.value(), ErrorCode.TAG_DAO_ERROR_CODE.getErrorCode());
        return getResponseEntity(exception, errorCode, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(TagAlreadyExistsException.class)
    public ResponseEntity<Object> handleTagAlreadyExistsException(TagAlreadyExistsException exception) {
        String errorCode = String.format("%s%s", HttpStatus.BAD_REQUEST.value(), ErrorCode.VALIDATION_ERROR_CODE.getErrorCode());
        return getResponseEntity(exception, errorCode, HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException exception, HttpHeaders headers,
                                                                  HttpStatus status, WebRequest request) {
        String errorCode = String.format("%s%s", HttpStatus.BAD_REQUEST.value(), ErrorCode.VALIDATION_ERROR_CODE.getErrorCode());
        return getResponseEntityWithCommonMessage(exception, errorCode, HttpStatus.BAD_REQUEST, "data.not.valid");
    }


    private ResponseEntity<Object> getResponseEntity(ServiceException exception, String errorCode, HttpStatus httpStatus) {
        String message = localeTranslator.getString(exception.getMessage(), exception.getArgs());
        log.error(message, exception);
        ExceptionResponse error = new ExceptionResponse(message, errorCode);
        return new ResponseEntity<>(error, new HttpHeaders(), httpStatus);
    }

    private ResponseEntity<Object> getResponseEntityWithCommonMessage(Throwable exception, String errorCode, HttpStatus httpStatus,
                                                                      String commonMessage) {
        String message = localeTranslator.getString(commonMessage);
        log.error(message, exception);
        ExceptionResponse error = new ExceptionResponse(message, errorCode);
        return new ResponseEntity<>(error, new HttpHeaders(), httpStatus);
    }
}
