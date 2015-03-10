package com.lvg.weldercenter.ui.servicesui.impl;

import com.lvg.weldercenter.models.*;
import com.lvg.weldercenter.services.CurriculumService;
import com.lvg.weldercenter.services.TotalProtocolService;
import com.lvg.weldercenter.spring.factories.ServiceFactory;
import com.lvg.weldercenter.spring.factories.ServiceUIFactory;
import com.lvg.weldercenter.ui.entities.JournalUI;
import com.lvg.weldercenter.ui.entities.TeacherUI;
import com.lvg.weldercenter.ui.entities.TotalProtocolUI;
import com.lvg.weldercenter.ui.entities.WelderUI;
import com.lvg.weldercenter.ui.servicesui.JournalServiceUI;
import com.lvg.weldercenter.ui.servicesui.TeacherServiceUI;
import com.lvg.weldercenter.ui.servicesui.WelderServiceUI;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Victor Levchenko LVG Corp. on 19.02.15.
 */
public class JournalUIManager implements JournalServiceUI {

    @Autowired
    private CurriculumService curriculumService;
    @Autowired
    private WelderServiceUI welderServiceUI;
    @Autowired
    private TeacherServiceUI teacherServiceUI;

    @Override
    public Journal getJournalFromJournalUI(JournalUI journalUI) {
        Journal result = new Journal();
        result.setJournalId(journalUI.getId());
        result.setNumber(journalUI.getNumber());
        result.setDateBegin(journalUI.getDateBegin());
        result.setDateEnd(journalUI.getDateEnd());

        List<Curriculum> curriculums = curriculumService.getAll();
        for (Curriculum cur: curriculums){
            if(journalUI.getCurriculum().equals(cur.getTitle())){
                result.setCurriculum(cur);
                break;
            }
        }

        List<Teacher> teacherList = new ArrayList<Teacher>();
        for (TeacherUI teacherUI : journalUI.getTeachers()){
            teacherList.add(teacherServiceUI.getTeacherFromTeacherUI(teacherUI));
        }
        result.setTeachers(teacherList);

        List<Welder> welderList = new ArrayList<Welder>();
        for(WelderUI welderUI: journalUI.getWelders()){
            welderList.add(welderServiceUI.getWelderFromWelderUI(welderUI));
        }
        result.setWelders(welderList);
        return result;
    }
}
