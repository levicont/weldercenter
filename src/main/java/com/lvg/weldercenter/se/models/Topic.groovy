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

    boolean equals(o) {
        if (this.is(o)) return true
        if (getClass() != o.class) return false

        Topic topic = (Topic) o

        if (title != topic.title) return false

        return true
    }

    int hashCode() {
        return (title != null ? title.hashCode() : 0)
    }

    String toString(){
        return "$title"
    }
}