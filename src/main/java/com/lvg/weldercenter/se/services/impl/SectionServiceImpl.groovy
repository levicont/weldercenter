package com.lvg.weldercenter.se.services.impl

import com.lvg.weldercenter.se.models.Section
import com.lvg.weldercenter.se.repositories.SectionRepository
import com.lvg.weldercenter.se.services.SectionService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class SectionServiceImpl implements SectionService{

    @Autowired
    SectionRepository repository

    @Override
    Section save(Section section) {
        return repository.save(section)
    }

    @Override
    Section get(Long id) {
        return repository.findOne(id)
    }

    @Override
    List<Section> getAll() {
        return repository.findAll()
    }

    @Override
    void delete(Section section) {
        repository.delete(section)
    }
}
