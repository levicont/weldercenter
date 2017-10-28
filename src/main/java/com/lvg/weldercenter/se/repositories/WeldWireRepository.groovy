package com.lvg.weldercenter.se.repositories

import com.lvg.weldercenter.se.models.WeldWire
import org.springframework.data.jpa.repository.JpaRepository

interface WeldWireRepository extends JpaRepository<WeldWire, Long>{
    void delete(WeldWire weldWire)

}