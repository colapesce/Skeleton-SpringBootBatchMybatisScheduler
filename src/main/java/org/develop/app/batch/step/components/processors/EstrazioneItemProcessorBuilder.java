package org.develop.app.batch.step.components.processors;


import org.codehaus.jettison.json.JSONObject;
import org.develop.app.batch.dto.EstrazioneDto;
import org.develop.app.batch.dto.interfaces.Dto;
import org.develop.app.batch.step.components.interfaces.ProcessorsInterface;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;


public class EstrazioneItemProcessorBuilder implements ProcessorsInterface<Dto, Dto> {

	private static final Logger log = LoggerFactory.getLogger(EstrazioneItemProcessorBuilder.class);
	
	@Override
	public ItemProcessor<Dto, Dto> build(JSONObject json) {		
		return new EstrazioneItemProcessor();
	}

	private class EstrazioneItemProcessor implements ItemProcessor<Dto, Dto> {
	
		@Override
		public EstrazioneDto process(Dto in) throws Exception {
	
			EstrazioneDto item = (EstrazioneDto) in;
			
			log.info("Processing..." + item);
			
			EstrazioneDto estrazione = new EstrazioneDto(); 
			estrazione.setDataEstrazione(item.getDataEstrazione());
			estrazione.setRuota(item.getRuota());
			estrazione.setColonna1(item.getColonna1());
			estrazione.setColonna2(item.getColonna2());
			estrazione.setColonna3(item.getColonna3());
			estrazione.setColonna4(item.getColonna4());
			estrazione.setColonna5(item.getColonna5());		
			
			return estrazione;
		}
	}
}