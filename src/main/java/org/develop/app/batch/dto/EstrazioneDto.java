package org.develop.app.batch.dto;

import java.util.Date;

import org.develop.app.batch.dto.interfaces.Dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class EstrazioneDto implements Dto {

	private Long id;
	private String ruota;
	private Date dataEstrazione;
	private int colonna1;
	private int colonna2;
	private int colonna3;
	private int colonna4;
	private int colonna5;
	
	@Override
	public String toString() {
		
		return this.getDataEstrazione() + " " +
			   this.getRuota() + " " +
			   this.getColonna1() + " " +
			   this.getColonna2() + " " +
			   this.getColonna3() + " " +
			   this.getColonna4() + " " +
			   this.getColonna5();
	}
}
