package org.develop.app.persistence.dao;

import java.util.List;

import org.develop.app.batch.dto.EstrazioneDto;

public interface EstrazioneDao
{
    void insertOne(EstrazioneDto estrazione);
//    void insertMore(List<EstrazioneDto> estrazioni);
    List<EstrazioneDto> findAll();
    EstrazioneDto findById(Integer idEstrazione);
}


