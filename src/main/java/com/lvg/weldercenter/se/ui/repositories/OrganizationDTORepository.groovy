package com.lvg.weldercenter.se.ui.repositories

import com.lvg.weldercenter.se.ui.dto.OrganizationDTO
import javafx.beans.property.ListProperty
import javafx.collections.ObservableList

import java.util.function.Predicate

interface OrganizationDTORepository extends GenericDTORepository<OrganizationDTO>{
    void setFilterPredicate(Predicate<? super OrganizationDTO> predicate)
    void setFilteredOrganizationName(String name)
    void saveOrganizationDTO(OrganizationDTO organizationDTO)
    ObservableList<OrganizationDTO> getAllOrganizationDTOFromDB()
}