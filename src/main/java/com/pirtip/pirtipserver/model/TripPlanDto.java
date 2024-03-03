package com.pirtip.pirtipserver.model;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.pirtip.pirtipserver.entity.Position;
import com.pirtip.pirtipserver.entity.TripPlan;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Builder
@Getter
public class TripPlanDto {

	private Long id;
	private Long tripId;
	private Long createdBy;
	private String content;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
	private LocalDateTime plannedAt;
	private Position position;

	public static TripPlanDto fromTripPlan(TripPlan plan) {
		return TripPlanDto.builder()
			.id(plan.getId())
			.tripId(plan.getTripId())
			.createdBy(plan.getCreatedBy())
			.content(plan.getContent())
			.plannedAt(plan.getPlannedAt())
			.position(plan.getPosition())
			.build();
	}
}
