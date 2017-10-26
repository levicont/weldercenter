package com.lvg.weldercenter.se.services

import com.lvg.weldercenter.se.models.Education

interface EducationService {

    Education addEducation(Education education)
    Set<Education> getAll()

}