package com.pirtip.pirtipserver.model;

import com.pirtip.pirtipserver.common.ErrorCode;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Builder
@Getter
public class ErrorResponse {

	private final ErrorCode errorCode;
	private final String message;
}
