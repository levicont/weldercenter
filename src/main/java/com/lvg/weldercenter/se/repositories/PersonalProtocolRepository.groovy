package com.lvg.weldercenter.se.repositories

import com.lvg.weldercenter.se.models.PersonalProtocol
import org.springframework.data.jpa.repository.JpaRepository

interface PersonalProtocolRepository extends JpaRepository<PersonalProtocol, Long>{
    void delete(PersonalProtocol personalProtocol)
}