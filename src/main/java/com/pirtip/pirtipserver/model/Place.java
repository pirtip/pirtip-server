package com.pirtip.pirtipserver.model;

import com.pirtip.pirtipserver.entity.Position;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Builder
@Getter
@ToString
public class Place {

	private String title;
	private String link;
	private String address;
	private Position position;
}
