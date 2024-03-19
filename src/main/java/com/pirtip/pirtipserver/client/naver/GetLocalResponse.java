package com.pirtip.pirtipserver.client.naver;

import java.math.BigDecimal;
import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.pirtip.pirtipserver.entity.Position;
import com.pirtip.pirtipserver.model.Place;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@RequiredArgsConstructor
@Getter
@ToString
public class GetLocalResponse {

	private final int total;
	private final int start;
	private final int display;
	private final List<Item> items;

	@Getter
	@ToString
	public static class Item {

		private final String title;
		private final String link;
		private final String category;
		private final String description;
		private final String telephone;
		private final String address;
		private final String roadAddress;
		private final BigDecimal mapx;
		private final BigDecimal mapy;

		public Item(
			String title,
			String link,
			String category,
			String description,
			String telephone,
			String address,
			String roadAddress,
			@JsonDeserialize(using = ScaleAdjustDeserializer.class) BigDecimal mapx,
			@JsonDeserialize(using = ScaleAdjustDeserializer.class) BigDecimal mapy
		) {
			this.title = title;
			this.link = link;
			this.category = category;
			this.description = description;
			this.telephone = telephone;
			this.address = address;
			this.roadAddress = roadAddress;
			this.mapx = mapx;
			this.mapy = mapy;
		}

		public static Place toPlace(Item item) {
			return Place.builder()
				.title(item.getTitle())
				.link(item.getLink())
				.address(item.getAddress())
				.position(
					Position.builder()
						.latitude(item.getMapy())
						.longitude(item.getMapx())
						.build()
				)
				.build();
		}
	}
}
