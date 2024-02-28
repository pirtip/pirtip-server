package com.pirtip.pirtipserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pirtip.pirtipserver.entity.TripPlan;

public interface TripPlanRepository extends JpaRepository<TripPlan, Long> {
}
