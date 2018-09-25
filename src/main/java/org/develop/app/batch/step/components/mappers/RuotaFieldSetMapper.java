package org.develop.app.batch.step.components.mappers;


import java.sql.ResultSet;
import java.sql.SQLException;

import org.develop.app.batch.dto.RuotaDto;
import org.develop.app.batch.dto.interfaces.Dto;
import org.springframework.jdbc.core.RowMapper;

public class RuotaFieldSetMapper implements RowMapper<Dto> {
	
	@Override
	public RuotaDto mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		RuotaDto ruota = new RuotaDto();

		ruota.setDescrizioneBreve(rs.getString(1));
		ruota.setPrimoUtilizzo(rs.getDate(2));
				
		return ruota;
	}

}