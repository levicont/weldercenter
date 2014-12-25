package com.lvg.weldercenter.hibernate.utils;

import org.hibernate.Hibernate;
import org.hibernate.proxy.HibernateProxy;

/**
 * Created by Victor Levchenko LVG Corp. on 16.12.14.
 */
public class Unproximator {
    public static <T> T initializeAndUnproxy(T entity) {
        if (entity == null) {
            throw new
                    NullPointerException("Entity passed for initialization is null");
        }

        Hibernate.initialize(entity);
        if (entity instanceof HibernateProxy) {
            entity = (T) ((HibernateProxy) entity).getHibernateLazyInitializer()
                    .getImplementation();
        }
        return entity;
    }
}
