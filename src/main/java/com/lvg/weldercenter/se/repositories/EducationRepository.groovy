package com.lvg.weldercenter.se.repositories

import com.lvg.weldercenter.se.models.Education
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.CrudRepository

interface EducationRepository extends JpaRepository<Education, Long> {
}
