package com.lvg.weldercenter.se.ui.repositories.impl

import com.lvg.weldercenter.se.ui.dto.DTOConstants
import com.lvg.weldercenter.se.ui.dto.WelderDTO
import com.lvg.weldercenter.se.ui.dto.WelderTableViewDTO
import com.lvg.weldercenter.se.ui.listeners.welderspane.LoadWeldersForTableViewChangeStateListener
import com.lvg.weldercenter.se.ui.listeners.welderspane.SaveWelderDTOChangeStateListener
import com.lvg.weldercenter.se.ui.repositories.WelderDTORepository
import com.lvg.weldercenter.se.ui.services.LoadingWeldersForTableViewService
import com.lvg.weldercenter.se.ui.services.SaveWelderDTOService
import com.lvg.weldercenter.se.ui.utils.ServiceUtils
import javafx.beans.property.ListProperty
import javafx.beans.property.ObjectProperty
import javafx.beans.property.SimpleListProperty
import javafx.beans.property.SimpleObjectProperty
import javafx.collections.FXCollections
import javafx.collections.ObservableList
import javafx.collections.ObservableMap
import javafx.collections.transformation.FilteredList
import org.apache.log4j.Logger
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

import java.util.function.Predicate
import java.util.stream.Collectors

@Component
class WeldersRepositoryImpl implements WelderDTORepository {
    //TODO Have to implement welderDTO buffer
    private final ObservableMap<Long, WelderDTO> welderDTOBuffer =
            FXCollections.observableHashMap()
    private final ObservableList<WelderTableViewDTO> welderTableViewDTOObservableList = FXCollections.observableArrayList()
    private final ObjectProperty<WelderDTO> welderDTOProperty = new SimpleObjectProperty<>()
    private final ListProperty<WelderTableViewDTO> welderTableViewDTOListProperty =
            new SimpleListProperty<>(welderTableViewDTOObservableList)
    private final FilteredList<WelderTableViewDTO> filteredList =
            new FilteredList<>(welderTableViewDTOObservableList, {true})

    @Autowired
    LoadingWeldersForTableViewService loadingWeldersForTableViewService

    @Autowired
    LoadWeldersForTableViewChangeStateListener loadWeldersForTableViewChangeStateListener
    private static final Logger LOGGER = Logger.getLogger(WeldersRepositoryImpl.class)

    @Autowired
    SaveWelderDTOService saveWelderDTOService
    @Autowired
    SaveWelderDTOChangeStateListener saveWelderDTOChangeStateListener


    @Override
    ListProperty<WelderTableViewDTO> welderTableViewDTOListProperty() {
        return welderTableViewDTOListProperty
    }

    @Override
    void updateWeldersListForTableView(ObservableList<WelderTableViewDTO> newWelderList) {
        filteredList.setPredicate({true})
        welderTableViewDTOListProperty.clear()
        welderTableViewDTOListProperty.addAll(newWelderList)
    }

    @Override
    void reloadWelders() {
        removeUnsavedItems()
        loadingWeldersForTableViewService.stateProperty().addListener(loadWeldersForTableViewChangeStateListener)
        ServiceUtils.startService(loadingWeldersForTableViewService)
        LOGGER.debug("Load welders performed")
    }

    @Override
    WelderDTO getNewWelderDTO() {
        def welderTableViewDTO = getDefaultWelderTableViewDTO()
        if (!welderTableViewDTOListProperty.contains(welderTableViewDTO)) {
            welderTableViewDTOListProperty.add(welderTableViewDTO)
        }
        return welderDTOProperty.get()
    }

    void filter(String searchString){
        if (searchString == null || searchString.isEmpty()){
            welderTableViewDTOListProperty.set(welderTableViewDTOObservableList)
            return
        }
        welderTableViewDTOListProperty.set(filteredList)
        filteredList.predicateProperty().set(new Predicate<WelderTableViewDTO>() {
            @Override
            boolean test(WelderTableViewDTO welderTableDTO) {
                String lowCaseString = searchString.toLowerCase()
                return  welderTableDTO.getNameProperty().get().toLowerCase().contains(lowCaseString) ||
                    welderTableDTO.secondNameProperty.get().toLowerCase().contains(lowCaseString) ||
                    welderTableDTO.surnameProperty.get().toLowerCase().contains(lowCaseString) ||
                    welderTableDTO.organizationProperty.get().toLowerCase().contains(lowCaseString)
            }
        })

    }

    void saveWelderDTO(WelderDTO welderDTO){

        saveWelderDTOService.setWelderDTO(welderDTO)
        saveWelderDTOService.stateProperty().addListener(saveWelderDTOChangeStateListener)
        ServiceUtils.startService(saveWelderDTOService)
    }

    void removeUnsavedItems(){
        ArrayList<WelderTableViewDTO> removedList = welderTableViewDTOListProperty.stream()
            .filter({welderTableViewDTO -> welderTableViewDTO.id == DTOConstants.NULL_ID_FIELD_DEFAULT})
            .collect(Collectors.toList())
        welderTableViewDTOListProperty.removeAll(removedList)
    }

    private static WelderTableViewDTO getDefaultWelderTableViewDTO() {
        new WelderTableViewDTO(DTOConstants.NULL_ID_FIELD_DEFAULT,
                DTOConstants.NULL_FIELD_PLACEHOLDER,
                DTOConstants.NULL_FIELD_PLACEHOLDER,
                DTOConstants.NULL_FIELD_PLACEHOLDER,
                DTOConstants.DEFAULT_BIRTHDAY_PLACEHOLDER,
                DTOConstants.NULL_FIELD_PLACEHOLDER)
    }


}
