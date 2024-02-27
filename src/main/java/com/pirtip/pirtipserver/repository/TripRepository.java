package com.pirtip.pirtipserver.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;

import com.pirtip.pirtipserver.entity.Trip;

public interface TripRepository extends JpaRepository<Trip, Long> {

	Slice<Trip> findByCreatedBy(long createdBy, Pageable pageable);
}
