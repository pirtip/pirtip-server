package com.pirtip.pirtipserver.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(
	name = "trip",
	indexes = {
		@Index(name = "idx_created_by", columnList = "trip_created_by")
	}
)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Getter
public class Trip {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "trip_id")
	private Long id;

	@Column(name = "trip_created_by")
	private Long createdBy;

	@Column(name = "trip_title")
	private String title;

	@Column(name = "trip_content")
	private String content;

	@Column(name = "trip_begin_date")
	private LocalDate beginDate;

	@Column(name = "trip_end_date")
	private LocalDate endDate;
}
