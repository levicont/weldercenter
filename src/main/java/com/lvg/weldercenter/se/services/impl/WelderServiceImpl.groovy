package com.lvg.weldercenter.se.services.impl

import com.lvg.weldercenter.se.models.Organization
import com.lvg.weldercenter.se.models.Welder
import com.lvg.weldercenter.se.repositories.OrganizationRepository
import com.lvg.weldercenter.se.repositories.WelderRepository
import com.lvg.weldercenter.se.services.WelderService
import com.lvg.weldercenter.se.ui.dto.WelderTableViewDTO
import org.apache.log4j.Logger
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

import javax.transaction.Transactional
import java.time.LocalDate

@Service
class WelderServiceImpl implements WelderService{
    private static final Logger LOGGER = Logger.getLogger(WelderServiceImpl.class)

    @Autowired
    WelderRepository repository
    @Autowired
    OrganizationRepository organizationRepository

    @Override
    @Transactional
    Welder save(Welder welder) {
        LOGGER.debug("Saving welder: organization checking ... ")
        Organization org = welder.organization
        LOGGER.debug("Saving welder: organization is ${org}")
        Long orgId = null
        if (org != null){
            orgId = org.id
            LOGGER.debug("Saving welder: organization's id is ${orgId}")
        }
        if (orgId == null && org != null){
            LOGGER.debug("Saving welder: New organization found, saving")
            organizationRepository.save(org)
            LOGGER.debug("Saving welder: New organization has saved")
        }

        return repository.save(welder)
    }

    @Override
    Welder get(Long id) {
        return repository.findOne(id)
    }

    @Override
    List<Welder> getAll() {
        return repository.findAll()
    }

    @Override
    void delete(Welder welder) {
        repository.delete(welder)
    }

    @Override
    Long count() {
        return repository.count()
    }

    @Override
    List<Object[]> getWeldersForTableView() {
        return repository.getWeldersForTableView()
    }

    @Override
    List<WelderTableViewDTO> getAllWeldersTableViewDTO() {
        List<WelderTableViewDTO> result = new ArrayList<>()
        getWeldersForTableView().each {Object[] weldersList ->
            WelderTableViewDTO item = new WelderTableViewDTO((Long)weldersList[0],
                    (String)weldersList[1],
                    (String)weldersList[2],
                    (String)weldersList[3],
                    (LocalDate)weldersList[4],
                    (String)weldersList[5])
            result.add(item)
        }
        return result
    }

    @Override
    Welder getFull(Long id) {
        return repository.getFull(id)
    }
}
