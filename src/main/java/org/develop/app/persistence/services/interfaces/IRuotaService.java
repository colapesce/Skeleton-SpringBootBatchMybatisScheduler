package org.develop.app.persistence.services.interfaces;

import java.util.List;

import org.develop.app.batch.dto.RuotaDto;

public interface IRuotaService {

	public RuotaDto loadRuota(String chiave);
	
	public List<RuotaDto> loadAll();
	
	public void salvaRuota(RuotaDto ruota);
	
}
