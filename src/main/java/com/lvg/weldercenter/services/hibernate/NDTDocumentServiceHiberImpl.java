package com.lvg.weldercenter.services.hibernate;

import com.lvg.weldercenter.dao.NDTDocumentDao;
import com.lvg.weldercenter.models.NDTDocument;
import com.lvg.weldercenter.services.NDTDocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Victor Levchenko LVG Corp. on 31.10.14.
 */
@Transactional(readOnly = true)
public class NDTDocumentServiceHiberImpl implements NDTDocumentService {

    @Autowired
    private NDTDocumentDao dao;

    public void setDao(NDTDocumentDao dao) {
        this.dao = dao;
    }

    @Override
    @Transactional
    public List<NDTDocument> getAll() {
        return dao.getAll();
    }

    @Override
    public NDTDocument get(Long id) {
        return dao.get(id);
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void update(NDTDocument record) {
        dao.update(record);
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public Long insert(NDTDocument record) {
        return dao.add(record);
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void delete(NDTDocument record) {
        dao.delete(record);
    }
}
