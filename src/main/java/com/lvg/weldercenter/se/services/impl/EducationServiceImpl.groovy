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
    Education addEducation(Education education) {
        return educationRepository.save(education)
    }

    @Override
    Set<Education> getAll() {
        Set<Education> set = new HashSet<>()
        set.addAll(educationRepository.findAll())
        return set
    }
}
