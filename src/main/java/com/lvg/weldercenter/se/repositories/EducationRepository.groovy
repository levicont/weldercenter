package com.lvg.weldercenter.se.repositories

import com.lvg.weldercenter.se.models.Education
import org.springframework.data.jpa.repository.JpaRepository

interface EducationRepository extends JpaRepository<Education, Long> {
    void delete(Education education)
}
