package com.pirtip.pirtipserver.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pirtip.pirtipserver.common.BusinessException;
import com.pirtip.pirtipserver.common.ErrorCode;
import com.pirtip.pirtipserver.entity.Trip;
import com.pirtip.pirtipserver.entity.UserAccount;
import com.pirtip.pirtipserver.model.CreateTripDto;
import com.pirtip.pirtipserver.model.TripDto;
import com.pirtip.pirtipserver.repository.TripRepository;
import com.pirtip.pirtipserver.repository.UserAccountRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TripService {

	private final UserAccountRepository userAccountRepository;

	private final TripRepository tripRepository;

	@Transactional
	public TripDto createTrip(long accountId, CreateTripDto body) {
		UserAccount user = userAccountRepository.findById(accountId)
			.orElseThrow(() -> new BusinessException(ErrorCode.USER_NOT_FOUND));
		Trip trip = Trip.builder()
			.createdBy(user.getId())
			.title(body.getTitle())
			.content(body.getContent())
			.beginDate(body.getBeginDate())
			.endDate(body.getEndDate())
			.build();
		tripRepository.save(trip);
		return TripDto.fromTrip(trip);
	}

	@Transactional(readOnly = true)
	public TripDto getTripById(long accountId, long tripId) {
		Trip trip = tripRepository.findById(tripId)
			.orElseThrow(() -> new BusinessException(ErrorCode.TRIP_NOT_FOUND));
		if (trip.getCreatedBy() != accountId) {
			throw new BusinessException(ErrorCode.NOT_AUTHORIZED, "This trip is not yours.");
		}
		return TripDto.fromTrip(trip);
	}
}
