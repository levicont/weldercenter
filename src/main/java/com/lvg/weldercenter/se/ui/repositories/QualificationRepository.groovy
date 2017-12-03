package com.lvg.weldercenter.se.ui.repositories

import javafx.beans.property.ListProperty
import javafx.collections.ObservableList

interface QualificationRepository {
    void loadQualifications()
    void updateQualificationDTOList(List<String> list)
    ListProperty<String> qualificationsProperty()
}