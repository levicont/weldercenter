package com.lvg.weldercenter.se.ui.repositories.impl

import com.lvg.weldercenter.se.models.Organization
import com.lvg.weldercenter.se.models.Welder
import com.lvg.weldercenter.se.ui.dto.WelderDTO
import com.lvg.weldercenter.se.ui.dto.WelderTableViewDTO
import com.lvg.weldercenter.se.ui.listeners.welderspane.LoadWeldersForTableViewChangeStateListener
import com.lvg.weldercenter.se.ui.repositories.WelderDTORepository
import com.lvg.weldercenter.se.ui.services.LoadingWeldersForTableViewService
import javafx.beans.property.ObjectProperty
import javafx.beans.property.SimpleObjectProperty
import javafx.collections.FXCollections
import javafx.collections.ObservableList
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

import java.time.LocalDate

@Component
class WeldersReposotoryImpl implements WelderDTORepository{

    private ObservableList<WelderDTO> allWelders =
            FXCollections.observableArrayList()
    private ObservableList<WelderTableViewDTO> allWeldersTableViewDTO = FXCollections.observableArrayList()

    private final ObjectProperty<ObservableList<WelderTableViewDTO>> allWeldersTableViewDTOProperty =
            new SimpleObjectProperty<>(allWeldersTableViewDTO)
    private final ObjectProperty<ObservableList<WelderDTO>> allWeldersProperty =
            new SimpleObjectProperty<ObservableList<WelderDTO>>(allWelders)

    @Autowired
    LoadingWeldersForTableViewService loadingWeldersForTableViewService

    @Autowired
    LoadWeldersForTableViewChangeStateListener loadWeldersForTableViewChangeStateListener

    WeldersReposotoryImpl(){
      allWeldersTableViewDTO.add(getWelderDTO())
    }

    @Override
    void updateWeldersList(ObservableList<WelderDTO> newWelderList) {
        this.allWelders.setAll(newWelderList)
    }

    @Override
    ObjectProperty<ObservableList<WelderTableViewDTO>> getAllWeldersForTableView() {
        return allWeldersTableViewDTOProperty
    }

    @Override
    void updateWeldersListForTableView() {
        if (loadingWeldersForTableViewService.isStartedOnce())
            loadingWeldersForTableViewService.restart()
        else{
            loadingWeldersForTableViewService.start()
            loadingWeldersForTableViewService.setStartedOnceFlag(true)
        }
        loadingWeldersForTableViewService.stateProperty().addListener(loadWeldersForTableViewChangeStateListener)
    }

    @Override
    void updateWeldersListForTableView(ObservableList<WelderTableViewDTO> newWelderList) {
        this.allWeldersTableViewDTO.setAll(newWelderList)
    }

    ObservableList<WelderDTO> showAllWelders(){
        allWeldersProperty.set(allWelders)
    }

    void addWelder(WelderDTO welderUI){
        allWelders.add(welderUI)
    }

    void removeWelder(WelderDTO welderUI){
        allWelders.remove(welderUI)
    }

    private static Welder getWelder() {
        def welder = new Welder(name: 'Иван', surname: 'Иванов', secondName: 'Иванович')
        welder.id = 100
        welder.birthday = LocalDate.of(1984, 10, 28)
        welder.dateBegin = LocalDate.of(2000, 10, 28)
        welder.documentNumber = '17-033/17'
        welder.address = 'Michigan City 12066'
        welder.education = 'среднее-специальное'
        welder.qualification = 'электросварщик'
        welder.job = 'элекросварщик'
        welder.organization = getOrganization()
        return welder
    }

    private static Organization getOrganization() {
        return new Organization(id: 100, name: 'IBM', address: 'New-York', phone: '(0595)466-15-59')
    }

    private static WelderTableViewDTO getWelderDTO(){
        Welder welder = getWelder()
        WelderTableViewDTO result = new WelderTableViewDTO(welder.id, welder.name, welder.surname, welder.secondName,
        welder.birthday,welder.organization.name)
        return result
    }

}
