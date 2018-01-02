package com.lvg.weldercenter.se.ui.repositories

import com.lvg.weldercenter.se.ui.dto.OrganizationDTO
import javafx.beans.property.ListProperty
import javafx.collections.ObservableList

import java.util.function.Predicate

interface OrganizationDTORepository extends GenericDTORepository<OrganizationDTO>{
    ObservableList<String> organizationNameList()
    ListProperty<String> organizationNameListProperty()
    void setFilterPredicate(Predicate<? super OrganizationDTO> predicate)
}