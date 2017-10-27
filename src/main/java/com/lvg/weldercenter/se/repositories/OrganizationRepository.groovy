package com.lvg.weldercenter.se.repositories

import com.lvg.weldercenter.se.models.Organization
import org.springframework.data.jpa.repository.JpaRepository

interface OrganizationRepository extends JpaRepository<Organization, Long>{
    void delete(Organization organization)
}