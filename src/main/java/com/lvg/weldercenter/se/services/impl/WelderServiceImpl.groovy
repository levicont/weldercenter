package com.lvg.weldercenter.se.services.impl

import com.lvg.weldercenter.se.models.Welder
import com.lvg.weldercenter.se.repositories.WelderRepository
import com.lvg.weldercenter.se.services.WelderService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class WelderServiceImpl implements WelderService{

    @Autowired
    WelderRepository repository

    @Override
    Welder save(Welder welder) {
        return repository.save(welder)
    }

    @Override
    Welder get(Long id) {
        return repository.findOne(id)
    }

    @Override
    List<Welder> getAll() {
        return repository.findAll()
    }

    @Override
    void delete(Welder welder) {
        repository.delete(welder)
    }
}
