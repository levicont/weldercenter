package com.lvg.weldercenter.se.models

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

import static com.lvg.weldercenter.se.cfg.R.ModelsConfig.ID_GENERATOR_NAME

@Entity
class Organization implements Serializable{

    @Id
    @GeneratedValue(generator = ID_GENERATOR_NAME)
    protected Long id
    protected String name
    protected String address
    protected String phone

    Long getId() {
        return id
    }

    String getName() {
        return name
    }

    void setName(String name) {
        this.name = name
    }

    String getAddress() {
        return address
    }

    void setAddress(String address) {
        this.address = address
    }

    String getPhone() {
        return phone
    }

    void setPhone(String phone) {
        this.phone = phone
    }
}
