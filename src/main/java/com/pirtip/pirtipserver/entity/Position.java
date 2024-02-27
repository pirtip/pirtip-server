package com.pirtip.pirtipserver.entity;

import java.math.BigDecimal;
import java.util.Optional;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Getter
public class Position {

	@Column(name = "latitude", columnDefinition = "DECIMAL(10,8)")
	private BigDecimal latitude;

	@Column(name = "longitude", columnDefinition = "DECIMAL(10,8)")
	private BigDecimal longitude;

	@Override
	@JsonValue
	public String toString() {
		return latitude + "," + longitude;
	}

	@JsonCreator
	public static Position from(String p) {
		String[] latLon = p.split(",");
		if (latLon.length != 2) {
			throw new IllegalArgumentException("Wrong position argument! (argument=" + p + ")");
		}
		BigDecimal lat = BigDecimal.valueOf(Double.parseDouble(latLon[0]));
		BigDecimal lon = BigDecimal.valueOf(Double.parseDouble(latLon[1]));
		return Position.builder()
			.latitude(lat)
			.longitude(lon)
			.build();
	}
}
