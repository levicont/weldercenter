package com.lvg.weldercenter.se.repositories

import com.lvg.weldercenter.se.models.Job
import org.springframework.data.jpa.repository.JpaRepository

interface JobRepository extends JpaRepository<Job, Long>{

}