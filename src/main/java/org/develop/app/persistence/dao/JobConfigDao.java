package org.develop.app.persistence.dao;

import java.util.List;

import org.develop.app.batch.dto.JobConfigDto;

public interface JobConfigDao {

	List<JobConfigDto> findAll();
	List<JobConfigDto> findBy(JobConfigDto jobDto);
	
}
