package com.pirtip.pirtipserver.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pirtip.pirtipserver.common.BusinessException;
import com.pirtip.pirtipserver.common.ErrorCode;
import com.pirtip.pirtipserver.entity.Trip;
import com.pirtip.pirtipserver.entity.UserAccount;
import com.pirtip.pirtipserver.model.CreateTripRequest;
import com.pirtip.pirtipserver.model.ReadTripRequest;
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
	public TripDto createTrip(long accountId, CreateTripRequest body) {
		UserAccount user = userAccountRepository.findById(accountId)
			.orElseThrow(() -> new BusinessException(ErrorCode.USER_NOT_FOUND));
		Trip trip = Trip.builder()
			.createdBy(user.getId())
			.title(body.getTitle())
			.content(body.getContent())
			.beginDate(body.getBeginDate())
			.endDate(body.getEndDate())
			.position(body.getPosition())
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

	@Transactional(readOnly = true)
	public Slice<TripDto> getTrips(long userId, ReadTripRequest request) {
		if (request == null) {
			request = ReadTripRequest.builder()
				.userId(userId)
				.page(0)
				.size(10)
				.build();
		}
		if (request.getUserId() == null) {
			request.setUserId(userId);
		}
		Pageable pageable = PageRequest.of(
			request.getPage(),
			request.getSize()
		);
		Slice<Trip> tripSlice = tripRepository.findByCreatedBy(request.getUserId(), pageable);
		List<TripDto> tripDtoList = tripSlice.getContent()
			.stream()
			.map(TripDto::fromTrip)
			.collect(Collectors.toList());
		return new SliceImpl<>(tripDtoList, tripSlice.getPageable(), tripSlice.hasNext());
	}
}
