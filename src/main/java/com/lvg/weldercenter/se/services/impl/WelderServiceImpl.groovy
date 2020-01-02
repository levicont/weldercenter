package com.lvg.weldercenter.se.services.impl

import com.lvg.weldercenter.se.models.OrganizationEmbedded
import com.lvg.weldercenter.se.models.Welder
import com.lvg.weldercenter.se.repositories.WelderRepository
import com.lvg.weldercenter.se.services.WelderService
import com.lvg.weldercenter.se.ui.dto.WelderTableViewDTO
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

import javax.transaction.Transactional
import java.time.LocalDate

@Service
class WelderServiceImpl implements WelderService{

    @Autowired
    WelderRepository repository

    @Override
    @Transactional
    Welder save(Welder welder) {
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
    Set<OrganizationEmbedded> getAllOrganization() {
        Set<OrganizationEmbedded> result = new TreeSet<OrganizationEmbedded>(new Comparator<OrganizationEmbedded>() {
            @Override
            int compare(OrganizationEmbedded o1, OrganizationEmbedded o2) {
                String orgName1 = o1.name
                String orgName2 = o2.name
                return orgName1.compareToIgnoreCase(orgName2)
            }
        })
        getAll().forEach({welder ->
            result.add(welder.organization)
        })
        return result
    }

    Welder getFull(Long id) {
        return repository.getFull(id)
    }
}
