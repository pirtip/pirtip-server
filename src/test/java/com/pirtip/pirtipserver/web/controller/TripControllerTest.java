package com.pirtip.pirtipserver.web.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.time.LocalDate;

import org.assertj.core.api.BDDAssertions;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.BDDMockito;
import org.mockito.Captor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import com.pirtip.pirtipserver.config.SecurityConfiguration;
import com.pirtip.pirtipserver.model.CreateTripDto;
import com.pirtip.pirtipserver.service.TripService;

@WebMvcTest(controllers = TripController.class)
@Import(SecurityConfiguration.class)
@ActiveProfiles("local")
class TripControllerTest {

	@SpyBean
	private TripController tripController;

	@MockBean
	private TripService tripService;

	@Autowired
	private MockMvc mvc;

	@Captor
	private ArgumentCaptor<CreateTripDto> argumentCaptor;

	@Test
	void createTrip() throws Exception {
		// given
		String bodyAsString = "{\"title\":\"title\", \"content\":\"content\", \"beginDate\": \"2024-01-01\", \"endDate\": \"2024-01-03\"}";

		// when
		ResultActions result = mvc.perform(
			post("/api/trip")
				.contentType(MediaType.APPLICATION_JSON)
				.content(bodyAsString)
		);

		// then
		result.andExpect(status().isOk());
		BDDMockito.then(tripController)
			.should()
			.createTrip(argumentCaptor.capture());
		BDDAssertions.then(argumentCaptor.getValue())
			.extracting(CreateTripDto::getTitle, CreateTripDto::getContent, CreateTripDto::getBeginDate, CreateTripDto::getEndDate)
			.containsExactly("title", "content", LocalDate.of(2024, 1, 1), LocalDate.of(2024, 1, 3));
	}
}
