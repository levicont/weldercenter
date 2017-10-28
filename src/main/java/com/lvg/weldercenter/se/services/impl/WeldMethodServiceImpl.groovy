package com.lvg.weldercenter.se.services.impl

import com.lvg.weldercenter.se.models.WeldMethod
import com.lvg.weldercenter.se.repositories.WeldMethodRepository
import com.lvg.weldercenter.se.services.WeldMethodService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class WeldMethodServiceImpl implements WeldMethodService{

    @Autowired
    WeldMethodRepository repository

    @Override
    WeldMethod save(WeldMethod weldMethod) {
        return repository.save(weldMethod)
    }

    @Override
    WeldMethod get(Long id) {
        return repository.findOne(id)
    }

    @Override
    List<WeldMethod> getAll() {
        return repository.findAll()
    }

    @Override
    void delete(WeldMethod weldMethod) {
        repository.delete(weldMethod)
    }
}
