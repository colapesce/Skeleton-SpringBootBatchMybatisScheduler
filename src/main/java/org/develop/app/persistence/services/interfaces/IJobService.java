package org.develop.app.persistence.services.interfaces;

import java.util.List;

import org.develop.app.batch.dto.JobConfigDto;

public interface IJobService {

	public List<JobConfigDto> loadAll();
	public JobConfigDto loadBy(JobConfigDto jobDto);	
	
}
