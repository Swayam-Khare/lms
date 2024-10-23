package com.ss.lms.exception;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.security.SignatureException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.util.*;
import java.util.logging.Logger;

@RestControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger log = Logger.getLogger("Global Exception Handler");

    @ExceptionHandler( AccessDeniedException.class)
    public ResponseEntity<CustomErrorResponse> handleAccessDeniedException( AccessDeniedException e)
    {
        CustomErrorResponse customError = new CustomErrorResponse(403, e.getMessage(), new Date(System.currentTimeMillis()));
        return new ResponseEntity<>(customError, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<CustomErrorResponse> handleUsernameNotFoundException(UsernameNotFoundException e) {
        log.info("In user not found exception handler");
        CustomErrorResponse customError = new CustomErrorResponse(404, e.getMessage(), new Date(System.currentTimeMillis()));
        return new ResponseEntity<>(customError, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(CustomEntityNotFoundException.class)
    public ResponseEntity<CustomErrorResponse> handleEntityNotFoundException(CustomEntityNotFoundException e) {
        CustomErrorResponse customError = new CustomErrorResponse(404,e.getMessage(), new Date(System.currentTimeMillis()));
        return new ResponseEntity<>(customError, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MalformedJwtException.class)
    public ResponseEntity<CustomErrorResponse> handleMalformedJwtException(MalformedJwtException e) {
        CustomErrorResponse customError = new CustomErrorResponse(401,e.getMessage(), new Date(System.currentTimeMillis()));
        return new ResponseEntity<>(customError, HttpStatus.UNAUTHORIZED);

    }

    @ExceptionHandler(SignatureException.class)
    public ResponseEntity<CustomErrorResponse> handleSignatureException(SignatureException e) {
        CustomErrorResponse customError = new CustomErrorResponse(401,e.getMessage(), new Date(System.currentTimeMillis()));
        return new ResponseEntity<>(customError, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<CustomErrorResponse> handleBadCredentialException(BadCredentialsException e) {
        log.info("In Bad Credentials exception handler");
        CustomErrorResponse customError = new CustomErrorResponse(401, "Invalid Email or Password", new Date(System.currentTimeMillis()));
        return new ResponseEntity<>(customError, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(ExpiredJwtException.class)
    public ResponseEntity<CustomErrorResponse> handleExpiredJwtException(ExpiredJwtException e) {
        CustomErrorResponse customError = new CustomErrorResponse(401,e.getMessage(), new Date(System.currentTimeMillis()));
        return new ResponseEntity<>(customError, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<CustomErrorResponse> handleNoResourceFoundException(NoResourceFoundException e) {
        CustomErrorResponse customError = new CustomErrorResponse(404,e.getMessage(), new Date(System.currentTimeMillis()));
        return new ResponseEntity<>(customError, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> notValid(MethodArgumentNotValidException ex, HttpServletRequest request) {
        List<String> errors = new ArrayList<>();

        ex.getAllErrors().forEach(err -> errors.add(err.getDefaultMessage()));

        Map<String, List<String>> result = new HashMap<>();
        result.put("errors", errors);

        return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<CustomErrorResponse> handleException(Exception e) {
        CustomErrorResponse customError = new CustomErrorResponse(500,e.getMessage(), new Date(System.currentTimeMillis()));
        return new ResponseEntity<>(customError, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
