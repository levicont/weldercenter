package com.lvg.weldercenter.utils

import com.lvg.weldercenter.models.Education
import com.lvg.weldercenter.models.Electrode
import com.lvg.weldercenter.models.Job
import com.lvg.weldercenter.models.Organization
import com.lvg.weldercenter.models.Qualification
import com.lvg.weldercenter.models.Welder

/**
 * Created by Victor on 06.07.2017.
 */
class ModelGenerator {

    static Welder getWelder(){
        Welder welder = new Welder()

        welder.name = 'Иван'
        welder.surname = 'Иванов'
        welder.secname = 'Иванович'
        welder.birthday = new Date()
        welder.address = 'г. Харьков, ул. Гагарина, 17'
        welder.dateBegin = new Date()
        welder.docNumber = 'ХА-001'

        welder.education = getEducation()
        welder.organization = getOrganization()
        welder.job = getJob()
        welder.qualification = getQualification()


        return welder
    }

    static Qualification getQualification() {
        Qualification qualification = new Qualification()
        qualification.type = 'электросварщик'
    }

    static Job getJob() {
        Job job = new Job()
        job.name = 'электросварщик'
        return job
    }

    static Organization getOrganization() {
        Organization organization = new Organization()
        organization.name = 'ООО \"Сварка и стройка\"'
        organization.adress = 'г. Киев, ул. Шевченко, 113'
        organization.phone = '044 253 55 56'
        return organization

    }

    static Education getEducation(){
        Education education = new Education()
        education.type = 'среднее-специальное'
        return education
    }

    static Electrode getEletrode(){
        Electrode electrode = new Electrode()

        electrode.type = 'АНО-21'
    }

}
