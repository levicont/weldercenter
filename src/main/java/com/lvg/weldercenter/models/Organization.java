package com.lvg.weldercenter.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by victor on 15.10.2014.
 */
@Entity
@Table(name = "organization")
public class Organization {

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

    @OneToMany(targetEntity = Welder.class, mappedBy = "organization", cascade = CascadeType.ALL)
    public List<Welder> getWelders() {
        return welders;
    }

    public void setWelders(List<Welder> welders) {
        this.welders = welders;
    }
}
