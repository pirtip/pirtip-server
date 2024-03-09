package com.pirtip.pirtipserver.entity;

import java.time.LocalDateTime;

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
	name = "trip_plan",
	indexes = {
		@Index(name = "idx_created_by", columnList = "trip_plan_created_by"),
		@Index(name = "idx_trip_id_planned_at", columnList = "trip_id,trip_plan_planned_at")
	}
)
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Getter
public class TripPlan {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "trip_plan_id")
	private Long id;

	@Column(name = "trip_id")
	private Long tripId;

	@Column(name = "trip_plan_created_by")
	private Long createdBy;

	@Column(name = "trip_plan_content")
	@Setter
	private String content;

	@Column(name = "trip_plan_planned_at")
	@Setter
	private LocalDateTime plannedAt;

	@Embedded
	@AttributeOverrides({
		@AttributeOverride(name = "latitude", column = @Column(name = "trip_plan_latitude", columnDefinition = "DECIMAL(11,8)")),
		@AttributeOverride(name = "longitude", column = @Column(name = "trip_plan_longitude", columnDefinition = "DECIMAL(11,8)"))
	})
	@Setter
	private Position position;
}
