package com.lvg.weldercenter.ui.servicesui.impl;

import com.lvg.weldercenter.models.Teacher;
import com.lvg.weldercenter.services.TeacherService;
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
        if (teacherUI == null)
            return null;

        Teacher result = teacherService.get(teacherUI.getId());
        if(result != null){
            updateTeacherFromUIModel(result, teacherUI);

        }else {
            result = new Teacher();
            updateTeacherFromUIModel(result, teacherUI);
        }
        return result;
    }

    private void updateTeacherFromUIModel(Teacher updTeacher, TeacherUI uiModel){
        updTeacher.setName(uiModel.getName());
        updTeacher.setSurname(uiModel.getSurname());
        updTeacher.setSecname(uiModel.getSecname());
    }
}
