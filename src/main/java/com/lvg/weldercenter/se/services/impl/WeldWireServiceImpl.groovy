package com.lvg.weldercenter.se.services.impl

import com.lvg.weldercenter.se.models.WeldWire
import com.lvg.weldercenter.se.repositories.WeldWireRepository
import com.lvg.weldercenter.se.services.WeldWireService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class WeldWireServiceImpl implements WeldWireService{

    @Autowired
    WeldWireRepository repository

    @Override
    WeldWire save(WeldWire weldWire) {
        return repository.save(weldWire)
    }

    @Override
    WeldWire get(Long id) {
        return repository.findOne(id)
    }

    @Override
    List<WeldWire> getAll() {
        return repository.findAll()
    }

    @Override
    void delete(WeldWire weldWire) {
        repository.delete(weldWire)
    }

    @Override
    Long count() {
        return repository.count()
    }
}
