package com.lvg.weldercenter.se.models

import com.lvg.weldercenter.se.cfg.R
import com.lvg.weldercenter.se.converters.LocalDateConverter

import javax.persistence.CascadeType
import javax.persistence.Column
import javax.persistence.Convert
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.ForeignKey
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.JoinTable
import javax.persistence.ManyToMany
import javax.persistence.ManyToOne
import javax.persistence.OneToMany
import javax.persistence.Table
import java.time.LocalDate

@Entity
@Table(name = 'personal_protocol')
class PersonalProtocol implements Serializable{

    @Id
    @GeneratedValue(generator = R.ModelsConfig.ID_GENERATOR_NAME)
    @Column(name = 'ID')
    Long id

    @Convert(converter = LocalDateConverter.class)
    @Column(name = 'DATE_CERTIFICATION')
    LocalDate dateCertification

    @Column(name = 'NUMBER')
    String number

    @Column(name = 'ATTEST_TYPE')
    String attestType

    @Column(name = 'RESOLUTION_CERTIFICATION', columnDefinition = 'TEXT')
    String resolutionCertification

    TheoryTest theoryTest

    @OneToMany(mappedBy = 'personalProtocol', fetch = FetchType.LAZY)
    Set<WeldPattern> weldPatterns = new HashSet<>()

    @ManyToOne
    @JoinColumn(name = 'WELDER_ID', nullable = false)
    Welder welder

    @ManyToOne
    @JoinColumn(name = 'JOURNAL_ID', nullable = false)
    Journal journal

    @ManyToMany(targetEntity = NDTDocument.class, cascade = CascadeType.PERSIST)
    @JoinTable(name = 'personal_protocol_ndt_document',
        joinColumns = @JoinColumn(name = 'PERSONAL_PROTOCOL_ID'),
        inverseJoinColumns = @JoinColumn(name = 'NDT_DOCUMENT_ID'))
    Set<NDTDocument> ndtDocuments = new HashSet<>()

    protected PersonalProtocol(){}

    PersonalProtocol(Welder welder, Journal journal){
        if (welder.id == null) throw new IllegalArgumentException('Welder\'s id must be not null' )
        if (journal.id == null) throw new IllegalArgumentException('Journal\'s id must be not null' )

        this.journal = journal
        this.welder = welder
    }

    boolean equals(o) {
        if (this.is(o)) return true
        if (getClass() != o.class) return false

        PersonalProtocol that = (PersonalProtocol) o

        if (id != that.id) return false
        if (journal != that.journal) return false
        if (welder != that.welder) return false

        return true
    }

    int hashCode() {
        int result
        result = (id != null ? id.hashCode() : 0)
        result = 31 * result + (welder != null ? welder.hashCode() : 0)
        result = 31 * result + (journal != null ? journal.hashCode() : 0)
        return result
    }

    String toString() {
        return "$number $dateCertification $welder"
    }
}
