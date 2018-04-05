package com.lvg.weldercenter.se.ui.repositories.impl

import com.lvg.weldercenter.se.ui.dto.OrganizationDTO
import com.lvg.weldercenter.se.ui.listeners.welderspane.LoadAllOrganizationsChangeStateListener
import com.lvg.weldercenter.se.ui.repositories.OrganizationDTORepository
import com.lvg.weldercenter.se.ui.services.LoadingAllOrganizationsService
import com.lvg.weldercenter.se.ui.utils.ServiceUtils
import javafx.beans.property.ListProperty
import javafx.beans.property.SimpleListProperty
import javafx.beans.property.SimpleStringProperty
import javafx.beans.property.StringProperty
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

    private final ObservableList<OrganizationDTO> allData = FXCollections.observableArrayList()
    private final FilteredList<OrganizationDTO> filteredData =
            new FilteredList<>(allData, new OrganizationPredicate('test'))
    private final ListProperty<OrganizationDTO> allOrganizationListProperty =
            new SimpleListProperty<>(filteredData)

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

    @Override
    void saveOrganizationDTO(OrganizationDTO organizationDTO) {
        LOGGER.debug("saveOrganizationDTO: START saving organizationDTO: ${organizationDTO}")
        Predicate<OrganizationDTO> predicate = filteredData.predicateProperty().get()
        OrganizationDTO updatedOrganization = null
        allData.stream().forEach({org ->
            if (org.id == organizationDTO.id){
                updatedOrganization = org
            }
        })

        if (updatedOrganization != null){
            LOGGER.debug("saveOrganizationDTO: updating existed organizationDTO: ${organizationDTO}")
            updatedOrganization.nameProperty().set(organizationDTO.nameProperty().get())
            updatedOrganization.addressProperty().set(organizationDTO.addressProperty().get())
            updatedOrganization.phoneProperty().set(organizationDTO.phoneProperty().get())
        }else {
            LOGGER.debug("saveOrganizationDTO: adding new organizationDTO: ${organizationDTO}")
            allData.add(organizationDTO)
        }


    }

    void setFilterPredicate(Predicate<? super OrganizationDTO> predicate){
        filteredData.setPredicate(predicate)
    }

    void setFilteredOrganizationName(String name){
        filteredData.predicateProperty().set(new OrganizationPredicate(name))
    }


    private class OrganizationPredicate implements Predicate<OrganizationDTO>{
        private final StringProperty checkedNameProperty = new SimpleStringProperty()

        OrganizationPredicate(String filteredName){
            checkedNameProperty.set(filteredName)
        }
        @Override
        boolean test(OrganizationDTO organizationDTO) {
            if (checkedNameProperty.get() == null) return true
            if (organizationDTO == null ) return true
            if (organizationDTO.nameProperty().get().isEmpty()) return true
            LOGGER.debug("OrganizationPredicate organizationDTO name checked: ${organizationDTO.nameProperty().get()} " +
                    "with value: ${checkedNameProperty.get()}")
            String nameLowerCase = organizationDTO.nameProperty().get().toLowerCase()
            return nameLowerCase.contains(checkedNameProperty.get().toLowerCase())
        }
    }


}
