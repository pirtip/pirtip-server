package com.pirtip.pirtipserver.web.controller;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pirtip.pirtipserver.model.Place;
import com.pirtip.pirtipserver.service.PlaceService;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/place")
@RequiredArgsConstructor
public class PlaceController {

	private final PlaceService placeService;

	@GetMapping
	@Parameters({
		@Parameter(name = "query", in = ParameterIn.QUERY, example = "인하대학교"),
		@Parameter(name = "page", in = ParameterIn.QUERY, example = "0"),
		@Parameter(name = "size", in = ParameterIn.QUERY, example = "2147483646"),
	})
	public ResponseEntity<?> getPlaces(
		@RequestParam String query,
		@Parameter(hidden = true) @PageableDefault(size = Integer.MAX_VALUE, page = 0) Pageable pageable
	) {
		Slice<Place> places = placeService.getPlaces(query, pageable);
		return ResponseEntity.ok(places);
	}
}
