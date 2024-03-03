package com.pirtip.pirtipserver.web.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pirtip.pirtipserver.model.CreateTripPlanRequest;
import com.pirtip.pirtipserver.model.TripPlanDto;
import com.pirtip.pirtipserver.service.TripPlanService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/plan")
@RequiredArgsConstructor
public class TripPlanController {

	private final TripPlanService tripPlanService;

	@GetMapping("/{planId}")
	public ResponseEntity<TripPlanDto> getTripPlanById(@PathVariable long planId) {
		TripPlanDto plan = tripPlanService.getTripPlan(1L, planId);
		return ResponseEntity.ok(plan);
	}

	@DeleteMapping("/{planId}")
	public ResponseEntity<?> deleteTripPlanById(@PathVariable long planId) {
		tripPlanService.deleteTripPlan(1L, planId);
		return ResponseEntity.ok(null);
	}

	@PutMapping("/{planId}")
	public ResponseEntity<?> replaceTripPlanById(
		@PathVariable long planId,
		@RequestBody CreateTripPlanRequest body
	) {
		tripPlanService.replaceTripPlan(1L, planId, body);
		return ResponseEntity.ok(null);
	}
}
