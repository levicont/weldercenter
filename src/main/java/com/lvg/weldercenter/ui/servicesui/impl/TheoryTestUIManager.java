package com.lvg.weldercenter.ui.servicesui.impl;

import com.lvg.weldercenter.models.TheoryTest;
import com.lvg.weldercenter.services.TheoryTestService;
import com.lvg.weldercenter.ui.entities.TheoryTestUI;
import com.lvg.weldercenter.ui.servicesui.TheoryTestServiceUI;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by Victor on 06.04.2015.
 */
public class TheoryTestUIManager implements TheoryTestServiceUI {
    @Autowired
    private TheoryTestService theoryTestService;

    @Override
    public TheoryTest getTheoryTestFromUIModel(TheoryTestUI theoryTestUI) {
        if (theoryTestUI == null){
            return null;
        }
        TheoryTest tt = theoryTestService.get(theoryTestUI.getId());
        if (tt != null){
            updateTheoryTestFormUIModel(tt, theoryTestUI);
        }else {
            tt = new TheoryTest();
            updateTheoryTestFormUIModel(tt, theoryTestUI);
        }
        return tt;
    }

    private void updateTheoryTestFormUIModel(TheoryTest updTheoryTest, TheoryTestUI modelUI){
        updTheoryTest.setRating(modelUI.getRating());
        updTheoryTest.setTicketNumber(modelUI.getTicketNumber());
    }
}
