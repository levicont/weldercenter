package com.lvg.weldercenter.ui.servicesui.impl;

import com.lvg.weldercenter.models.Welder;
import com.lvg.weldercenter.services.WelderService;
import com.lvg.weldercenter.spring.factories.ServiceFactory;
import com.lvg.weldercenter.ui.entities.WelderUI;
import com.lvg.weldercenter.ui.servicesui.WelderServiceUI;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by Victor Levchenko LVG Corp. on 19.02.15.
 */
public class WelderUIManager implements WelderServiceUI {

    @Autowired
    private WelderService welderService;

    @Override
    public Welder getWelderFromWelderUI(WelderUI welderUI) {
        Welder result;
        if (welderUI.getId() == 0){
            result = new Welder();
        }else {
            result = welderService.get(welderUI.getId());
        }
        result.setSurname(welderUI.getSurname());
        result.setName(welderUI.getName());
        result.setSecname(welderUI.getSecname());
        result.setBirthday(welderUI.getBirthday());
        result.setDateBegin(welderUI.getDateBegin());
        result.setDocNumber(welderUI.getDocNumber());
        result.setAddress(welderUI.getAddress());
        //TODO set others objects parameters

        return result;
    }
}
