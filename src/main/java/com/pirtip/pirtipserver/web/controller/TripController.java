package com.pirtip.pirtipserver.web.controller;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pirtip.pirtipserver.model.CreateTripPlanRequest;
import com.pirtip.pirtipserver.model.CreateTripRequest;
import com.pirtip.pirtipserver.model.ReadTripRequest;
import com.pirtip.pirtipserver.model.TripDto;
import com.pirtip.pirtipserver.model.TripPlanDto;
import com.pirtip.pirtipserver.service.TripPlanService;
import com.pirtip.pirtipserver.service.TripService;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/trip")
@RequiredArgsConstructor
public class TripController {

	private final TripService tripService;
	private final TripPlanService tripPlanService;

	@PostMapping
	public ResponseEntity<TripDto> createTrip(
		@RequestBody CreateTripRequest body
	) {
		TripDto trip = tripService.createTrip(1L, body);
		return ResponseEntity.ok(trip);
	}

	@GetMapping("/{tripId}")
	public ResponseEntity<TripDto> getTripById(
		@PathVariable long tripId
	) {
		TripDto trip = tripService.getTripById(1L, tripId);
		return ResponseEntity.ok(trip);
	}

	@GetMapping
	@Parameters({
		@Parameter(name = "page", in = ParameterIn.QUERY, example = "0"),
		@Parameter(name = "size", in = ParameterIn.QUERY, example = "2147483646"),
		@Parameter(name = "sort", in = ParameterIn.QUERY, examples = {
			@ExampleObject(name = "여행일자 내림차순", value = "beginDate,desc"),
			@ExampleObject(name = "여행일자 오름차순", value = "beginDate,asc"),
			@ExampleObject(name = "생성 내림차순", value = "id,desc"),
			@ExampleObject(name = "생성 오름차순", value = "id,asc")
		})
	})
	public ResponseEntity<Slice<TripDto>> getTrips(
		@Parameter(hidden = true) ReadTripRequest param,
		@Parameter(hidden = true) @PageableDefault(size = Integer.MAX_VALUE, page = 0, sort = "beginDate", direction = Sort.Direction.ASC) Pageable pageable
	) {
		Slice<TripDto> trips = tripService.getTrips(1L, param, pageable);
		return ResponseEntity.ok(trips);
	}

	@PostMapping("/{tripId}/plan")
	public ResponseEntity<TripPlanDto> createTripPlan(
		@PathVariable long tripId,
		@RequestBody CreateTripPlanRequest body
	) {
		TripPlanDto plan = tripPlanService.createTripPlan(1L, tripId, body);
		return ResponseEntity.ok(plan);
	}

	@GetMapping("/{tripId}/plan")
	@Parameters({
		@Parameter(name = "page", in = ParameterIn.QUERY, example = "0"),
		@Parameter(name = "size", in = ParameterIn.QUERY, example = "2147483646"),
		@Parameter(name = "sort", in = ParameterIn.QUERY, examples = {
			@ExampleObject(name = "일정 내림차순", value = "plannedAt,desc"),
			@ExampleObject(name = "일정 오름차순", value = "plannedAt,asc"),
			@ExampleObject(name = "생성 내림차순", value = "id,desc"),
			@ExampleObject(name = "생성 오름차순", value = "id,asc")
		})
	})
	public ResponseEntity<Slice<TripPlanDto>> getTripPlans(
		@PathVariable long tripId,
		@Parameter(hidden = true) @PageableDefault(size = Integer.MAX_VALUE, page = 0, sort = "plannedAt", direction = Sort.Direction.ASC) Pageable pageable
	) {
		Slice<TripPlanDto> plans = tripPlanService.getTripPlans(1L, tripId, pageable);
		return ResponseEntity.ok(plans);
	}

	@DeleteMapping("/{tripId}")
	public ResponseEntity<?> deleteTrip(
		@PathVariable long tripId
	) {
		tripService.deleteTrip(1L, tripId);
		return ResponseEntity.ok(null);
	}

	@PutMapping("/{tripId}")
	public ResponseEntity<?> replaceTrip(
		@PathVariable long tripId,
		@RequestBody CreateTripRequest body
	) {
		tripService.replaceTrip(1L, tripId, body);
		return ResponseEntity.ok(null);
	}
}
