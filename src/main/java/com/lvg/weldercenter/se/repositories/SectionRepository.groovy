package com.lvg.weldercenter.se.repositories

import com.lvg.weldercenter.se.models.Section
import org.springframework.data.jpa.repository.JpaRepository

interface SectionRepository extends JpaRepository<Section, Long>{
    void delete(Section section)
}