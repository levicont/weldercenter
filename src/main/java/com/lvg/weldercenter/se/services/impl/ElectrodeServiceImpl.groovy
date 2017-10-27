package com.lvg.weldercenter.se.services.impl

import com.lvg.weldercenter.se.models.Electrode
import com.lvg.weldercenter.se.repositories.ElectrodeRepository
import com.lvg.weldercenter.se.services.ElectrodeService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class ElectrodeServiceImpl implements ElectrodeService {

    @Autowired
    ElectrodeRepository repository


    @Override
    Electrode save(Electrode electrode) {
        return repository.save(electrode)
    }

    @Override
    Electrode get(Long id) {
        return repository.findOne(id)
    }

    @Override
    List<Electrode> getAll() {
        return repository.findAll()
    }

    @Override
    void delete(Electrode electrode) {
        repository.delete(electrode)
    }
}
