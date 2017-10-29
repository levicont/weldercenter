package com.lvg.weldercenter.se.repositories

import com.lvg.weldercenter.se.models.CommissionCertification
import org.springframework.data.jpa.repository.JpaRepository

interface CommissionCertificationRepository extends JpaRepository<CommissionCertification, Long>{
    void delete(CommissionCertification commissionCertification)
}