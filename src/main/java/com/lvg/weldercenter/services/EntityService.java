package com.lvg.weldercenter.services;

import com.lvg.weldercenter.models.Welder;

import java.util.List;

/**
 * Created by Victor Levchenko LVG Corp. on 27.10.14.
 */
public interface EntityService<T> {

    List<T> getAll();

    T get(Long id);

    void update(T record);

    void insert(T record);

    void delete(T record);
}
