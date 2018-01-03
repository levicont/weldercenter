package com.lvg.weldercenter.se.ui.repositories

import com.lvg.weldercenter.se.ui.dto.WelderTableViewDTO
import com.lvg.weldercenter.se.ui.dto.WelderDTO
import javafx.beans.property.ObjectProperty
import javafx.collections.ObservableList

interface WelderDTORepository {
    void updateWeldersList(ObservableList<WelderDTO> newWelderList)

    ObjectProperty<ObservableList<WelderTableViewDTO>> getAllWeldersForTableView()
    WelderDTO addNewWelderDTO()
    void updateWeldersListForTableView(ObservableList<WelderTableViewDTO> newWelderList)
    void reloadWelders()
}
