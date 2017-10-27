package com.lvg.weldercenter.se.repositories

import com.lvg.weldercenter.se.models.Electrode
import org.springframework.data.jpa.repository.JpaRepository

interface ElectrodeRepository extends JpaRepository<Electrode, Long>{
    void delete(Electrode electrode)
}