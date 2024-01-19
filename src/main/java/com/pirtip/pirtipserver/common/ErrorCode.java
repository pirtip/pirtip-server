package com.pirtip.pirtipserver.common;

import com.fasterxml.jackson.annotation.JsonValue;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Getter
@Slf4j
public enum ErrorCode {

	DUPLICATE_ERROR("000", "Duplicate data", 409, ErrorLevel.WARN),
	USER_NOT_FOUND("001", "User is not found", 404, ErrorLevel.WARN);

	private final String code;
	private final String message;
	private final int httpStatusCode;
	private final ErrorLevel level;

	@JsonValue
	public String getCode() {
		return code;
	}

	@RequiredArgsConstructor
	@Getter
	public enum ErrorLevel {

		ERROR(40000),
		WARN(30000),
		INFO(20000),
		DEBUG(10000),
		TRACE(5000);

		private final int level;
	}
}
