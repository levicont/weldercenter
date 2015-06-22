package com.lvg.weldercenter.models;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Victor on 22.06.2015.
 */
@Entity
@Table(name = "weld_join_type")
public class WeldJoinType implements Serializable {

    private static final long serialVersionUID = 8478989907375731028L;
    private Long weldJoinTypeId;
    private String type;
    private String description;

    private List<WeldPattern> weldPatterns = new ArrayList<WeldPattern>();

    //Getters and Setters

    @Id
    @GeneratedValue
    @Column(name = "id_weld_join_type")
    public Long getWeldJoinTypeId() {
        return weldJoinTypeId;
    }

    public void setWeldJoinTypeId(Long weldJoinTypeId) {
        this.weldJoinTypeId = weldJoinTypeId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @ManyToMany
    @LazyCollection(value = LazyCollectionOption.FALSE)
    @JoinTable(name = "weld_pattern_weld_join_type",
            joinColumns = {@JoinColumn(name = "id_weld_join_type")},
            inverseJoinColumns = {@JoinColumn(name = "id_weld_pattern")})
    public List<WeldPattern> getWeldPatterns() {
        return weldPatterns;
    }

    public void setWeldPatterns(List<WeldPattern> weldPatterns) {
        this.weldPatterns = weldPatterns;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        WeldJoinType that = (WeldJoinType) o;

        if (description != null ? !description.equals(that.description) : that.description != null) return false;
        if (type != null ? !type.equals(that.type) : that.type != null) return false;
        if (!weldJoinTypeId.equals(that.weldJoinTypeId)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = weldJoinTypeId.hashCode();
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "WeldJoinType{" +
                "weldJoinTypeId=" + weldJoinTypeId +
                ", type='" + type + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
