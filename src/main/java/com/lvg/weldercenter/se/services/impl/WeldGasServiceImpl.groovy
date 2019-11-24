package com.lvg.weldercenter.se.services.impl

import com.lvg.weldercenter.se.models.WeldGas
import com.lvg.weldercenter.se.repositories.WeldGasRepository
import com.lvg.weldercenter.se.services.WeldGasService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

/**
 * Created by Victor Levchenko LVG Corp. on 18.03.19.
 */

@Service
class WeldGasServiceImpl implements WeldGasService{

    @Autowired
    WeldGasRepository repository

    @Override
    WeldGas save(WeldGas entity) {
        return repository.save(entity)
    }

    @Override
    WeldGas get(Long id) {
        return repository.findOne(id)
    }

    @Override
    List<WeldGas> getAll() {
        return repository.findAll()
    }

    @Override
    void delete(WeldGas entity) {
        repository.delete(entity)
    }

    @Override
    Long count() {
        return repository.count()
    }
}
