package com.lvg.weldercenter.se.repositories

import com.lvg.weldercenter.se.models.WeldPattern
import org.springframework.data.jpa.repository.JpaRepository

interface WeldPatternRepository extends JpaRepository<WeldPattern, Long>{
    void delete(WeldPattern weldPattern)
}