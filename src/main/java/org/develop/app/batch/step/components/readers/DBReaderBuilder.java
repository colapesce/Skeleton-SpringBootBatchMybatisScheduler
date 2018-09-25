package org.develop.app.batch.step.components.readers;

import static org.develop.app.batch.step.Utils.searchInJson;

import org.apache.ibatis.session.SqlSessionFactory;
import org.codehaus.jettison.json.JSONObject;
import org.develop.app.batch.dto.interfaces.Dto;
import org.develop.app.batch.step.Enums.PkgEnums;
import org.develop.app.batch.step.Enums.StepEnums;
import org.develop.app.batch.step.components.interfaces.ReadersInterface;
import org.mybatis.spring.batch.MyBatisCursorItemReader;
import org.springframework.batch.item.ItemReader;
import org.springframework.jdbc.core.RowMapper;

public class DBReaderBuilder implements ReadersInterface<Dto>{

	SqlSessionFactory sqlSessionFactory;
	
	@Override
	public ItemReader<Dto> build(JSONObject json) throws Exception {
		
		Class<RowMapper<Dto>> mapperClass = (Class<RowMapper<Dto>>)Class.forName(PkgEnums.MAPPERS_PKG.getValue()+(String)searchInJson(json, StepEnums.MAPPER_CLASS));
		RowMapper<Dto> mapper = mapperClass.newInstance();
		
		MyBatisCursorItemReader<Dto> reader = new MyBatisCursorItemReader<>();
		reader.setSqlSessionFactory(sqlSessionFactory);
		reader.setQueryId("com.develop.app.dao."+json.getString("mapperStatement"));
		
		return reader;
	}
	
	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		this.sqlSessionFactory = sqlSessionFactory;
	}	
	
}
