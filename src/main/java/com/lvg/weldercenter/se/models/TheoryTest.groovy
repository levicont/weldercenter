package com.lvg.weldercenter.se.models

import javax.persistence.Column
import javax.persistence.Embeddable

@Embeddable
class TheoryTest implements Serializable{

    @Column(name = 'TICKET_NUMBER')
    String ticketNumber

    @Column(name = 'RATING')
    String rating

    String toString(){return "$ticketNumber $rating"}
}
