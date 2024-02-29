package com.pirtip.pirtipserver.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;

import com.pirtip.pirtipserver.entity.TripPlan;

public interface TripPlanRepository extends JpaRepository<TripPlan, Long> {

	Slice<TripPlan> findByTripId(long tripId, Pageable pageable);
}
