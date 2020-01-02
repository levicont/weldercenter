package com.lvg.weldercenter.se.models

import javax.persistence.Column
import javax.persistence.Embeddable

/**
 * Created by Victor Levchenko LVG Corp. on 10.12.2019.
 */

@Embeddable
class OrganizationEmbedded implements Serializable {

    @Column(name = "ORG_NAME")
    String name
    @Column(name = "ORG_ADDRESS")
    String address
    @Column(name = "ORG_PHONE")
    String phone

    boolean equals(o) {
        if (this.is(o)) return true
        if (!(o instanceof Organization)) return false

        Organization that = (Organization) o

        if (name != that.name) return false
        if (address != that.address) return false
        if (phone != that.phone) return false

        return true
    }

    int hashCode() {
        int result
        result = 31 * (name != null ? name.hashCode() : 0)
        result = 31 * result + (address != null ? address.hashCode() : 0)
        result = 31 * result + (phone != null ? phone.hashCode() : 0)
        return result
    }

    String toString(){
        return "$name, $address, $phone"
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return new OrganizationEmbedded(name: this.name, address: this.address, phone: this.phone)
    }
}
