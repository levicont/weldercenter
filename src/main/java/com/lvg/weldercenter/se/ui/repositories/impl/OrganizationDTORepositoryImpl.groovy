package com.lvg.weldercenter.se.ui.repositories.impl

import com.lvg.weldercenter.se.ui.dto.OrganizationDTO
import com.lvg.weldercenter.se.ui.listeners.welderspane.LoadAllOrganizationsChangeStateListener
import com.lvg.weldercenter.se.ui.listeners.welderspane.SaveOrganizationDTOChangeStateListener
import com.lvg.weldercenter.se.ui.repositories.OrganizationDTORepository
import com.lvg.weldercenter.se.ui.services.LoadingAllOrganizationsService
import com.lvg.weldercenter.se.ui.services.SaveOrganizatioDTOService
import com.lvg.weldercenter.se.ui.utils.ServiceUtils
import javafx.application.Platform
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
    @Autowired
    SaveOrganizatioDTOService saveOrganizatioDTOService
    @Autowired
    SaveOrganizationDTOChangeStateListener saveOrganizationDTOChangeStateListener

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
        OrganizationDTO updatedOrganization = null
        if (!validOrganizationDTO(organizationDTO)){
            LOGGER.warn("Save OrganizationDTO: cannot save OrganizationDTO because it is not valid")
            updatedOrganization = OrganizationDTO.getDefaultOrganizationDTO()
            return
        }


        allData.stream().forEach({org ->
            if (org.id == organizationDTO.id){
                updatedOrganization = org
            }
        })

        saveOrganizatioDTOService.setOrganizationDTO(organizationDTO)
        saveOrganizatioDTOService.stateProperty().addListener(saveOrganizationDTOChangeStateListener)
        ServiceUtils.startService(saveOrganizatioDTOService)
    }
    private boolean validOrganizationDTO(OrganizationDTO organizationDTO){
        if (organizationDTO.nameProperty().get().isEmpty()) return false
        return true
    }

    @Override
    ObservableList<OrganizationDTO> getAllOrganizationDTOFromDB() {
        return Collections.unmodifiableList(allData)
    }

    @Override
    OrganizationDTO findOrganizationDTOByName(String name){
        OrganizationDTO result = null
        allData.forEach({ org ->
            if (org.name == name)
                result = org
        })
        return result

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
