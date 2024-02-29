package com.pirtip.pirtipserver.model;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.pirtip.pirtipserver.entity.Position;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class CreateTripRequest {

	private String title;

	private String content;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private LocalDate beginDate;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private LocalDate endDate;

	private Position position;
}
