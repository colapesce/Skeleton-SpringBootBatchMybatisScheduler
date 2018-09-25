//package org.develop.app.batch.step.components.writers;
//
//import javax.persistence.EntityManagerFactory;
//
//import org.codehaus.jettison.json.JSONObject;
//import org.develop.app.batch.dto.interfaces.Dto;
//import org.develop.app.batch.step.components.interfaces.WritersInterface;
//import org.springframework.batch.item.ItemWriter;
//import org.springframework.batch.item.database.JpaItemWriter;
//
//public class JPAWriterBuilder implements WritersInterface<Dto>{
//
//	private EntityManagerFactory entityManagerFactory;
//	
//	@Override
//	public ItemWriter<Dto> build(JSONObject json) {
//		JpaItemWriter<Dto> writer = new JpaItemWriter<Dto>();
//		writer.setEntityManagerFactory(entityManagerFactory);
//		return writer;
//	}
//	
//	public void setEntityManager(EntityManagerFactory entityManagerFactory) {
//		this.entityManagerFactory = entityManagerFactory;
//	}	
//
//}
