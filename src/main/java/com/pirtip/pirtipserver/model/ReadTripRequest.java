package com.pirtip.pirtipserver.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@ToString
public class ReadTripRequest {

	@Setter
	private Long userId;
	private Integer page;
	private Integer size;

	@Builder
	public ReadTripRequest(Long userId, Integer page, Integer size) {
		this.userId = userId;
		this.page = page == null ? 0 : page;
		this.size = size == null ? 10 : size;
	}
}
