package com.lvg.weldercenter.se.models

import com.lvg.weldercenter.se.cfg.R

import javax.persistence.*

@Entity
class Section implements Serializable{

    @Column(name = 'ID')
    @Id
    @GeneratedValue(generator = R.ModelsConfig.ID_GENERATOR_NAME)
    Long id

    @Column(name = 'ORDER_INDEX')
    Integer orderIndex;

    @Column(name = 'TITLE', nullable = false, unique = true)
    String title;

    @Column(name = 'DESCRIPTION')
    String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinTable(name = 'CURRICULUM_SECTION',
        joinColumns = @JoinColumn(name = 'SECTION_ID'),
        inverseJoinColumns = @JoinColumn(nullable = false))
    Curriculum curriculum

    @ElementCollection
    @OrderBy('orderIndex')
    Set<Topic> topics = new LinkedHashSet<>()

    boolean equals(o) {
        if (this.is(o)) return true
        if (getClass() != o.class) return false

        Section section = (Section) o

        if (id != section.id) return false
        if (title != section.title) return false

        return true
    }

    int hashCode() {
        int result
        result = (id != null ? id.hashCode() : 0)
        result = 31 * result + (title != null ? title.hashCode() : 0)
        return result
    }

    String toString(){
        return "$title"
    }
}
