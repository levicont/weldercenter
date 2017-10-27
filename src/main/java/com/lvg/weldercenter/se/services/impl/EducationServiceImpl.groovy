package com.lvg.weldercenter.se.services.impl

import com.lvg.weldercenter.se.models.Education
import com.lvg.weldercenter.se.repositories.EducationRepository
import com.lvg.weldercenter.se.services.EducationService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class EducationServiceImpl implements EducationService{

    @Autowired
    EducationRepository educationRepository

    @Override
    Education save(Education education) {
        return educationRepository.save(education)
    }

    @Override
    Education get(Long id) {
        return educationRepository.findOne(id)
    }

    @Override
    List<Education> getAll() {
        return educationRepository.findAll()
    }

    @Override
    void delete(Education education) {
        educationRepository.delete(education)
    }

}
