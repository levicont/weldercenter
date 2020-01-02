package com.lvg.weldercenter.se.services

import com.lvg.weldercenter.se.models.OrganizationEmbedded
import com.lvg.weldercenter.se.models.Welder
import com.lvg.weldercenter.se.ui.dto.WelderTableViewDTO

interface WelderService extends GenericService<Welder>{
    List<Object[]> getWeldersForTableView()
    List<WelderTableViewDTO> getAllWeldersTableViewDTO()
    Set<OrganizationEmbedded> getAllOrganization()
    Welder getFull(Long id)

}