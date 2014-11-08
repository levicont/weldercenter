package com.lvg.weldercenter.models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Victor Levchenko LVG Corp. on 23.10.14.
 */
@Entity
@Table(name = "ndt_document")
public class NDTDocument implements Serializable{


    private static final long serialVersionUID = -3888270281942580260L;
    private Long ndtDocumentId;
    private String name;
    private String fullName;

    private List<PersonalProtocol> personalProtocols = new ArrayList<PersonalProtocol>();

    //Getters and Setters

    @Id
    @GeneratedValue
    @Column(name = "id_ndt_document")
    public Long getNdtDocumentId() {
        return ndtDocumentId;
    }

    public void setNdtDocumentId(Long ndtDocumentId) {
        this.ndtDocumentId = ndtDocumentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "full_name", columnDefinition = "TEXT")
    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    @ManyToMany
    @JoinTable(name = "personal_protocol_ndt_document",
        joinColumns = {@JoinColumn(name = "id_ndt_document")},
        inverseJoinColumns = {@JoinColumn(name = "id_personal_protocol")})
    public List<PersonalProtocol> getPersonalProtocols() {
        return personalProtocols;
    }

    public void setPersonalProtocols(List<PersonalProtocol> personalProtocols) {
        this.personalProtocols = personalProtocols;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        NDTDocument that = (NDTDocument) o;

        if (!name.equals(that.name)) return false;
        if (!ndtDocumentId.equals(that.ndtDocumentId)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = ndtDocumentId.hashCode();
        result = 31 * result + name.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "NDTDocument{" +
                "ndtDocumentId=" + ndtDocumentId +
                ", name='" + name + '\'' +
                '}';
    }
}
