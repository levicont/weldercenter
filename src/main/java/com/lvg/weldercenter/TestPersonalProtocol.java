package com.lvg.weldercenter;

import com.lvg.weldercenter.models.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.tool.hbm2ddl.SchemaExport;

import java.util.Date;

/**
 * Created by Victor Levchenko LVG Corp. on 23.10.14.
 */
public class TestPersonalProtocol {
    public static void main(String[] args) {
        AnnotationConfiguration cfg = new AnnotationConfiguration();

        cfg.addAnnotatedClass(Electrode.class);
        cfg.addAnnotatedClass(WeldWire.class);
        cfg.addAnnotatedClass(WeldGas.class);
        cfg.addAnnotatedClass(WeldDetail.class);
        cfg.addAnnotatedClass(SteelType.class);
        cfg.addAnnotatedClass(SteelGroup.class);
        cfg.addAnnotatedClass(WeldPosition.class);
        cfg.addAnnotatedClass(PatternThickness.class);
        cfg.addAnnotatedClass(PatternDiameter.class);
        cfg.addAnnotatedClass(Evaluation.class);
        cfg.addAnnotatedClass(RadiationTest.class);
        cfg.addAnnotatedClass(VisualTest.class);
        cfg.addAnnotatedClass(MechanicalTest.class);
        cfg.addAnnotatedClass(WeldMethod.class);
        cfg.addAnnotatedClass(WeldPattern.class);
        //welder test
        cfg.addAnnotatedClass(Welder.class);
        cfg.addAnnotatedClass(Education.class);
        cfg.addAnnotatedClass(Qualification.class);
        cfg.addAnnotatedClass(Organization.class);
        cfg.addAnnotatedClass(Job.class);
        cfg.addAnnotatedClass(Journal.class);
        cfg.addAnnotatedClass(Teacher.class);
        cfg.addAnnotatedClass(Curriculum.class);
        cfg.addAnnotatedClass(Section.class);
        cfg.addAnnotatedClass(Topic.class);
        //welder test
        //personal protocol test
        cfg.addAnnotatedClass(CommissionCertification.class);
        cfg.addAnnotatedClass(ResolutionCertification.class);
        cfg.addAnnotatedClass(NDTDocument.class);
        cfg.addAnnotatedClass(TheoryTest.class);
        cfg.addAnnotatedClass(PersonalProtocol.class);
        //personal protocol test

        cfg.configure("hibernate.cfg.xml");



        new SchemaExport(cfg).create(true,true);

        SessionFactory factory = cfg.buildSessionFactory();
        Session session = factory.getCurrentSession();

        session.beginTransaction();

        Electrode elctrd1 = new Electrode();
        elctrd1.setType("АНО-21");
        Electrode elctrd2 = new Electrode();
        elctrd2.setType("УОНИ");
        Electrode elctrd3 = new Electrode();
        elctrd3.setType("none");


        WeldWire wire1 = new WeldWire();
        wire1.setType("09ГС");
        WeldWire wire2 = new WeldWire();
        wire2.setType("09Г2С");

        WeldGas gas1 = new WeldGas();
        gas1.setType("CO2");
        WeldGas gas2 = new WeldGas();
        gas2.setType("C2Н2");
        WeldGas gas3 = new WeldGas();
        gas3.setType("Ar");

        SteelGroup sg = new SteelGroup();
        sg.setStGroup("W1");
        sg.setDescription("Углеродистые стали");

        SteelType st = new SteelType();
        st.setType("Сталь 20");
        st.setStGroup(sg);
        SteelType st2 = new SteelType();
        st2.setType("Ст. 2пс");
        st.setStGroup(sg);

        WeldDetail wdPlane = new WeldDetail();
        wdPlane.setType("Пластина");
        wdPlane.setCode("P");
        WeldDetail wdTube = new WeldDetail();
        wdTube.setType("Труба");
        wdTube.setCode("T");

        WeldPosition wp1 = new WeldPosition();
        wp1.setType("Нижнее");
        wp1.setCode("PA");
        WeldPosition wp2 = new WeldPosition();
        wp2.setType("Вертикальное");
        wp2.setCode("PF");

        PatternThickness patThickness1 = new PatternThickness();
        patThickness1.setThickness(2.0);
        PatternThickness patThickness2 = new PatternThickness();
        patThickness2.setThickness(5.0);

        PatternDiameter patD1 = new PatternDiameter();
        patD1.setDiameter(57.0);
        PatternDiameter patD2 = new PatternDiameter();
        patD2.setDiameter(76.0);

        Evaluation eva1 = new Evaluation();
        eva1.setType("Годен");
        Evaluation eva2 = new Evaluation();
        eva2.setType("Не годен");

        RadiationTest rt = new RadiationTest();
        rt.setEvaluation(eva2);
        rt.setDefects("2П0,8; 2Ш3х4; Нк25");
        rt.setSensitivity("0.2");
        rt.setNumber("14-001");
        rt.setProtDate(new Date());

        VisualTest vt = new VisualTest();
        vt.setEvaluation(eva1);
        vt.setDefects("ДНО");
        vt.setNumber("14-001vt");
        vt.setProtDate(new Date());

        MechanicalTest mechT = new MechanicalTest();
        mechT.setEvaluation(eva1);
        mechT.setNumber("14-002Mech");
        mechT.setProtDate(new Date());
        mechT.setAngle(120.0);

        WeldMethod wm111 = new WeldMethod();
        wm111.setCode("111");
        wm111.setName("РДЭ");

        WeldMethod wm135 = new WeldMethod();
        wm135.setCode("135");
        wm135.setName("ГС");

        WeldMethod wm141 = new WeldMethod();
        wm141.setCode("141");
        wm141.setName("ВИГ");

        WeldPattern weldPattern = new WeldPattern();
        weldPattern.setDiameter(patD1.getDiameter());
        weldPattern.setElectrode(elctrd3);
        weldPattern.setIsHeating(false);
        weldPattern.setIsHeatTreatment(false);
        weldPattern.setMark("14-001");
        weldPattern.setMechanicalTest(mechT);
        weldPattern.setRadiationTest(rt);
        weldPattern.setVisualTest(vt);
        weldPattern.setSteelType(st);
        weldPattern.setThickness(patThickness1.getThickness());
        weldPattern.setWeldMethod(wm135);
        weldPattern.setWeldWire(wire1);
        weldPattern.setWeldGas(gas1);
        weldPattern.setWeldDetail(wdPlane);



        mechT.setWeldPattern(weldPattern);
        rt.setWeldPattern(weldPattern);
        vt.setWeldPattern(weldPattern);

        //welder test

        Education edu = new Education();
        edu.setType("Высшее");

        Qualification qualif1 = new Qualification();
        qualif1.setType("Электросварщик");

        Qualification qualif2 = new Qualification();
        qualif2.setType("Электрогазосварщик");

        Qualification qualif3 = new Qualification();
        qualif3.setType("Газосварщик");

        Organization org1 = new Organization();
        org1.setName("ООО \"Новые технологии\"");
        org1.setAdress("г. Харьков, пр. Косиора, 100");
        org1.setPhone("+380 57 715 13 28");

        Organization org2 = new Organization();
        org2.setName("ООО \"Газавтоматика\"");
        org2.setAdress("г. Харьков, ул. Светлая, 16");
        org2.setPhone("+380 57 714 58 96");

        Job job1 = new Job();
        job1.setName("Электросварщик");

        Job job2 = new Job();
        job2.setName("Газосварщик");

        Job job3 = new Job();
        job3.setName("Электрогазосварщик");

        Job job4 = new Job();
        job4.setName("Аргонщик");

        Teacher tAshukin = new Teacher();
        tAshukin.setName("Александр");
        tAshukin.setSurname("Ашукин");
        tAshukin.setSecname("Владимирович");
        Teacher tChuyko = new Teacher();
        tChuyko.setName("Андрей");
        tChuyko.setSurname("Чуйко");
        tChuyko.setSecname("Анатольевич");
        Teacher tKrapiva = new Teacher();
        tKrapiva.setName("Дмитрий");
        tKrapiva.setSurname("Крапива");
        tKrapiva.setSecname("Анатольевич");
        Teacher tKasyanenko = new Teacher();
        tKasyanenko.setName("Светлана");
        tKasyanenko.setSurname("Касьяненко");
        tKasyanenko.setSecname("Александровна");



        Topic topic1 = new Topic();
        topic1.setOrderIndex(1);
        topic1.setTitle("Основы сварки плавлением");
        topic1.setDescription("Сущность процессов, напряжения и деформации при сварке, " +
                "понятия и показатели свариваемости");
        topic1.setTimelong(6.0);

        Topic topic2 = new Topic();
        topic2.setOrderIndex(2);
        topic2.setTitle("Сварные соединения и швы");
        topic2.setDescription("Классификация, положения при сварке, зачистка кромок");
        topic2.setTimelong(4.0);


        Section section1 = new Section();
        section1.setOrderIndex(1);
        section1.setTitle("Теория");
        section1.setDescription("Теория сварочного дела");

        Section section2 = new Section();
        section2.setOrderIndex(2);
        section2.setTitle("Практика");
        section2.setDescription("Практика сварочного дела");

        Curriculum curr = new Curriculum();
        curr.setDescription("Программа предаттестационной подготовки сварщиков 24 часа");
        curr.setTitle("Подготовка 24 часа");

        Journal j1 = new Journal();
        j1.setDateBegin(new Date());
        j1.setDateEnd(new Date());
        j1.setNumber("14-001");
        j1.getTeachers().add(tAshukin);
        j1.getTeachers().add(tChuyko);
        j1.setCurriculum(curr);

        Welder welder1 = new Welder();
        welder1.setName("Иван");
        welder1.setSecname("Дорофеевич");
        welder1.setSurname("Добрынин");
        welder1.setDocNumber("XA-1620");
        welder1.setBirthday(new Date());
        welder1.setDateBegin(new Date());
        welder1.setEducation(edu);
        welder1.setQualification(qualif1);
        welder1.setOrganization(org1);
        welder1.setJob(job3);

        Welder welder2 = new Welder();
        welder2.setName("Петр");
        welder2.setSurname("Петковский");
        welder2.setSecname("Ильич");
        welder2.setDocNumber("XA-1626");
        welder2.setBirthday(new Date());
        welder2.setDateBegin(new Date());
        welder2.setEducation(edu);
        welder2.setQualification(qualif3);
        welder2.setOrganization(org2);
        welder2.setJob(job4);

        wm111.getWelders().add(welder1);
        wm111.getWelders().add(welder2);
        wm135.getWelders().add(welder1);
        wm141.getWelders().add(welder2);

        j1.getWelders().add(welder1);
        j1.getWelders().add(welder2);

        curr.getSections().add(section1);
        curr.getSections().add(section2);

        //welder test

        //personal protocol test
        CommissionCertification comm = new CommissionCertification();
        comm.setHead(tChuyko);
        comm.setWeldSpecialist(tAshukin);
        comm.setNdtSpecialist(tKrapiva);
        comm.setSafetySpecialist(tKasyanenko);

        ResolutionCertification resolution = new ResolutionCertification();
        resolution.setTextResolution("Сварщик сертифицирован на работы повышенной опасности");

        TheoryTest thTest = new TheoryTest();
        thTest.setRating("Сдано");
        thTest.setTicketNumber("25");

        NDTDocument ndtNPAOP = new NDTDocument();
        ndtNPAOP.setFullName("Сосуды работающие под давлением");
        ndtNPAOP.setName("НПАОП 0.00-1.07-96");
        NDTDocument ndtDBN = new NDTDocument();
        ndtDBN.setFullName("Газоснабжение");
        ndtDBN.setName("ДБН В.2.5-20-2001");

        PersonalProtocol pp = new PersonalProtocol();
        pp.setWelder(welder1);
        pp.setJournal(j1);
        pp.setResolutionCertification(resolution);
        pp.setTheoryTest(thTest);
        pp.setDatePeriodicalCert(new Date());

        pp.getNdtDocuments().add(ndtDBN);
        pp.getNdtDocuments().add(ndtNPAOP);

        weldPattern.setPersonalProtocol(pp);
        //personal protocol test


        session.save(elctrd1);
        session.save(elctrd2);
        session.save(elctrd3);
        session.save(wire1);
        session.save(wire2);
        session.save(gas1);
        session.save(gas2);
        session.save(gas3);
        session.save(sg);
        session.save(st);
        session.save(st2);
        session.save(wdPlane);
        session.save(wdTube);
        session.save(wp1);
        session.save(wp2);
        session.save(patThickness1);
        session.save(patThickness2);
        session.save(patD1);
        session.save(patD2);
        session.save(eva1);
        session.save(eva2);
        session.save(rt);
        session.save(vt);
        session.save(mechT);
        session.save(wm111);
        session.save(wm135);
        session.save(wm141);
        session.save(weldPattern);

        //welder save
        session.save(edu);
        session.save(welder1);
        session.save(welder2);
        session.save(wm111);
        session.save(wm135);
        session.save(wm141);
        session.save(qualif1);
        session.save(qualif2);
        session.save(qualif3);
        session.save(org1);
        session.save(org2);
        session.save(job1);
        session.save(job2);
        session.save(job3);
        session.save(job4);
        session.save(j1);
        session.save(tAshukin);
        session.save(tChuyko);
        session.save(tKrapiva);
        session.save(tKasyanenko);
        session.save(curr);
        session.save(section1);
        session.save(section2);
        session.save(topic1);
        session.save(topic2);

        //welder save

        //personal_protocol save
        session.save(comm);
        session.save(resolution);
        session.save(thTest);
        session.save(ndtDBN);
        session.save(ndtNPAOP);
        session.save(pp);

        //personal_protocol save
        System.out.println("\n SYSOUT \n");
        session.getTransaction().commit();
    }

}


