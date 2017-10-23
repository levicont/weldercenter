package com.lvg.weldercenter.se.models

import com.lvg.weldercenter.se.cfg.R

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.Table

@Entity
@Table(name = 'commission_certification')
class CommissionCertification implements Serializable{

    @Id
    @GeneratedValue(generator = R.ModelsConfig.ID_GENERATOR_NAME)
    @Column(name = 'ID')
    Long id

    @ManyToOne(targetEntity = Teacher.class, optional = false)
    @JoinColumn(name = 'HEAD_ID')
    Teacher head

    @ManyToOne(targetEntity = Teacher.class, optional = false)
    @JoinColumn(name = 'WELD_SPECIALIST_ID')
    Teacher weldSpecialist;

    @ManyToOne(targetEntity = Teacher.class, optional = false)
    @JoinColumn(name = 'NDT_SPECIALIST_ID')
    Teacher ndtSpecialist;

    @ManyToOne(targetEntity = Teacher.class, optional = false)
    @JoinColumn(name = 'SAFETY_SPECIALIST_ID')
    Teacher safetySpecialist;

    protected CommissionCertification(){}

    CommissionCertification(Teacher head, Teacher weldSpecialist, Teacher ndtSpecialist, Teacher safetySpecialist){

        if (head == null)
            throw new IllegalArgumentException('head of CommissionCertification must be not null')
        else this.head = head

        if (weldSpecialist == null)
            throw new IllegalArgumentException('weldSpecialist of CommissionCertification must be not null')
        else this.weldSpecialist = weldSpecialist

        if (ndtSpecialist == null)
            throw new IllegalArgumentException('ndtSpecialist of CommissionCertification must be not null')
        else this.ndtSpecialist  = ndtSpecialist

        if (safetySpecialist == null)
            throw new IllegalArgumentException('safetySpecialist of CommissionCertification must be not null')
        else this.safetySpecialist = safetySpecialist
    }

    boolean equals(o) {
        if (this.is(o)) return true
        if (getClass() != o.class) return false

        CommissionCertification that = (CommissionCertification) o

        if (head != that.head) return false
        if (id != that.id) return false
        if (ndtSpecialist != that.ndtSpecialist) return false
        if (safetySpecialist != that.safetySpecialist) return false
        if (weldSpecialist != that.weldSpecialist) return false

        return true
    }

    int hashCode() {
        int result
        result = (id != null ? id.hashCode() : 0)
        result = 31 * result + (head != null ? head.hashCode() : 0)
        result = 31 * result + (weldSpecialist != null ? weldSpecialist.hashCode() : 0)
        result = 31 * result + (ndtSpecialist != null ? ndtSpecialist.hashCode() : 0)
        result = 31 * result + (safetySpecialist != null ? safetySpecialist.hashCode() : 0)
        return result
    }

    @Override
    String toString() {
        return "Комиссия глава(эксперт): $head; специалист по сварке: $weldSpecialist; специалист по контролю: $ndtSpecialist; специалист по охране труда: $safetySpecialist"
    }
}
