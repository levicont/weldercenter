package com.lvg.weldercenter.se.services

import com.lvg.weldercenter.se.models.Education

interface EducationService {

    Education addEducation(Education education)
    Education findById(Long id)
    List<Education> getAll()
    void delete(Education education)

}