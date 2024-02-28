package com.pirtip.pirtipserver.web.controller;

import org.springframework.data.domain.Slice;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pirtip.pirtipserver.model.CreateTripDto;
import com.pirtip.pirtipserver.model.CreateTripPlanDto;
import com.pirtip.pirtipserver.model.ReadTripRequest;
import com.pirtip.pirtipserver.model.TripDto;
import com.pirtip.pirtipserver.model.TripPlanDto;
import com.pirtip.pirtipserver.service.TripPlanService;
import com.pirtip.pirtipserver.service.TripService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/trip")
@RequiredArgsConstructor
public class TripController {

	private final TripService tripService;
	private final TripPlanService tripPlanService;

	@PostMapping
	public ResponseEntity<TripDto> createTrip(
		@RequestBody CreateTripDto body
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
	public ResponseEntity<Slice<TripDto>> getTrips(
		ReadTripRequest param
	) {
		Slice<TripDto> trips = tripService.getTrips(1L, param);
		return ResponseEntity.ok(trips);
	}

	@PostMapping("/{tripId}/plan")
	public ResponseEntity<TripPlanDto> createTripPlan(
		@PathVariable long tripId,
		@RequestBody CreateTripPlanDto body
	) {
		TripPlanDto plan = tripPlanService.createTripPlan(1L, tripId, body);
		return ResponseEntity.ok(plan);
	}
}
