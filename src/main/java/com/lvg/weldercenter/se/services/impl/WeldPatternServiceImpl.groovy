package com.lvg.weldercenter.se.services.impl

import com.lvg.weldercenter.se.models.WeldPattern
import com.lvg.weldercenter.se.repositories.WeldPatternRepository
import com.lvg.weldercenter.se.services.WeldPatternService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class WeldPatternServiceImpl implements WeldPatternService{

    @Autowired
    WeldPatternRepository repository

    @Override
    WeldPattern save(WeldPattern weldPattern) {
        return repository.save(weldPattern)
    }

    @Override
    WeldPattern get(Long id) {
        return repository.findOne(id)
    }

    @Override
    List<WeldPattern> getAll() {
        return repository.findAll()
    }

    @Override
    void delete(WeldPattern weldPattern) {
        repository.delete(weldPattern)
    }

    @Override
    Long count() {
        return repository.count()
    }
}
