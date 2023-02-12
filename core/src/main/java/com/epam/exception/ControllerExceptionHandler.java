package com.epam.exception;

import lombok.AllArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolationException;

@AllArgsConstructor
@ControllerAdvice
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler {
    private final LocaleTranslator localeTranslator;

    private static final Logger logger = LogManager.getLogger();

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
        String errorCode = String.format("%s%s", HttpStatus.NOT_FOUND.value(), ErrorCode.TAG_ERROR_CODE.getErrorCode());
        return getResponseEntity(exception, errorCode, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(TagAlreadyExistsException.class)
    public ResponseEntity<Object> handleTagAlreadyExistsException(TagAlreadyExistsException exception) {
        String errorCode = String.format("%s%s", HttpStatus.BAD_REQUEST.value(), ErrorCode.VALIDATION_ERROR_CODE.getErrorCode());
        return getResponseEntity(exception, errorCode, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(AuthorNotFoundException.class)
    public ResponseEntity<Object> handleAuthorNotFoundException(AuthorNotFoundException exception) {
        String errorCode = String.format("%s%s", HttpStatus.NOT_FOUND.value(), ErrorCode.AUTHOR_ERROR_CODE.getErrorCode());
        return getResponseEntity(exception, errorCode, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(AuthorAlreadyExistsException.class)
    public ResponseEntity<Object> handleAuthorAlreadyExistsException(AuthorAlreadyExistsException exception) {
        String errorCode = String.format("%s%s", HttpStatus.BAD_REQUEST.value(), ErrorCode.VALIDATION_ERROR_CODE.getErrorCode());
        return getResponseEntity(exception, errorCode, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CommentNotFoundException.class)
    public ResponseEntity<Object> handleCommentNotFoundException(CommentNotFoundException exception) {
        String errorCode = String.format("%s%s", HttpStatus.NOT_FOUND.value(), ErrorCode.COMMENT_ERROR_CODE.getErrorCode());
        return getResponseEntity(exception, errorCode, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NewsNotFoundException.class)
    public ResponseEntity<Object> handleNewsNotFoundException(NewsNotFoundException exception) {
        String errorCode = String.format("%s%s", HttpStatus.NOT_FOUND.value(), ErrorCode.NEWS_ERROR_CODE.getErrorCode());
        return getResponseEntity(exception, errorCode, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NewsAlreadyExistsException.class)
    public ResponseEntity<Object> handleNewsAlreadyExistsException(NewsAlreadyExistsException exception) {
        String errorCode = String.format("%s%s", HttpStatus.BAD_REQUEST.value(), ErrorCode.VALIDATION_ERROR_CODE.getErrorCode());
        return getResponseEntity(exception, errorCode, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleException(Exception exception) {
        String errorCode = String.format("%s%s", HttpStatus.INTERNAL_SERVER_ERROR.value(), ErrorCode.SERVER_ERROR_CODE.getErrorCode());
        return getResponseEntityWithCommonMessage(exception, errorCode, HttpStatus.INTERNAL_SERVER_ERROR, "server.error");
    }

    @ExceptionHandler(RoleNotFoundException.class)
    public ResponseEntity<Object> handleRoleNotFoundException(RoleNotFoundException exception) {
        String errorCode = String.format("%s%s", HttpStatus.NOT_FOUND.value(), ErrorCode.ROLE_ERROR_CODE.getErrorCode());
        return getResponseEntity(exception, errorCode, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<Object> handleUserAlreadyExistsException(UserAlreadyExistsException exception) {
        String errorCode = String.format("%s%s", HttpStatus.BAD_REQUEST.value(), ErrorCode.VALIDATION_ERROR_CODE.getErrorCode());
        return getResponseEntity(exception, errorCode, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Object> handleUserNotFoundException(UserNotFoundException exception) {
        String errorCode = String.format("%s%s", HttpStatus.NOT_FOUND.value(), ErrorCode.USER_ERROR_CODE.getErrorCode());
        return getResponseEntity(exception, errorCode, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(AuthenticationDataException.class)
    public ResponseEntity<Object> handleAuthenticationDataException(AuthenticationDataException exception) {
        String errorCode = String.format("%s%s", HttpStatus.UNAUTHORIZED.value(), ErrorCode.USER_ERROR_CODE.getErrorCode());
        return getResponseEntity(exception, errorCode, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(AuthenticationCredentialsNotFoundException.class)
    public ResponseEntity<Object> handleAuthenticationCredentialsNotFoundException(AuthenticationCredentialsNotFoundException exception) {
        String errorCode = String.format("%s%s", HttpStatus.UNAUTHORIZED.value(), ErrorCode.USER_ERROR_CODE.getErrorCode());
        return getResponseEntityWithCommonMessage(exception, errorCode, HttpStatus.UNAUTHORIZED, "access.denied");
    }

    @ExceptionHandler(JwtAuthenticationException.class)
    public ResponseEntity<Object> handleUserJwtAuthenticationException(JwtAuthenticationException exception) {
        String errorCode = String.format("%s%s", HttpStatus.UNAUTHORIZED.value(), ErrorCode.USER_ERROR_CODE.getErrorCode());
        return getResponseEntity(exception, errorCode, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<Object> handleAccessDeniedException(AccessDeniedException exception) {
        String errorCode = String.format("%s%s", HttpStatus.FORBIDDEN.value(), ErrorCode.USER_ERROR_CODE.getErrorCode());
        return getResponseEntityWithCommonMessage(exception, errorCode, HttpStatus.FORBIDDEN, "access.denied");
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException exception, HttpHeaders headers,
                                                                  HttpStatus status, WebRequest request) {
        String errorCode = String.format("%s%s", HttpStatus.BAD_REQUEST.value(), ErrorCode.VALIDATION_ERROR_CODE.getErrorCode());
        return getResponseEntityWithCommonMessage(exception, errorCode, HttpStatus.BAD_REQUEST, "data.not.valid");
    }

    @Override
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex,
                                                                         HttpHeaders headers, HttpStatus status, WebRequest request) {
        String errorCode = String.format("%s%s", HttpStatus.METHOD_NOT_ALLOWED.value(), ErrorCode.REQUEST_ERROR_CODE.getErrorCode());
        return getResponseEntityWithCommonMessage(ex, errorCode, HttpStatus.METHOD_NOT_ALLOWED, "method.not.allowed");
    }

    @Override
    protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(HttpMediaTypeNotSupportedException ex, HttpHeaders headers,
                                                                     HttpStatus status, WebRequest request) {
        String errorCode = String.format("%s%s", HttpStatus.UNSUPPORTED_MEDIA_TYPE.value(), ErrorCode.REQUEST_ERROR_CODE.getErrorCode());
        return getResponseEntityWithCommonMessage(ex, errorCode, HttpStatus.UNSUPPORTED_MEDIA_TYPE, "media.not.supported");

    }

    @Override
    protected ResponseEntity<Object> handleHttpMediaTypeNotAcceptable(HttpMediaTypeNotAcceptableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        String errorCode = String.format("%s%s", HttpStatus.NOT_ACCEPTABLE.value(), ErrorCode.REQUEST_ERROR_CODE.getErrorCode());
        return getResponseEntityWithCommonMessage(ex, errorCode, HttpStatus.NOT_ACCEPTABLE, "media.not.acceptable");
    }

    @Override
    protected ResponseEntity<Object> handleMissingPathVariable(MissingPathVariableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        String errorCode = String.format("%s%s", HttpStatus.BAD_REQUEST.value(), ErrorCode.REQUEST_ERROR_CODE.getErrorCode());
        return getResponseEntityWithCommonMessage(ex, errorCode, HttpStatus.BAD_REQUEST, "missing.path.variable");
    }

    @Override
    protected ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        String errorCode = String.format("%s%s", HttpStatus.BAD_REQUEST.value(), ErrorCode.REQUEST_ERROR_CODE.getErrorCode());
        return getResponseEntityWithCommonMessage(ex, errorCode, HttpStatus.BAD_REQUEST, "missing.servlet.request.param");
    }

    @Override
    protected ResponseEntity<Object> handleTypeMismatch(TypeMismatchException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        String errorCode = String.format("%s%s", HttpStatus.BAD_REQUEST.value(), ErrorCode.REQUEST_ERROR_CODE.getErrorCode());
        return getResponseEntityWithCommonMessage(ex, errorCode, HttpStatus.BAD_REQUEST, "type.mismatch");
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        String errorCode = String.format("%s%s", HttpStatus.BAD_REQUEST.value(), ErrorCode.REQUEST_ERROR_CODE.getErrorCode());
        return getResponseEntityWithCommonMessage(ex, errorCode, HttpStatus.BAD_REQUEST, "not.readable");
    }

    private ResponseEntity<Object> getResponseEntity(ServiceException exception, String errorCode, HttpStatus httpStatus) {
        String message = localeTranslator.getString(exception.getMessage(), exception.getArgs());
        logger.error(message, exception);
        ExceptionResponse error = new ExceptionResponse(message, errorCode);
        return new ResponseEntity<>(error, new HttpHeaders(), httpStatus);
    }

    private ResponseEntity<Object> getResponseEntityWithCommonMessage(Throwable exception, String errorCode, HttpStatus httpStatus,
                                                                      String commonMessage) {
        String message = localeTranslator.getString(commonMessage);
        logger.error(message, exception);
        ExceptionResponse error = new ExceptionResponse(message, errorCode);
        return new ResponseEntity<>(error, new HttpHeaders(), httpStatus);
    }
}
