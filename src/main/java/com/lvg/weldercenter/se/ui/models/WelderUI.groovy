package com.lvg.weldercenter.se.ui.models

import com.lvg.weldercenter.se.models.Welder
import javafx.beans.property.SimpleLongProperty

class WelderUI extends GenericModelUI{


    WelderUI(Welder welder){
        this.id = new SimpleLongProperty(welder.id)
    }
}
