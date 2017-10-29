package com.lvg.weldercenter.se.repositories

import com.lvg.weldercenter.se.models.Journal
import org.springframework.data.jpa.repository.JpaRepository

interface JournalRepository extends JpaRepository<Journal, Long>{
    void delete(Journal journal)
}