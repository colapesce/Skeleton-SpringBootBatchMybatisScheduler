package org.develop.app.batch.dto;

import java.util.Date;

import org.develop.app.batch.dto.interfaces.Dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class RuotaDto implements Dto {

	private Long id;
	private String descrizione;
	private String descrizioneBreve;
	private Date primoUso; 	
	
}
