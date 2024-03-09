package com.pirtip.pirtipserver.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.pirtip.pirtipserver.entity.TripPlan;

public interface TripPlanRepository extends JpaRepository<TripPlan, Long> {

	Slice<TripPlan> findByTripId(long tripId, Pageable pageable);

	@Modifying(flushAutomatically = true)
	@Query("delete from TripPlan tp where tp.tripId = :tripId")
	int deleteAllByTripId(@Param("tripId") long tripId);
}
