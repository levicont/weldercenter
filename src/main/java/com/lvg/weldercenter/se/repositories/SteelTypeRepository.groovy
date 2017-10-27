package com.lvg.weldercenter.se.repositories

import com.lvg.weldercenter.se.models.SteelType
import org.springframework.data.jpa.repository.JpaRepository

interface SteelTypeRepository extends JpaRepository<SteelType, Long>{
    void delete(SteelType steelType)

}