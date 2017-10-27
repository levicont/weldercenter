package com.lvg.weldercenter.se.repositories

import com.lvg.weldercenter.se.models.Qualification
import org.springframework.data.jpa.repository.JpaRepository

interface QualificationRepository extends JpaRepository<Qualification, Long> {
    void delete(Qualification qualification)

}