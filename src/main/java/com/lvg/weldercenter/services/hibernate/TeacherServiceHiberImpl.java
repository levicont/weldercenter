package com.lvg.weldercenter.services.hibernate;

import com.lvg.weldercenter.dao.TeacherDao;
import com.lvg.weldercenter.models.Teacher;
import com.lvg.weldercenter.services.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Victor Levchenko LVG Corp. on 31.10.14.
 */
@Transactional(readOnly = true)
public class TeacherServiceHiberImpl implements TeacherService {

    @Autowired
    private TeacherDao dao;

    public void setDao(TeacherDao dao) {
        this.dao = dao;
    }

    @Override
    @Transactional
    public List<Teacher> getAll() {
        return dao.getAll();
    }

    @Override
    public Teacher get(Long id) {
        return dao.get(id);
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void update(Teacher record) {
        dao.update(record);
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public Long insert(Teacher record) {
        return dao.add(record);
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void delete(Teacher record) {
        dao.delete(record);
    }
}
