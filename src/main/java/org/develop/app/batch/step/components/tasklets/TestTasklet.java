package org.develop.app.batch.step.components.tasklets;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;

public class TestTasklet implements Tasklet {
	
	private Map<Object, String> params;

	public Map<Object, String> getParams() {
		return params;
	}

	public void setParams(Map<Object, String> params) {
		this.params = params;
	}

	Logger log = LoggerFactory.getLogger(TestTasklet.class);
	
	public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
		
		for(int i=0; i<100000; i++) {
			log.info("Task row: " + i);
		}
		
		return RepeatStatus.FINISHED;
	}
}