package com.lvg.weldercenter.se.repositories

import com.lvg.weldercenter.se.models.Welder
import org.springframework.data.jpa.repository.JpaRepository

interface WelderRepository extends JpaRepository<Welder, Long>{
    void delete(Welder welder)
}
