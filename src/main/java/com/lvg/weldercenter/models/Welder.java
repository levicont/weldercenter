package com.lvg.weldercenter.models;


import com.sun.xml.internal.bind.v2.runtime.reflect.Lister;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="welder")
public class Welder {
	 

	private Long welderId;
	private String name;
	private String surname;
    private String secname;
    private String docNumber;
    private Date birthday;
    private Date dateBegin;

    private Education education;
    private Qualification qualification;
    private Organization organization;
    private Job job;
    private List<WeldMethod> weldMethods = new ArrayList<WeldMethod>();
    private List<Journal> journals = new ArrayList<Journal>();
    private List<PersonalProtocol> personalProtocols = new ArrayList<PersonalProtocol>();

    public Welder(){

    }
	

	//Getters and Setters

    @Id
    @GeneratedValue
    @Column(name = "id_welder")
    public Long getWelderId() {
		return welderId;
	}

	public void setWelderId(Long welderId) {
		this.welderId = welderId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

    public String getSecname() {
        return secname;
    }

    public void setSecname(String secname) {
        this.secname = secname;
    }

    @Column(name = "doc_number")
    public String getDocNumber() {
        return docNumber;
    }

    public void setDocNumber(String docNumber) {
        this.docNumber = docNumber;
    }

    @Temporal(TemporalType.DATE)
    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    @Column(name = "date_begin")
    @Temporal(TemporalType.DATE)
    public Date getDateBegin() {
        return dateBegin;
    }

    public void setDateBegin(Date dateBegin) {
        this.dateBegin = dateBegin;
    }

    @ManyToOne(targetEntity = Education.class)
    @JoinColumn(name = "id_education")
    public Education getEducation() {
        return education;
    }

    public void setEducation(Education education) {
        this.education = education;
    }

    @ManyToOne(targetEntity = Qualification.class)
    @JoinColumn(name = "id_qualification")
    public Qualification getQualification() {
        return qualification;
    }

    public void setQualification(Qualification qualification) {
        this.qualification = qualification;
    }

    @ManyToOne(targetEntity = Organization.class)
    @JoinColumn(name = "id_organization")
    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }

    @ManyToOne(targetEntity = Job.class)
    @JoinColumn(name = "id_job")
    public Job getJob() {
        return job;
    }

    public void setJob(Job job) {
        this.job = job;
    }

    //TODO cascade CascadeType.ALL
    @OneToMany(targetEntity = PersonalProtocol.class, mappedBy = "welder")
    public List<PersonalProtocol> getPersonalProtocols() {
        return personalProtocols;
    }

    public void setPersonalProtocols(List<PersonalProtocol> personalProtocols) {
        this.personalProtocols = personalProtocols;
    }

    @ManyToMany
    @JoinTable(name = "welder_weld_method",
            joinColumns = {@JoinColumn(name = "id_welder")},
            inverseJoinColumns = {@JoinColumn(name = "id_weld_method")})
    public List<WeldMethod> getWeldMethods() {
        return weldMethods;
    }

    public void setWeldMethods(List<WeldMethod> weldMethods) {
        this.weldMethods = weldMethods;
    }

    @ManyToMany
    @JoinTable(name = "journal_welder",
            joinColumns = {@JoinColumn(name = "id_welder")},
            inverseJoinColumns = {@JoinColumn(name = "id_journal")})
    public List<Journal> getJournals() {
        return journals;
    }

    public void setJournals(List<Journal> journals) {
        this.journals = journals;
    }
}
