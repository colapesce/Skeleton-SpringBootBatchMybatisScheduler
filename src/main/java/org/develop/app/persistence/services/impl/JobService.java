package org.develop.app.persistence.services.impl;

import java.util.List;

import org.develop.app.batch.dto.JobConfigDto;
import org.develop.app.persistence.dao.JobConfigDao;
import org.develop.app.persistence.services.interfaces.IJobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JobService implements IJobService {

	@Autowired
	private JobConfigDao jobDao;
	
	@Override
	public JobConfigDto loadBy(JobConfigDto jobDto) {		
		return jobDao.findBy(jobDto).get(0);
	}

	@Override
	public List<JobConfigDto> loadAll() {
		return jobDao.findAll();
	}

}
