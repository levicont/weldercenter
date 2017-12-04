package com.lvg.weldercenter.se.services.impl

import com.lvg.weldercenter.se.models.NDTDocument
import com.lvg.weldercenter.se.repositories.NDTDocumentRepository
import com.lvg.weldercenter.se.services.NDTDocumentService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class NDTDocumentServiceImpl implements NDTDocumentService{

    @Autowired
    NDTDocumentRepository repository

    @Override
    NDTDocument save(NDTDocument ndtDocument) {
        return repository.save(ndtDocument)
    }

    @Override
    NDTDocument get(Long id) {
        return repository.findOne(id)
    }

    @Override
    List<NDTDocument> getAll() {
        return repository.findAll()
    }

    @Override
    void delete(NDTDocument ndtDocument) {
        repository.delete(ndtDocument)
    }

    @Override
    Long count() {
        return repository.count()
    }
}
