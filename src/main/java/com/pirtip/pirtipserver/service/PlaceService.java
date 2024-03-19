package com.pirtip.pirtipserver.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;
import org.springframework.stereotype.Service;

import com.pirtip.pirtipserver.client.naver.GetLocalResponse;
import com.pirtip.pirtipserver.client.naver.NaverOpenApiClient;
import com.pirtip.pirtipserver.model.Place;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PlaceService {

	private final NaverOpenApiClient naverOpenApiClient;

	public Slice<Place> getPlaces(String query, Pageable pageable) {
		GetLocalResponse response = naverOpenApiClient.getLocal(query, pageable.getPageSize());
		List<Place> places = response.getItems()
			.stream()
			.map(GetLocalResponse.Item::toPlace)
			.collect(Collectors.toList());
		return new SliceImpl<>(places);
	}
}
