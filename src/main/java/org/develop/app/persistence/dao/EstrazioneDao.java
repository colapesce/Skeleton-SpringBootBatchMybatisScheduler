package org.develop.app.persistence.dao;

import java.util.List;

import org.develop.app.batch.dto.EstrazioneDto;

public interface EstrazioneDao
{
    void insertEstrazione(EstrazioneDto estrazione);
    List<EstrazioneDto> findAll();
    EstrazioneDto findById(Integer idEstrazione);
}


