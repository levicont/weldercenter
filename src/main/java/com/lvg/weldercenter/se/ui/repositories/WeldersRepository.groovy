package com.lvg.weldercenter.se.ui.repositories

import com.lvg.weldercenter.se.ui.dto.WelderTableViewDTO
import com.lvg.weldercenter.se.ui.dto.WelderUI
import javafx.beans.property.ObjectProperty
import javafx.collections.ObservableList

interface WeldersRepository {
    ObjectProperty<ObservableList<WelderUI>> getAllWelders()
    void updateWeldersList()
    void updateWeldersList(ObservableList<WelderUI> newWelderList)

    ObjectProperty<ObservableList<WelderTableViewDTO>> getAllWeldersForTableView()
    void updateWeldersListForTableView()
    void updateWeldersListForTableView(ObservableList<WelderTableViewDTO> newWelderList)
}
