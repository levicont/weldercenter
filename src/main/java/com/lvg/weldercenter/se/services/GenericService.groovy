package com.lvg.weldercenter.se.services

interface GenericService<T extends Serializable> {
    T save(T entity)
    T get(Long id)
    List<T> getAll()
    void delete(T entity)
    Long count()
}
