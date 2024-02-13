package com.pirtip.pirtipserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pirtip.pirtipserver.entity.Trip;

public interface TripRepository extends JpaRepository<Trip, Long> {
}
