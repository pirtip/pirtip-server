package com.pirtip.pirtipserver.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pirtip.pirtipserver.common.BusinessException;
import com.pirtip.pirtipserver.common.ErrorCode;
import com.pirtip.pirtipserver.entity.TripPlan;
import com.pirtip.pirtipserver.model.CreateTripPlanRequest;
import com.pirtip.pirtipserver.model.TripPlanDto;
import com.pirtip.pirtipserver.repository.TripPlanRepository;
import com.pirtip.pirtipserver.repository.TripRepository;
import com.pirtip.pirtipserver.repository.UserAccountRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TripPlanService {

	private final UserAccountRepository userAccountRepository;
	private final TripRepository tripRepository;
	private final TripPlanRepository tripPlanRepository;

	@Transactional
	public TripPlanDto createTripPlan(long userId, long tripId, CreateTripPlanRequest request) {
		userAccountRepository.findById(userId)
			.orElseThrow(() -> new BusinessException(ErrorCode.USER_NOT_FOUND));
		tripRepository.findById(tripId)
			.orElseThrow(() -> new BusinessException(ErrorCode.TRIP_NOT_FOUND));

		TripPlan plan = TripPlan.builder()
			.tripId(tripId)
			.createdBy(userId)
			.content(request.getContent())
			.plannedAt(request.getPlannedAt())
			.position(request.getPosition())
			.build();
		tripPlanRepository.save(plan);
		return TripPlanDto.fromTripPlan(plan);
	}

	@Transactional(readOnly = true)
	public TripPlanDto getTripPlan(long userId, long planId) {
		TripPlan plan = tripPlanRepository.findById(planId)
			.orElseThrow(() -> new BusinessException(ErrorCode.PLAN_NOT_FOUND));
		if (userId != plan.getCreatedBy()) {
			throw new BusinessException(ErrorCode.NOT_AUTHORIZED);
		}
		return TripPlanDto.fromTripPlan(plan);
	}
}
