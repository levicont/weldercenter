package com.lvg.weldercenter.se.ui.repositories

import com.lvg.weldercenter.se.ui.dto.JobDTO
import javafx.beans.property.ListProperty
import javafx.collections.ObservableList

interface JobDTORepository extends GenericDTORepository<JobDTO>{
    ObservableList<String> jobsNameList()
    ListProperty<String> jobsNameListProperty()
}