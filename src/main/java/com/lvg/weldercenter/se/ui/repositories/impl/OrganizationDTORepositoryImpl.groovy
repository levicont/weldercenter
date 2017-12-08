package com.lvg.weldercenter.se.ui.repositories.impl

import com.lvg.weldercenter.se.ui.dto.OrganizationDTO
import com.lvg.weldercenter.se.ui.repositories.OrganizationDTORepository
import javafx.beans.property.ListProperty
import javafx.beans.property.SimpleListProperty
import javafx.collections.FXCollections
import javafx.collections.ObservableList
import org.apache.log4j.Logger
import org.springframework.stereotype.Component

@Component
class OrganizationDTORepositoryImpl implements OrganizationDTORepository{
    private static final Logger LOGGER = Logger.getLogger(OrganizationDTORepositoryImpl.class)

    private ObservableList<OrganizationDTO> allOrganizations = FXCollections.observableArrayList()
    private final ListProperty<OrganizationDTO> allOrganizationListProperty =
            new SimpleListProperty<>(allOrganizations)


    @Override
    ListProperty<OrganizationDTO> getAllDTO() {
        return allOrganizationListProperty
    }

    @Override
    void refreshAllDTO(ObservableList<OrganizationDTO> list) {
        allOrganizations.clear()
        allOrganizations.addAll(list)
    }

    @Override
    void loadAllDTO() {

    }
}
