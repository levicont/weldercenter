package com.lvg.weldercenter.services.hibernate;

import com.lvg.weldercenter.dao.TopicDao;
import com.lvg.weldercenter.models.Topic;
import com.lvg.weldercenter.services.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Victor Levchenko LVG Corp. on 31.10.14.
 */
@Transactional(readOnly = true)
public class TopicServiceHiberImpl implements TopicService {

    @Autowired
    private TopicDao dao;

    public void setDao(TopicDao dao) {
        this.dao = dao;
    }

    @Override
    @Transactional
    public List<Topic> getAll() {
        return dao.getAll();
    }

    @Override
    public Topic get(Long id) {
        return dao.get(id);
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void update(Topic record) {
        dao.update(record);
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public Long insert(Topic record) {
        return dao.add(record);
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void delete(Topic record) {
        dao.delete(record);
    }
}
