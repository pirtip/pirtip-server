package com.pirtip.pirtipserver.entity;

import java.time.LocalDate;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;
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
import lombok.Setter;

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

	@Setter
	@Column(name = "trip_title")
	private String title;

	@Setter
	@Column(name = "trip_content")
	private String content;

	@Setter
	@Column(name = "trip_begin_date")
	private LocalDate beginDate;

	@Setter
	@Column(name = "trip_end_date")
	private LocalDate endDate;

	@Setter
	@Embedded
	@AttributeOverrides({
		@AttributeOverride(name = "latitude", column = @Column(name = "trip_latitude", columnDefinition = "DECIMAL(11,8)")),
		@AttributeOverride(name = "longitude", column = @Column(name = "trip_longitude", columnDefinition = "DECIMAL(11,8)"))
	})
	private Position position;
}
