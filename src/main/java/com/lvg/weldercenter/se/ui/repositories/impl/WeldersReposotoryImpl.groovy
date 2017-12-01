package com.lvg.weldercenter.se.ui.repositories.impl

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
class WeldersReposotoryImpl implements WelderDTORepository{

    private ObservableList<WelderDTO> allWelders =
            FXCollections.observableArrayList()
    private ObservableList<WelderTableViewDTO> allWeldersTableViewDTO = FXCollections.observableArrayList()

    private final ObjectProperty<ObservableList<WelderTableViewDTO>> allWeldersTableViewDTOProperty =
            new SimpleObjectProperty<>(allWeldersTableViewDTO)

    @Autowired
    LoadingWeldersForTableViewService loadingWeldersForTableViewService

    @Autowired
    LoadWeldersForTableViewChangeStateListener loadWeldersForTableViewChangeStateListener
    private static final Logger LOGGER = Logger.getLogger(WeldersReposotoryImpl.class)

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
    void reloadWelders(){
        loadingWeldersForTableViewService.stateProperty().addListener(loadWeldersForTableViewChangeStateListener)
        ServiceUtils.startService(loadingWeldersForTableViewService)
        LOGGER.debug("Load welders performed")
    }

}
