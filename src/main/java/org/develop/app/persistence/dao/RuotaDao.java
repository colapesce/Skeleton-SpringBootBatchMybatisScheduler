package org.develop.app.persistence.dao;

import java.util.List;

import org.develop.app.batch.dto.RuotaDto;

public interface RuotaDao
{
    void insertOne(RuotaDto ruota);
    List<RuotaDto> findAll();
    List<RuotaDto> find(RuotaDto ruota);
}


