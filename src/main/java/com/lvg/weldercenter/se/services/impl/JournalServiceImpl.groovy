package com.lvg.weldercenter.se.services.impl

import com.lvg.weldercenter.se.models.Journal
import com.lvg.weldercenter.se.repositories.JournalRepository
import com.lvg.weldercenter.se.services.JournalService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class JournalServiceImpl implements JournalService{

    @Autowired
    JournalRepository repository

    @Override
    Journal save(Journal journal) {
        return repository.save(journal)
    }

    @Override
    Journal get(Long id) {
        return repository.findOne(id)
    }

    @Override
    List<Journal> getAll() {
        return repository.findAll()
    }

    @Override
    void delete(Journal journal) {
        repository.delete(journal)
    }
}
