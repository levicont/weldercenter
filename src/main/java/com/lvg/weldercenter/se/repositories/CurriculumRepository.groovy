package com.lvg.weldercenter.se.repositories

import com.lvg.weldercenter.se.models.Curriculum
import org.springframework.data.jpa.repository.JpaRepository

interface CurriculumRepository extends JpaRepository<Curriculum, Long>{
    void delete(Curriculum curriculum)
}