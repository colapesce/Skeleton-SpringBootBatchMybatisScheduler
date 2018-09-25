package org.develop.app.batch.step.components.writers;

import org.apache.ibatis.session.SqlSessionFactory;
import org.codehaus.jettison.json.JSONObject;
import org.develop.app.batch.dto.interfaces.Dto;
import org.develop.app.batch.step.components.interfaces.WritersInterface;
import org.mybatis.spring.batch.MyBatisBatchItemWriter;
import org.springframework.batch.item.ItemWriter;

public class DBWriterBuilder implements WritersInterface<Dto>{

	private SqlSessionFactory sqlSessionFactory;
	
	@Override
	public ItemWriter<Dto> build(JSONObject json) throws Exception {
		
		MyBatisBatchItemWriter<Dto> itemWriter = new MyBatisBatchItemWriter<>();
		
		itemWriter.setSqlSessionFactory(sqlSessionFactory);
		itemWriter.setStatementId("org.develop.app.persistence.dao."+json.getString("mapperStatement"));
		
		return itemWriter;
	}

	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		this.sqlSessionFactory = sqlSessionFactory;
	}	
	
}
