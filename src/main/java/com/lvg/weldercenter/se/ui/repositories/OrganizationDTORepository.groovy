package com.lvg.weldercenter.se.ui.repositories

import com.lvg.weldercenter.se.ui.dto.OrganizationDTO
import javafx.beans.property.ListProperty
import javafx.collections.ObservableList

interface OrganizationDTORepository extends GenericDTORepository<OrganizationDTO>{
    ObservableList<String> organizationNameList()
    ListProperty<String> organizationNameListProperty()
}