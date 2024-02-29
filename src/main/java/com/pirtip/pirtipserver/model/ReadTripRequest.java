package com.pirtip.pirtipserver.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@Builder
@Getter
@ToString
public class ReadTripRequest {

	@Setter
	private Long userId;
}
