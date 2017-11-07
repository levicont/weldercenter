package com.lvg.weldercenter.se.ui.repositories.impl

import com.lvg.weldercenter.se.models.Organization
import com.lvg.weldercenter.se.models.Welder
import com.lvg.weldercenter.se.ui.models.WelderUI
import com.lvg.weldercenter.se.ui.repositories.WeldersRepository
import com.lvg.weldercenter.se.ui.services.LoadingWeldersService
import javafx.beans.property.ObjectProperty
import javafx.beans.property.SimpleObjectProperty
import javafx.collections.FXCollections
import javafx.collections.ObservableList
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

import java.time.LocalDate

@Component
class WeldersReposotoryImpl implements WeldersRepository{

    private ObservableList<WelderUI> allWelders = FXCollections.observableArrayList()

    private final ObjectProperty<ObservableList<WelderUI>> allWeldersProperty =
            new SimpleObjectProperty<ObservableList<WelderUI>>()



    @Autowired
    LoadingWeldersService loadingWeldersService

    WeldersReposotoryImpl(){
       allWelders.add(new WelderUI(getWelder()))
    }



    @Override
    ObjectProperty<ObservableList<WelderUI>> getAllWelders() {
        return allWeldersProperty
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
