package com.lvg.weldercenter.se.ui.repositories

import javafx.beans.property.ListProperty

interface  GenericDTORepository<T> {
    ListProperty<T> getAllDTO()
    void refreshAllDTO(javafx.collections.ObservableList<T> list)
    void loadAllDTO()

}