package com.lvg.weldercenter.se.repositories

import com.lvg.weldercenter.se.models.NDTDocument
import org.springframework.data.jpa.repository.JpaRepository


interface NDTDocumentRepository extends JpaRepository<NDTDocument, Long>{
    void delete (NDTDocument ndtDocument)

}