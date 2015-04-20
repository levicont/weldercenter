package com.lvg.weldercenter.ui.servicesui.impl;

import com.lvg.weldercenter.models.Teacher;
import com.lvg.weldercenter.services.TeacherService;
import com.lvg.weldercenter.spring.factories.ServiceFactory;
import com.lvg.weldercenter.ui.entities.TeacherUI;
import com.lvg.weldercenter.ui.servicesui.TeacherServiceUI;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by Victor Levchenko LVG Corp. on 19.02.15.
 */
public class TeacherUIManager implements TeacherServiceUI {

    @Autowired
    private TeacherService teacherService;


    @Override
    public Teacher getTeacherFromTeacherUI(TeacherUI teacherUI) {
        Teacher result;
        if(teacherUI.getId()==0){
            result = new Teacher();
        }else {
            result = teacherService.get(teacherUI.getId());
        }
        result.setName(teacherUI.getName());
        result.setSurname(teacherUI.getSurname());
        result.setSecname(teacherUI.getSecname());

        return result;
    }
}
