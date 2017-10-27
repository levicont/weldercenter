package com.lvg.weldercenter.se.repositories

import com.lvg.weldercenter.se.models.Teacher
import org.springframework.data.jpa.repository.JpaRepository

interface TeacherRepository extends JpaRepository<Teacher, Long>{
    void delete(Teacher teacher)

}