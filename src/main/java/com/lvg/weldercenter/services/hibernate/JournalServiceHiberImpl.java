package com.lvg.weldercenter.services.hibernate;

import com.lvg.weldercenter.dao.JournalDao;
import com.lvg.weldercenter.models.Journal;
import com.lvg.weldercenter.services.JournalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Victor Levchenko LVG Corp. on 31.10.14.
 */
@Transactional(readOnly =  true)
public class JournalServiceHiberImpl implements JournalService {

    @Autowired
    private JournalDao dao;

    public void setDao(JournalDao dao) {
        this.dao = dao;
    }

    @Override
    public List<Journal> getAll() {
        return dao.getAll();
    }

    @Override
    public Journal get(Long id) {
        return dao.get(id);
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void update(Journal record) {
        dao.update(record);
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public Long insert(Journal record)  {
        return dao.add(record);
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void delete(Journal record) {
        dao.delete(record);
    }
}
