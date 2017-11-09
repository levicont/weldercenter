package com.lvg.weldercenter.se.services

import com.lvg.weldercenter.se.models.Welder
import com.lvg.weldercenter.se.ui.dto.WelderTableViewDTO

interface WelderService extends GenericService<Welder>{
    long count()
    List<Object[]> getWeldersForTableView()
    List<WelderTableViewDTO> getAllWeldersTableViewDTO()
}