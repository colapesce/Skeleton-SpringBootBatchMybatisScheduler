package org.develop.app.batch.dto;

import java.sql.Timestamp;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class JobConfigDto {

	private long id;
	private String name;
	private String parameters;
	private Timestamp insertTs;
	private List<StepConfigDto> steps;

}
