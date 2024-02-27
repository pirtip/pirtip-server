package com.pirtip.pirtipserver.model;

import java.time.LocalDate;

import com.pirtip.pirtipserver.entity.Position;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class CreateTripDto {

	private String title;
	private String content;
	private LocalDate beginDate;
	private LocalDate endDate;
	private Position position;
}
