package org.develop.app.batch.dto;

import java.sql.Timestamp;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class StepConfigDto {

	private long id;
	private String name;
	private String config;
	private Timestamp insertTs;
	private int executionOrder;
	private String type;
	
}
