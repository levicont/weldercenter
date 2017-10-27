package com.lvg.weldercenter.se.services.impl

import com.lvg.weldercenter.se.models.Teacher
import com.lvg.weldercenter.se.repositories.TeacherRepository
import com.lvg.weldercenter.se.services.TeacherService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class TeacherServiceImpl implements TeacherService{

    @Autowired
    TeacherRepository repository

    @Override
    Teacher save(Teacher teacher) {
        return repository.save(teacher)
    }

    @Override
    Teacher get(Long id) {
        return repository.findOne(id)
    }

    @Override
    List<Teacher> getAll() {
        return repository.findAll()
    }

    @Override
    void delete(Teacher teacher) {
        repository.delete(teacher)
    }
}
