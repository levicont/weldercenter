package com.lvg.weldercenter.services;

import com.lvg.weldercenter.models.WeldMethod;

/**
 * Created by Victor Levchenko (LVG Corp.) on 29.10.2014.
 */
public interface WeldMethodService extends GenericService<WeldMethod> {

    public WeldMethod getByName(String name);
    public WeldMethod getByCode(String code);
}
