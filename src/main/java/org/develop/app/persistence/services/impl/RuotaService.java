package org.develop.app.persistence.services.impl;

import java.util.List;

import org.develop.app.batch.dto.RuotaDto;
import org.develop.app.persistence.dao.RuotaDao;
import org.develop.app.persistence.services.interfaces.IRuotaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RuotaService implements IRuotaService {

	@Autowired
	private RuotaDao ruotaDao;
	
	@Override
	public RuotaDto loadRuota(String chiave) {
		
		RuotaDto ruota = new RuotaDto();
		ruota.setDescrizioneBreve(chiave);
		
		List<RuotaDto> ruote = ruotaDao.find(ruota);
		return !ruote.isEmpty() ? ruote.get(0) : null;
	}

	@Override
	@Transactional
	public void salvaRuota(RuotaDto ruota) {
		ruotaDao.insertRuota(ruota);
	}

	@Override
	public List<RuotaDto> loadAll() {
		return ruotaDao.findAll();
	}
	
}
