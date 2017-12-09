package com.lvg.weldercenter.se.ui.repositories.impl

import com.lvg.weldercenter.se.ui.dto.OrganizationDTO
import com.lvg.weldercenter.se.ui.listeners.welderspane.LoadAllOrganizationsChangeStateListener
import com.lvg.weldercenter.se.ui.repositories.OrganizationDTORepository
import com.lvg.weldercenter.se.ui.services.LoadingAllOrganizationsService
import com.lvg.weldercenter.se.ui.utils.ServiceUtils
import javafx.beans.InvalidationListener
import javafx.beans.property.ListProperty
import javafx.beans.property.SimpleListProperty
import javafx.collections.FXCollections
import javafx.collections.ObservableList
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class OrganizationDTORepositoryImpl implements OrganizationDTORepository{

    @Autowired
    LoadAllOrganizationsChangeStateListener listener
    @Autowired
    LoadingAllOrganizationsService service

    private ObservableList<OrganizationDTO> allOrganizations = FXCollections.observableArrayList()
    private final ListProperty<OrganizationDTO> allOrganizationListProperty =
            new SimpleListProperty<>(allOrganizations)
    private final ListProperty<String> allOrganizationNamesListProperty =
            new SimpleListProperty<>(FXCollections.observableArrayList())


    OrganizationDTORepositoryImpl(){
        initListaners()
    }

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
        service.stateProperty().addListener(listener)
        ServiceUtils.startService(service)
    }

    @Override
    ObservableList<String> organizationNameList() {
        return allOrganizationNamesListProperty.get()
    }

    @Override
    ListProperty<String> organizationNameListProperty() {
        return allOrganizationNamesListProperty
    }

    private initListaners(){
        allOrganizationListProperty.addListener((InvalidationListener){
            allOrganizationNamesListProperty.get().clear()
            allOrganizationListProperty.get().each { organizationDTO ->
                allOrganizationNamesListProperty.get().add(organizationDTO.name)}
        })
    }
}
