package com.lvg.weldercenter.dao;

import java.util.List;

/**
 * Created by Victor Levchenko (LVG Corp.) on 29.10.2014.
 */
public interface GenericDao<T> {

    List<T> getAll();

    T get(Long id);

    void update(T record);

    void add(T record);

    void delete(T record);
}
