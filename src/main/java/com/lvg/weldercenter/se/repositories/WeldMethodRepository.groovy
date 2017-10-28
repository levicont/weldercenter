package com.lvg.weldercenter.se.repositories

import com.lvg.weldercenter.se.models.WeldMethod
import org.springframework.data.jpa.repository.JpaRepository

interface WeldMethodRepository extends JpaRepository<WeldMethod, Long>{
    void delete(WeldMethod weldMethod)
}
