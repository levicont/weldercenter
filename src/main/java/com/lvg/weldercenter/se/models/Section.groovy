package com.lvg.weldercenter.se.models

import com.lvg.weldercenter.se.cfg.R
import com.lvg.weldercenter.se.exceptions.WelderCenterModelException

import javax.persistence.*

@Entity
class Section implements Serializable{

    @Column(name = 'ID')
    @Id
    @GeneratedValue(generator = R.ModelsConfig.ID_GENERATOR_NAME)
    Long id

    @Column(name = 'ORDER_INDEX')
    Integer orderIndex

    @Column(name = 'TITLE', nullable = false)
    String title

    @Column(name = 'DESCRIPTION')
    String description

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = 'CURRICULUM_ID',
            foreignKey = @ForeignKey(name = 'FK_SECTION_CURRICULUM'),
            nullable = false)
    Curriculum curriculum

    @ElementCollection
    @OrderBy('orderIndex')
    Set<Topic> topics = new LinkedHashSet<>()

    protected Section(){}

    Section(Curriculum curriculum, String title){
        if (curriculum == null || curriculum.id == null)
            throw new WelderCenterModelException("Curriculum or it's id must be not null")
        if (title == null || title.isEmpty())
            throw new WelderCenterModelException("Title of Section must be not null and not empty")

        this.curriculum = curriculum
        this.title = title
    }

    boolean equals(o) {
        if (this.is(o)) return true
        if (!(o instanceof Section)) return false

        Section section = (Section) o

        if (curriculum != section.curriculum) return false
        if (id != section.id) return false
        if (title != section.title) return false

        return true
    }

    int hashCode() {
        int result
        result = (id != null ? id.hashCode() : 0)
        result = 31 * result + (title != null ? title.hashCode() : 0)
        result = 31 * result + (curriculum != null ? curriculum.hashCode() : 0)
        return result
    }

    String toString(){
        return "$title"
    }
}
