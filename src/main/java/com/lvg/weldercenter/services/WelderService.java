package com.lvg.weldercenter.services;

import com.lvg.weldercenter.models.Welder;

import java.util.List;

/**
 * Created by Victor Levchenko LVG Corp. on 27.10.14.
 */
public interface WelderService extends GenericService<Welder> {

    List<Welder> getAllBySurname (String surname);
}
