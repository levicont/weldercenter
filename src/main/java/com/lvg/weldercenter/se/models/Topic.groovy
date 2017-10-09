package com.lvg.weldercenter.se.models

import javax.persistence.Column
import javax.persistence.Embeddable

/**
 * Created by Victor on 06.10.2017.
 */
@Embeddable
class Topic implements Serializable{

    @Column(name = 'ORDER_INDEX')
    private Integer orderIndex;

    @Column(name = 'TITLE', nullable = false)
    private String title;

    @Column(name= 'DESCRIPTION')
    private String description;

    @Column(name = 'TIME_LONG_HOURS')
    private Double timeLongHours;
}
