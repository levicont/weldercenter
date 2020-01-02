package com.lvg.weldercenter.se.ui.repositories

import com.lvg.weldercenter.se.models.OrganizationEmbedded
import com.lvg.weldercenter.se.ui.dto.WelderTableViewDTO
import com.lvg.weldercenter.se.ui.dto.WelderDTO
import javafx.beans.property.ListProperty
import javafx.beans.property.ObjectProperty
import javafx.collections.ObservableList
import sun.awt.EmbeddedFrame

interface WelderDTORepository {

    ListProperty<WelderTableViewDTO> welderTableViewDTOListProperty()
    WelderDTO getNewWelderDTO()
    Set<OrganizationEmbedded> getAllOrganizations()
    void updateWeldersListForTableView(ObservableList<WelderTableViewDTO> newWelderList)
    void reloadWelders()
    void removeUnsavedItems()
    void filter(String searchString)
    void saveWelderDTO(WelderDTO welderDTO)
    void updateOrganizationEmbeddedSet(javafx.collections.ObservableSet<OrganizationEmbedded> organizationEmbeddedSet)
}
