package com.lvg.weldercenter.se.ui.repositories.impl

import com.lvg.weldercenter.se.models.Organization
import com.lvg.weldercenter.se.models.Welder
import com.lvg.weldercenter.se.ui.dto.WelderTableViewDTO
import com.lvg.weldercenter.se.ui.listeners.welderspane.LoadWeldersChangeListener
import com.lvg.weldercenter.se.ui.dto.WelderUI
import com.lvg.weldercenter.se.ui.listeners.welderspane.LoadWeldersForTableViewChangeStateListener
import com.lvg.weldercenter.se.ui.repositories.WeldersRepository
import com.lvg.weldercenter.se.ui.services.LoadingWeldersForTableViewService
import com.lvg.weldercenter.se.ui.services.LoadingWeldersService
import javafx.beans.property.ObjectProperty
import javafx.beans.property.SimpleObjectProperty
import javafx.collections.FXCollections
import javafx.collections.ObservableList
import org.apache.log4j.Logger
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

import java.time.LocalDate

@Component
class WeldersReposotoryImpl implements WeldersRepository{
    private static final Logger LOGGER = Logger.getLogger(WeldersReposotoryImpl.class)

    private ObservableList<WelderUI> allWelders =
            FXCollections.observableArrayList()
    private ObservableList<WelderTableViewDTO> allWeldersTableViewDTO = FXCollections.observableArrayList()

    private final ObjectProperty<ObservableList<WelderTableViewDTO>> allWeldersTableViewDTOProperty =
            new SimpleObjectProperty<>(allWeldersTableViewDTO)
    private final ObjectProperty<ObservableList<WelderUI>> allWeldersProperty =
            new SimpleObjectProperty<ObservableList<WelderUI>>(allWelders)



    @Autowired
    LoadingWeldersService loadingWeldersService
    @Autowired
    LoadingWeldersForTableViewService loadingWeldersForTableViewService

    @Autowired
    LoadWeldersChangeListener changeStateServiceListener
    @Autowired
    LoadWeldersForTableViewChangeStateListener loadWeldersForTableViewChangeStateListener

    WeldersReposotoryImpl(){
      allWeldersTableViewDTO.add(getWelderDTO())
    }



    @Override
    ObjectProperty<ObservableList<WelderUI>> getAllWelders() {
        return allWeldersProperty
    }

    void updateWeldersList(){
        if (loadingWeldersService.isStartedOnce())
            loadingWeldersService.restart()
        else{
            loadingWeldersService.start()
            loadingWeldersService.setStartedOnceFlag(true)
        }
        loadingWeldersService.stateProperty().addListener(changeStateServiceListener)
    }

    @Override
    void updateWeldersList(ObservableList<WelderUI> newWelderList) {
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

    ObservableList<WelderUI> showAllWelders(){
        allWeldersProperty.set(allWelders)
    }

    void addWelder(WelderUI welderUI){
        allWelders.add(welderUI)
    }

    void removeWelder(WelderUI welderUI){
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
