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
import javafx.collections.transformation.FilteredList
import org.apache.log4j.Logger
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

import java.util.function.Predicate

@Component
class OrganizationDTORepositoryImpl implements OrganizationDTORepository{
    private static final Logger LOGGER = Logger.getLogger(OrganizationDTORepositoryImpl.class)

    @Autowired
    LoadAllOrganizationsChangeStateListener listener
    @Autowired
    LoadingAllOrganizationsService service

    private ObservableList<OrganizationDTO> allData = FXCollections.observableArrayList()
    private final FilteredList<OrganizationDTO> filteredData =
            new FilteredList<>(allData, {e -> true})
    private final ListProperty<OrganizationDTO> allOrganizationListProperty =
            new SimpleListProperty<>(filteredData)
    private final ListProperty<String> allOrganizationNamesListProperty =
            new SimpleListProperty<>(FXCollections.observableArrayList())



    OrganizationDTORepositoryImpl(){
        initListeners()
    }

    @Override
    ListProperty<OrganizationDTO> getAllDTO() {
        return allOrganizationListProperty
    }

    @Override
    void refreshAllDTO(ObservableList<OrganizationDTO> list) {
        LOGGER.debug("-- Refreshing OrganizationDTO allDTO Before cleaning list has size: ${allData.size()}")
        allData.clear()
        LOGGER.debug("-- Refreshing OrganizationDTO allDTO After cleaning list has size: ${allData.size()}")
        allData.addAll(list)
        LOGGER.debug("-- Refreshing OrganizationDTO allDTO After refreshing list has size: ${allData.size()}")
    }

    @Override
    void loadAllDTO() {
        service.stateProperty().addListener(listener)
        ServiceUtils.startService(service)
    }

    void setFilterPredicate(Predicate<? super OrganizationDTO> predicate){
        filteredData.setPredicate(predicate)
    }

    @Override
    ObservableList<String> organizationNameList() {
        return allOrganizationNamesListProperty.get()
    }

    @Override
    ListProperty<String> organizationNameListProperty() {
        return allOrganizationNamesListProperty
    }

    private initListeners(){
        allOrganizationListProperty.addListener((InvalidationListener){
            allOrganizationNamesListProperty.get().clear()
            allOrganizationListProperty.get().each { organizationDTO ->
                allOrganizationNamesListProperty.get().add(organizationDTO.name)}
        })


    }
}
