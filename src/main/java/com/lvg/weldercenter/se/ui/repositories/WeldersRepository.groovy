package com.lvg.weldercenter.se.ui.repositories

import com.lvg.weldercenter.se.ui.models.WelderUI
import javafx.beans.property.ObjectProperty
import javafx.collections.ObservableList

interface WeldersRepository {
    ObjectProperty<ObservableList<WelderUI>> getAllWelders()
    void updateWeldersList()
    void updateWeldersList(ObservableList<WelderUI> newWelderList)
}
