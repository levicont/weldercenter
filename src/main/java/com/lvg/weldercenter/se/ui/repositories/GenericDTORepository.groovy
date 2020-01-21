package com.lvg.weldercenter.se.ui.repositories

interface  GenericDTORepository<T> {
    void refreshAllDTO(javafx.collections.ObservableList<T> list)
    void loadAllDTO()

}