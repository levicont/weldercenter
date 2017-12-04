package com.lvg.weldercenter.se.services.impl

import com.lvg.weldercenter.se.models.Qualification
import com.lvg.weldercenter.se.repositories.QualificationRepository
import com.lvg.weldercenter.se.services.QualificationService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class QualificationServiceImpl implements QualificationService {

    @Autowired
    QualificationRepository repository

    @Override
    Qualification save(Qualification qualification) {
        return repository.save(qualification)
    }

    @Override
    Qualification get(Long id) {
        return repository.findOne(id)
    }

    @Override
    List<Qualification> getAll() {
        return repository.findAll()
    }

    @Override
    void delete(Qualification qualification) {
        repository.delete(qualification)
    }

    @Override
    Long count() {
        return repository.count()
    }


}
