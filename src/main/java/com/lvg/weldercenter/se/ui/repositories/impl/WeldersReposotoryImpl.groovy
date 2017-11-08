package com.lvg.weldercenter.se.ui.repositories.impl

import com.lvg.weldercenter.se.models.Organization
import com.lvg.weldercenter.se.models.Welder
import com.lvg.weldercenter.se.ui.listeners.welderspane.LoadWeldersChangeListener
import com.lvg.weldercenter.se.ui.models.WelderUI
import com.lvg.weldercenter.se.ui.repositories.WeldersRepository
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

    private final ObjectProperty<ObservableList<WelderUI>> allWeldersProperty =
            new SimpleObjectProperty<ObservableList<WelderUI>>(allWelders)



    @Autowired
    LoadingWeldersService loadingWeldersService

    @Autowired
    LoadWeldersChangeListener changeStateServiceListener

    WeldersReposotoryImpl(){
       allWelders.add(new WelderUI(getWelder()))
    }



    @Override
    ObjectProperty<ObservableList<WelderUI>> getAllWelders() {
        return allWeldersProperty
    }

    void updateWeldersList(){
        if (loadingWeldersService.isOnceStarted())
            loadingWeldersService.restart()
        else{
            loadingWeldersService.start()
            loadingWeldersService.onceStarted = true
        }
        loadingWeldersService.stateProperty().addListener(changeStateServiceListener)
    }

    @Override
    void updateWeldersList(ObservableList<WelderUI> newWelderList) {
        this.allWelders.setAll(newWelderList)
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

}
