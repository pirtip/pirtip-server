package com.pirtip.pirtipserver.web.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.pirtip.pirtipserver.common.BusinessException;
import com.pirtip.pirtipserver.model.ErrorResponse;

import lombok.extern.slf4j.Slf4j;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

	@ExceptionHandler(BusinessException.class)
	public ResponseEntity<ErrorResponse> handleBusinessException(BusinessException e) {
		switch (e.getErrorCode().getLevel()) {
			case ERROR:
				log.error("BusinessException occurred (errorCode={})", e.getErrorCode().getCode(), e);
				break;
			case WARN:
				log.warn("BusinessException occurred (errorCode={})", e.getErrorCode().getCode(), e);
				break;
			case INFO:
				log.info("BusinessException occurred (errorCode={})", e.getErrorCode().getCode(), e);
				break;
			case DEBUG:
				log.debug("BusinessException occurred (errorCode={})", e.getErrorCode().getCode(), e);
				break;
			case TRACE:
				log.trace("BusinessException occurred (errorCode={})", e.getErrorCode().getCode(), e);
				break;
			default:
		}

		ErrorResponse errorResponse = ErrorResponse.builder()
			.errorCode(e.getErrorCode())
			.message(e.getErrorCode().getMessage())
			.build();
		return ResponseEntity.status(e.getErrorCode().getHttpStatusCode())
			.body(errorResponse);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<?> handleException(Exception e) {
		log.error("Unhandled exception occurred", e);
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
			.build();
	}
}
