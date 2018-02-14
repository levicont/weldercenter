package com.lvg.weldercenter.se.ui.repositories.impl

import com.lvg.weldercenter.se.ui.dto.DTOConstants
import com.lvg.weldercenter.se.ui.dto.WelderDTO
import com.lvg.weldercenter.se.ui.dto.WelderTableViewDTO
import com.lvg.weldercenter.se.ui.listeners.welderspane.LoadWeldersForTableViewChangeStateListener
import com.lvg.weldercenter.se.ui.repositories.WelderDTORepository
import com.lvg.weldercenter.se.ui.services.LoadingWeldersForTableViewService
import com.lvg.weldercenter.se.ui.utils.ServiceUtils
import javafx.beans.property.ListProperty
import javafx.beans.property.ObjectProperty
import javafx.beans.property.SimpleListProperty
import javafx.beans.property.SimpleObjectProperty
import javafx.collections.FXCollections
import javafx.collections.ObservableList
import javafx.collections.ObservableMap
import org.apache.log4j.Logger
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

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

    @Autowired
    LoadingWeldersForTableViewService loadingWeldersForTableViewService

    @Autowired
    LoadWeldersForTableViewChangeStateListener loadWeldersForTableViewChangeStateListener
    private static final Logger LOGGER = Logger.getLogger(WeldersRepositoryImpl.class)

    @Override
    ListProperty<WelderTableViewDTO> welderTableViewDTOListProperty() {
        return welderTableViewDTOListProperty
    }

    @Override
    void updateWeldersListForTableView(ObservableList<WelderTableViewDTO> newWelderList) {
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
