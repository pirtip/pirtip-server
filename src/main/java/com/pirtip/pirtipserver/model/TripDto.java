/*
 * TripDto.java 2024. 02. 13
 *
 * Copyright 2024 NAVER Corp. All rights Reserved.
 * NAVER PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.pirtip.pirtipserver.model;

import java.time.LocalDate;

import com.pirtip.pirtipserver.entity.Position;
import com.pirtip.pirtipserver.entity.Trip;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Builder
@Getter
public class TripDto {

	private Long id;
	private Long createdBy;
	private String title;
	private String content;
	private LocalDate beginDate;
	private LocalDate endDate;
	private Position position;

	public static TripDto fromTrip(Trip trip) {
		return TripDto.builder()
			.id(trip.getId())
			.createdBy(trip.getCreatedBy())
			.title(trip.getTitle())
			.content(trip.getContent())
			.beginDate(trip.getBeginDate())
			.endDate(trip.getEndDate())
			.position(trip.getPosition())
			.build();
	}
}
