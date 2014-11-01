package com.lvg.weldercenter.services.hibernate;

import com.lvg.weldercenter.dao.SectionDao;
import com.lvg.weldercenter.models.Section;
import com.lvg.weldercenter.services.SectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Victor Levchenko LVG Corp. on 31.10.14.
 */
@Transactional(readOnly = true)
public class SectionServiceHiberImpl implements SectionService {


    @Autowired
    private SectionDao dao;

    public void setDao(SectionDao dao) {
        this.dao = dao;
    }

    @Override
    @Transactional
    public List<Section> getAll() {
        return dao.getAll();
    }

    @Override
    public Section get(Long id) {
        return dao.get(id);
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void update(Section record) {
        dao.update(record);
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public Long insert(Section record) {
        return dao.add(record);
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void delete(Section record) {
        dao.delete(record);
    }
}
