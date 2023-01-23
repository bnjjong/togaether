/*
 * Copyright (C) 2018-2022 Bigin(https://bigin.io/main) - All Rights Reserved.
 *
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 *
 * Written by dev team, henry<jognsang@bigin.io>
 * Last modified on 2022/3/31
 */

package io.ddd.togaether.config;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ConstraintViolationException;
import java.net.BindException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

/**
 * create on 2021/07/19. create by IntelliJ IDEA.
 *
 * <pre>
 * Spring controller exception advice handler.
 * Most of all exception that client call wrong apis is handled here.
 * </pre>
 *
 * @author Henry
 * @version 1.0
 * @since 1.0
 */
@RestControllerAdvice
@Slf4j
public class GlobalControllerExceptionHandler {

  /**
   * <pre>
   *  occur error by Valid annotation.
   * </pre>
   *
   * @param e {@code MethodArgumentNotValidException} exception.
   * @return {@code ResponseCommonEntity} with error code and message.
   */
  @ExceptionHandler(MethodArgumentNotValidException.class)
  @Order(1)
  protected ResponseEntity<String> handleValidException(MethodArgumentNotValidException e) {
    log.error("MethodArgumentNotValidException Exception >>>> {}", e.getClass().getName());
    String errorMsg = e.getBindingResult().getAllErrors().get(0).getDefaultMessage();
    return new ResponseEntity<>(errorMsg, HttpStatus.BAD_REQUEST);
  }

  /**
   * <pre>
   *   occur error when binding model by @ModelAttribute.
   * </pre>
   *
   * <a
   * href="https://docs.spring.io/spring/docs/current/spring-framework-reference/web.html#mvc-ann-modelattrib-method-args">
   * reference </a>
   *
   * @param e {@code BindException} exception.
   * @return {@code ResponseCommonEntity} with error code and message.
   */
  @ExceptionHandler(BindException.class)
  @Order(1)
  protected ResponseEntity<String> handleBindException(BindException e) {
    log.error("handleBindException", e);
    String errorMsg = e.getMessage();
    return new ResponseEntity<>(errorMsg, HttpStatus.BAD_REQUEST);
  }


  /**
   * {@code @RequestMapping}으로 지정한 필수 request parameter가 누락되었을 때.
   *
   * @param e {@link MissingServletRequestParameterException}
   * @return {@link ResponseEntity}
   * @see <a
   *     href="https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/web/bind/MissingServletRequestParameterException.html">MissingServletRequestParameterException</a>
   */
  @ExceptionHandler(MissingServletRequestParameterException.class)
  @Order(1)
  protected ResponseEntity<String> handleRequestParamException(
      MissingServletRequestParameterException e) {
    log.error("handleRequestParamException", e);
    String errorMsg = e.getMessage();
    return new ResponseEntity<>(errorMsg, HttpStatus.BAD_REQUEST);
  }


  /**
   * {@code @RequestMapping}으로 지정한 필수 request header가 누락되었을 때.
   *
   * @param e {@link MissingRequestHeaderException}
   * @return {@link ResponseEntity}
   * @see <a
   *     href="https://docs.spring.io/spring-framework/docs/current/javadoc-api/index.html?org/springframework/web/bind/MissingServletRequestParameterException.html">MissingRequestHeaderException</a>
   */
  @ExceptionHandler(MissingRequestHeaderException.class)
  @Order(1)
  protected ResponseEntity<String> handleRequestHeaderException(MissingRequestHeaderException e) {
    log.error("handleRequestHeaderException", e);
    String errorMsg = e.getMessage();
    return new ResponseEntity<>(errorMsg, HttpStatus.BAD_REQUEST);
  }

  /**
   * package의 annotation으로 지정한 request의 validation이 통과되지 못할 때.
   *
   * @param e {@link ConstraintViolationException}
   * @return {@link ResponseEntity}
   * @see <a
   *     href="https://docs.oracle.com/javaee/7/api/javax/validation/constraints/package-summary.html">javax.validation.constraints</a>
   */
  @ExceptionHandler(ConstraintViolationException.class)
  @Order(1)
  protected ResponseEntity<String> handleConstraintViolationException(
      ConstraintViolationException e) {
    log.error("handleConstraintViolationException", e);
    System.out.println(e.getStackTrace());

    String errorMsg = e.getMessage();
    return new ResponseEntity<>(errorMsg, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(NoHandlerFoundException.class)
  @Order(1)
  protected ResponseEntity<String> handleNoHandlerFoundException(NoHandlerFoundException e) {
    log.error("handleConstraintViolationException", e);

    String errorMsg = e.getMessage();
    return new ResponseEntity<>(errorMsg, HttpStatus.NOT_FOUND);
  }




  @ExceptionHandler(EntityNotFoundException.class)
  @Order(1)
  protected ResponseEntity<String> handleEntityNotFoundException(EntityNotFoundException e) {
    log.error("entity not Exception >>>> {}", e.getClass().getName());

    String errorMsg = e.getMessage();
    return new ResponseEntity<>(errorMsg, HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(IllegalStateException.class)
  @Order(1)
  protected ResponseEntity<String> handleIllegalStateException(IllegalStateException e) {
    log.error("IllegalStateException >>>> {}", e.getClass().getName());

    String errorMsg = e.getMessage();
    return new ResponseEntity<>(errorMsg, HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(SecurityException.class)
  @Order(1)
  protected ResponseEntity<String> handleSecurityException(SecurityException e) {
    log.error("Security Exception >>>> {}", e.getClass().getName());
    
    String errorMsg = e.getMessage();
    return new ResponseEntity<>(errorMsg, HttpStatus.NOT_FOUND);
  }


  @ExceptionHandler(Exception.class)
  @Order(9)
  protected ResponseEntity<String> handleUncaughtException(Exception e) {
    log.error("Uncaught Exception >>>> {}", e.getClass().getName());
    e.printStackTrace();

    String errorMsg = e.getMessage();
    return new ResponseEntity<>(errorMsg, HttpStatus.INTERNAL_SERVER_ERROR);
  }

}
