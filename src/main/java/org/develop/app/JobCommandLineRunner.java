//package org.develop.app;
//
//import java.util.Date;
//
//import org.codehaus.jettison.json.JSONObject;
//import org.develop.app.batch.factory.JobFactory;
//import org.develop.app.batch.listeners.JobCompletionNotificationListener;
//import org.springframework.batch.core.JobParametersBuilder;
//import org.springframework.batch.core.launch.JobLauncher;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.stereotype.Component;
//
//@Component
//public class JobCommandLineRunner implements CommandLineRunner{
//
//	@Autowired
//	private JobLauncher jobLauncher;
//	@Autowired
//	private JobFactory jobFactory;
//	@Autowired 
//	JobCompletionNotificationListener jobListener;
//	
//	@Override
//	public void run(String... args) throws Exception {
//		
//		JobParametersBuilder builder = new JobParametersBuilder();
//		
////		Date start = new Date();
//		jobLauncher.run(jobFactory.getJob(args[0], jobListener), builder.toJobParameters());
//		
//	}
//
//}
