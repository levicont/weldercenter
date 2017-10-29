package com.lvg.weldercenter.se.models

import com.lvg.weldercenter.se.cfg.R
import com.lvg.weldercenter.se.exceptions.WelderCenterModelException

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.FetchType
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

    @ManyToOne(targetEntity = Teacher.class, optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = 'HEAD_ID')
    Teacher head

    @ManyToOne(targetEntity = Teacher.class, optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = 'WELD_SPECIALIST_ID')
    Teacher weldSpecialist

    @ManyToOne(targetEntity = Teacher.class, optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = 'NDT_SPECIALIST_ID')
    Teacher ndtSpecialist

    @ManyToOne(targetEntity = Teacher.class, optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = 'SAFETY_SPECIALIST_ID')
    Teacher safetySpecialist

    protected CommissionCertification(){}

    CommissionCertification(Teacher head, Teacher weldSpecialist, Teacher ndtSpecialist, Teacher safetySpecialist){

        if (head == null || head.id == null)
            throw new WelderCenterModelException('head of CommissionCertification must be not null')
        else this.head = head

        if (weldSpecialist == null || head.id == null)
            throw new WelderCenterModelException('weldSpecialist of CommissionCertification must be not null')
        else this.weldSpecialist = weldSpecialist

        if (ndtSpecialist == null || head.id == null)
            throw new WelderCenterModelException('ndtSpecialist of CommissionCertification must be not null')
        else this.ndtSpecialist  = ndtSpecialist

        if (safetySpecialist == null || head.id == null)
            throw new WelderCenterModelException('safetySpecialist of CommissionCertification must be not null')
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
