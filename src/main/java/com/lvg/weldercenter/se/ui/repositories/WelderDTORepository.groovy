package com.lvg.weldercenter.se.ui.repositories

import com.lvg.weldercenter.se.ui.dto.WelderTableViewDTO
import com.lvg.weldercenter.se.ui.dto.WelderDTO
import javafx.beans.property.ListProperty
import javafx.beans.property.ObjectProperty
import javafx.collections.ObservableList

interface WelderDTORepository {

    ListProperty<WelderTableViewDTO> welderTableViewDTOListProperty()
    WelderDTO getNewWelderDTO()
    void updateWeldersListForTableView(ObservableList<WelderTableViewDTO> newWelderList)
    void reloadWelders()
    void removeUnsavedItems()
    void filter(String searchString)
    void saveWelderDTO(WelderDTO welderDTO)
}
