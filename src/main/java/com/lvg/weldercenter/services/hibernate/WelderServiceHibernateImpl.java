package com.lvg.weldercenter.services.hibernate;

import com.lvg.weldercenter.models.Welder;
import com.lvg.weldercenter.services.WelderService;

import java.util.List;

/**
 * Created by Victor Levchenko (LVG Corp.) on 28.10.2014.
 */
public class WelderServiceHibernateImpl implements WelderService {


    @Override
    public List<Welder> getAllBySurname(String surname) {
        return null;
    }

    @Override
    public List<Welder> getAll() {
        return null;
    }

    @Override
    public Welder get(Long id) {
        return null;
    }

    @Override
    public void update(Welder record) {

    }

    @Override
    public void insert(Welder record) {

    }

    @Override
    public void delete(Welder record) {

    }
}
