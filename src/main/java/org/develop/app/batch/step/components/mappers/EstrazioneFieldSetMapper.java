package org.develop.app.batch.step.components.mappers;


import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.develop.app.batch.dto.EstrazioneDto;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.validation.BindException;

public class EstrazioneFieldSetMapper implements FieldSetMapper<EstrazioneDto> {

	@Override
	public EstrazioneDto mapFieldSet(FieldSet fieldSet) throws BindException {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		
		EstrazioneDto lotto = new EstrazioneDto();

		try {
			lotto.setDataEstrazione(sdf.parse(fieldSet.readString(0)));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		lotto.setRuota(fieldSet.readString(1));
		lotto.setColonna1(fieldSet.readInt(2));
		lotto.setColonna2(fieldSet.readInt(3));
		lotto.setColonna3(fieldSet.readInt(4));
		lotto.setColonna4(fieldSet.readInt(5));
		lotto.setColonna5(fieldSet.readInt(6));
				
		return lotto;

	}

}