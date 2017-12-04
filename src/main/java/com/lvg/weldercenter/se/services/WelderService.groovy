package com.lvg.weldercenter.se.services

import com.lvg.weldercenter.se.models.Welder
import com.lvg.weldercenter.se.ui.dto.WelderTableViewDTO

interface WelderService extends GenericService<Welder>{
    List<Object[]> getWeldersForTableView()
    List<WelderTableViewDTO> getAllWeldersTableViewDTO()
    Welder getFull(Long id)
}