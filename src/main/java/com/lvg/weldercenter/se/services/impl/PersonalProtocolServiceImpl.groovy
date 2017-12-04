package com.lvg.weldercenter.se.services.impl

import com.lvg.weldercenter.se.models.PersonalProtocol
import com.lvg.weldercenter.se.repositories.PersonalProtocolRepository
import com.lvg.weldercenter.se.services.PersonalProtocolService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class PersonalProtocolServiceImpl implements PersonalProtocolService{

    @Autowired
    PersonalProtocolRepository repository

    @Override
    PersonalProtocol save(PersonalProtocol personalProtocol) {
        return repository.save(personalProtocol)
    }

    @Override
    PersonalProtocol get(Long id) {
        return repository.findOne(id)
    }

    @Override
    List<PersonalProtocol> getAll() {
        return repository.findAll()
    }

    @Override
    void delete(PersonalProtocol personalProtocol) {
        repository.delete(personalProtocol)
    }

    @Override
    Long count() {
        return repository.count()
    }
}
