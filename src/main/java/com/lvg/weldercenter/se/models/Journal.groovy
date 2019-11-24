package com.lvg.weldercenter.se.models

import com.lvg.weldercenter.se.cfg.R
import com.lvg.weldercenter.se.converters.LocalDateConverter

import javax.persistence.*
import java.time.LocalDate

@Entity
class Journal implements Serializable{

    @Id
    @GeneratedValue(generator = R.ModelsConfig.ID_GENERATOR_NAME)
    @Column(name = 'ID')
    Long id

    @Column(name = 'NUMBER')
    String number

    @Column(name = 'DATE_BEGIN')
    @Convert(converter = LocalDateConverter.class)
    LocalDate dateBegin

    @Column(name = 'DATE_END')
    @Convert(converter = LocalDateConverter.class)
    LocalDate dateEnd

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = 'CURRICULUM_ID',
            foreignKey = @ForeignKey(name = 'FK_JOURNAL_CURRICULUM'))
    Curriculum curriculum

    @ManyToMany(targetEntity = Teacher.class, cascade = CascadeType.PERSIST)
    @JoinTable(name = 'journal_teacher',
        joinColumns = @JoinColumn(name = 'JOURNAL_ID', foreignKey = @ForeignKey(name = 'FK_JOURNAL_TEACHER') ),
        inverseJoinColumns = @JoinColumn(name = 'TEACHER_ID', foreignKey = @ForeignKey(name = 'FK_TEACHER_JOURNAL_INV')))
    Set<Teacher> teachers = new HashSet<>()

    boolean equals(o) {
        if (this.is(o)) return true
        if (!(o instanceof Journal)) return false

        Journal journal = (Journal) o

        if (dateBegin != journal.dateBegin) return false
        if (id != journal.id) return false
        if (number != journal.number) return false

        return true
    }

    int hashCode() {
        int result
        result = (id != null ? id.hashCode() : 0)
        result = 31 * result + (number != null ? number.hashCode() : 0)
        result = 31 * result + (dateBegin != null ? dateBegin.hashCode() : 0)
        return result
    }

    String toString(){
        return "Журнал №$number от $dateBegin"
    }
}
