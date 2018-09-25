package org.develop.app.batch.factory;

import static org.develop.app.batch.step.Utils.searchInJson;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.codehaus.jettison.json.JSONObject;
import org.develop.app.batch.dto.JobConfigDto;
import org.develop.app.batch.dto.StepConfigDto;
import org.develop.app.batch.dto.interfaces.Dto;
import org.develop.app.batch.exceptions.NoJobDefException;
import org.develop.app.batch.listeners.JobCompletionNotificationListener;
import org.develop.app.batch.step.Enums.PkgEnums;
import org.develop.app.batch.step.Enums.StepEnums;
import org.develop.app.batch.step.components.interfaces.ProcessorsInterface;
import org.develop.app.batch.step.components.interfaces.ReadersInterface;
import org.develop.app.batch.step.components.interfaces.WritersInterface;
import org.develop.app.persistence.services.interfaces.IJobService;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.job.builder.FlowBuilder;
import org.springframework.batch.core.job.builder.FlowJobBuilder;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.job.builder.JobFlowBuilder;
import org.springframework.batch.core.job.flow.Flow;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.step.builder.SimpleStepBuilder;
import org.springframework.batch.core.step.builder.TaskletStepBuilder;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableBatchProcessing
public class JobFactory {

	@Autowired
	public JobBuilderFactory jobBuilderFactory;

	@Autowired
	public StepBuilderFactory stepBuilderFactory;

	@Autowired
	public SqlSessionFactory sqlSessionFactory;
	
	@Autowired
	public IJobService jobService;
	
	public Job getJob(String jobName, JobCompletionNotificationListener listener) throws Exception{
		
		Job job = null;
		
		JobConfigDto jobDto = new JobConfigDto();
		jobDto.setName(jobName);
		
		JobConfigDto jobConfig = jobService.loadBy(jobDto);
		
		List<Step> steps = new ArrayList<>();
		
		if(jobConfig != null) {
		
			List<StepConfigDto> stepsDto = jobConfig.getSteps();
			
			JobBuilder jobBuilder = jobBuilderFactory.get(jobConfig.getName())
					 .incrementer(new RunIdIncrementer())
					 .listener(listener);
			
			JobFlowBuilder jobFlowBuilder = null;
			FlowBuilder<FlowJobBuilder> flowBuilder = null;
			
			for(StepConfigDto step : stepsDto) {
				
				JSONObject json = new JSONObject(step.getConfig());
				
				steps.add(getStep(step.getName(), step.getType(), json));
			}	
			
			FlowBuilder<Flow> flow = new FlowBuilder<Flow>(jobName+"_steps_flow");
			
			for(int i=0; i<steps.size(); i++) {
				if(i==0) {
					flow.from(steps.get(0));
				}
				else {
					flow.next(steps.get(i));
				}
			}			
			
			job = jobBuilder.start(flow.end()).end().build();
		}
		else {
			throw new NoJobDefException();
		}
		return job;
	}

	
	private Step getStep(String stepName, String stepType, JSONObject stepInfo) throws Exception {
		
		ItemReader<Dto> itemReader = null;
		ItemProcessor<Dto, Dto> itemProcessor = null;
		ItemWriter<Dto> itemWriter = null;
		
		int chunkSize = searchInJson(stepInfo, StepEnums.CHUNK) != null ? Integer.parseInt((String)searchInJson(stepInfo, StepEnums.CHUNK)) : 1;
		
		if(stepType.equals("step")) {
			
			SimpleStepBuilder<Dto, Dto> stepBuilder = stepBuilderFactory.get(stepName).<Dto, Dto>chunk(chunkSize);
			
			if(searchInJson(stepInfo, StepEnums.READER) != null) {
				
				JSONObject itemJson = (JSONObject)searchInJson(stepInfo, StepEnums.READER);
				
				Class<ReadersInterface<Dto>> readerClass = (Class<ReadersInterface<Dto>>)Class.forName(PkgEnums.READERS_PKG.getValue()+(String)searchInJson(itemJson, StepEnums.CLASS_NAME));
				ReadersInterface<Dto> reader = readerClass.newInstance();
				try {
					Method m = reader.getClass().getMethod("setSqlSessionFactory", SqlSessionFactory.class);
					m.invoke(reader, sqlSessionFactory);
				}
				catch(Exception e) {}
				
				stepBuilder.reader(reader.build(itemJson.getJSONObject("info")));
	//			stepBuilder.listener(listener);
			}
			
			if(searchInJson(stepInfo, StepEnums.PROCESSOR) != null) {
				
				JSONObject itemJson = (JSONObject)searchInJson(stepInfo, StepEnums.PROCESSOR);
				
				Class<ProcessorsInterface<Dto,Dto>> processorClass = (Class<ProcessorsInterface<Dto, Dto>>)Class.forName(PkgEnums.PROCESSORS_PKG.getValue()+(String)searchInJson(itemJson, StepEnums.CLASS_NAME));
				ProcessorsInterface<Dto, Dto> processor = processorClass.newInstance();
				
				stepBuilder.processor(processor.build(itemJson.getJSONObject("info")));
	//			stepBuilder.listener(listener);
			}
			
			if(searchInJson(stepInfo, StepEnums.WRITER) != null) {
				
				JSONObject itemJson = (JSONObject)searchInJson(stepInfo, StepEnums.WRITER);
				
				Class<WritersInterface<Dto>> readerClass = (Class<WritersInterface<Dto>>)Class.forName(PkgEnums.WRITERS_PKG.getValue()+(String)searchInJson(itemJson, StepEnums.CLASS_NAME));
				WritersInterface<Dto> writer = readerClass.newInstance();
				
				try {
					Method m = writer.getClass().getMethod("setSqlSessionFactory", SqlSessionFactory.class);
					m.invoke(writer, sqlSessionFactory);
				}
				catch(Exception e) {}
				
				stepBuilder.writer(writer.build(itemJson.getJSONObject("info")));
				
				stepBuilder.allowStartIfComplete(itemJson.getJSONObject("info").getBoolean("restartable"));
	//			stepBuilder.listener(listener);
			}
			
			return stepBuilder.build();
		}
		else {
			
			JSONObject itemJson = (JSONObject)searchInJson(stepInfo, StepEnums.TASKLET);
			
			Class<Tasklet> taskletClass = (Class<Tasklet>)Class.forName(PkgEnums.TESKLETS_PKG.getValue()+(String)searchInJson(itemJson, StepEnums.CLASS_NAME));
			Tasklet tasklet = taskletClass.newInstance();
			
			TaskletStepBuilder taskletStepBuilder = stepBuilderFactory.get(stepName).tasklet(tasklet);
			taskletStepBuilder.allowStartIfComplete(((JSONObject)itemJson.get("info")).getBoolean("restartable"));
//			taskletStepBuilder.listener(listener);
			
			return taskletStepBuilder.build();
			
		}
		
		
	}

}
