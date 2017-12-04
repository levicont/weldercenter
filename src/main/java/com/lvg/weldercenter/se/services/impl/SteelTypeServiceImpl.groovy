package com.lvg.weldercenter.se.services.impl

import com.lvg.weldercenter.se.models.SteelType
import com.lvg.weldercenter.se.repositories.SteelTypeRepository
import com.lvg.weldercenter.se.services.SteelTypeService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class SteelTypeServiceImpl implements SteelTypeService {

    @Autowired
    SteelTypeRepository repository

    @Override
    SteelType save(SteelType steelType) {
        return repository.save(steelType)
    }

    @Override
    SteelType get(Long id) {
        return repository.findOne(id)
    }

    @Override
    List<SteelType> getAll() {
        return repository.findAll()
    }

    @Override
    void delete(SteelType steelType) {
        repository.delete(steelType)
    }

    @Override
    Long count() {
        return repository.count()
    }
}
