package com.lvg.weldercenter.models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by victor on 15.10.2014.
 */
@Entity
@Table(name = "organization")
public class Organization implements Serializable{


    private static final long serialVersionUID = 7520196006411038477L;
    private Long organizationId;
    private String adress;
    private String name;
    private String phone;

    private List<Welder> welders = new ArrayList<Welder>();

    //Getters and Setters
    @Id
    @GeneratedValue
    @Column(name = "id_organization")
    public Long getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(Long organizationId) {
        this.organizationId = organizationId;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @OneToMany(targetEntity = Welder.class, mappedBy = "organization")
    public List<Welder> getWelders() {
        return welders;
    }

    public void setWelders(List<Welder> welders) {
        this.welders = welders;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Organization that = (Organization) o;

        if (adress != null ? !adress.equals(that.adress) : that.adress != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (!organizationId.equals(that.organizationId)) return false;
        if (phone != null ? !phone.equals(that.phone) : that.phone != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = organizationId.hashCode();
        result = 31 * result + (adress != null ? adress.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (phone != null ? phone.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Organization{" +
                "organizationId=" + organizationId +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
