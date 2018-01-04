package com.lvg.weldercenter.se.ui.repositories.impl

import com.lvg.weldercenter.se.ui.dto.DTOConstants
import com.lvg.weldercenter.se.ui.dto.WelderDTO
import com.lvg.weldercenter.se.ui.dto.WelderTableViewDTO
import com.lvg.weldercenter.se.ui.listeners.welderspane.LoadWeldersForTableViewChangeStateListener
import com.lvg.weldercenter.se.ui.repositories.WelderDTORepository
import com.lvg.weldercenter.se.ui.services.LoadingWeldersForTableViewService
import com.lvg.weldercenter.se.ui.utils.ServiceUtils
import javafx.beans.property.ObjectProperty
import javafx.beans.property.SimpleObjectProperty
import javafx.collections.FXCollections
import javafx.collections.ObservableList
import org.apache.log4j.Logger
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class WeldersRepositoryImpl implements WelderDTORepository {

    private ObservableList<WelderDTO> allWelders =
            FXCollections.observableArrayList()
    private ObservableList<WelderTableViewDTO> allWeldersTableViewDTO = FXCollections.observableArrayList()
    private final ObjectProperty<WelderDTO> welderDTOProperty = new SimpleObjectProperty<>()
    private final ObjectProperty<ObservableList<WelderTableViewDTO>> allWeldersTableViewDTOProperty =
            new SimpleObjectProperty<>(allWeldersTableViewDTO)

    @Autowired
    LoadingWeldersForTableViewService loadingWeldersForTableViewService

    @Autowired
    LoadWeldersForTableViewChangeStateListener loadWeldersForTableViewChangeStateListener
    private static final Logger LOGGER = Logger.getLogger(WeldersRepositoryImpl.class)

    @Override
    void updateWeldersList(ObservableList<WelderDTO> newWelderList) {
        this.allWelders.setAll(newWelderList)
    }

    @Override
    ObjectProperty<ObservableList<WelderTableViewDTO>> getAllWeldersForTableView() {
        return allWeldersTableViewDTOProperty
    }

    @Override
    void updateWeldersListForTableView(ObservableList<WelderTableViewDTO> newWelderList) {
        this.allWeldersTableViewDTO.setAll(newWelderList)
    }

    @Override
    void reloadWelders() {
        loadingWeldersForTableViewService.stateProperty().addListener(loadWeldersForTableViewChangeStateListener)
        ServiceUtils.startService(loadingWeldersForTableViewService)
        LOGGER.debug("Load welders performed")
    }

    @Override
    WelderDTO getNewWelderDTO() {
        def welderTableViewDTO = getDefaultWelderTableViewDTO()
        if (!allWeldersTableViewDTO.contains(welderTableViewDTO)) {
            allWeldersTableViewDTO.add(welderTableViewDTO)
        }
        return welderDTOProperty.get()
    }

    void clearWelderTableViewDTOList(){
        Optional<WelderTableViewDTO> emptyWelderTableViewDTO = allWeldersForTableView.get()
                .stream()
                .filter({welder -> welder.id==DTOConstants.NULL_ID_FIELD_DEFAULT})
                .findFirst()
        if(emptyWelderTableViewDTO.isPresent()){
            allWeldersForTableView.get().remove(emptyWelderTableViewDTO.get())
        }
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
