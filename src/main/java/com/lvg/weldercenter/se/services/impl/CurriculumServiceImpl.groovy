package com.lvg.weldercenter.se.services.impl

import com.lvg.weldercenter.se.models.Curriculum
import com.lvg.weldercenter.se.repositories.CurriculumRepository
import com.lvg.weldercenter.se.services.CurriculumService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class CurriculumServiceImpl implements CurriculumService{

    @Autowired
    CurriculumRepository repository

    @Override
    Curriculum save(Curriculum curriculum) {
        return repository.save(curriculum)
    }

    @Override
    Curriculum get(Long id) {
        return repository.findOne(id)
    }

    @Override
    List<Curriculum> getAll() {
        return repository.findAll()
    }

    @Override
    void delete(Curriculum curriculum) {
        repository.delete(curriculum)
    }
}
