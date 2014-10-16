package com.lvg.weldercenter;

import com.lvg.weldercenter.models.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.tool.hbm2ddl.SchemaExport;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by victor on 14.10.2014.
 */
public class TestWelder {

    public static void main(String[] args) {
        AnnotationConfiguration cfg = new AnnotationConfiguration();
        cfg.addAnnotatedClass(Welder.class);
        cfg.addAnnotatedClass(Education.class);
        cfg.addAnnotatedClass(WeldMethod.class);
        cfg.addAnnotatedClass(Qualification.class);
        cfg.addAnnotatedClass(Organization.class);
        cfg.addAnnotatedClass(Job.class);
        cfg.addAnnotatedClass(Journal.class);
        cfg.addAnnotatedClass(Teacher.class);
        cfg.addAnnotatedClass(Curriculum.class);
        cfg.addAnnotatedClass(Section.class);
        cfg.addAnnotatedClass(Topic.class);
        cfg.configure("hibernate.cfg.xml");



        new SchemaExport(cfg).create(true,true);

        SessionFactory factory = cfg.buildSessionFactory();
        Session session = factory.getCurrentSession();

        session.beginTransaction();

        Education edu = new Education();
        edu.setType("Высшее");

        Qualification qualif1 = new Qualification();
        qualif1.setType("Электросварщик");

        Qualification qualif2 = new Qualification();
        qualif2.setType("Электро-газосварщик");

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
        job3.setName("Электро-газосварщик");

        Job job4 = new Job();
        job4.setName("Аргонщик");


        WeldMethod wm111 = new WeldMethod();
        wm111.setCode("111");
        wm111.setName("РДЭ");

        WeldMethod wm135 = new WeldMethod();
        wm135.setCode("135");
        wm135.setName("ГС");

        WeldMethod wm141 = new WeldMethod();
        wm141.setCode("141");
        wm141.setName("ВИГ");

        Teacher teach1 = new Teacher();
        teach1.setName("Александр");
        teach1.setSurname("Ашукин");
        teach1.setSecname("Владимирович");

        Teacher teach2 = new Teacher();
        teach2.setName("Андрей");
        teach2.setSurname("Чуйко");
        teach2.setSecname("Анатольевич");

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
        j1.getTeachers().add(teach1);
        j1.getTeachers().add(teach2);

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
        session.save(teach1);
        session.save(teach2);
        session.save(curr);
        session.save(section1);
        session.save(section2);
        session.save(topic1);
        session.save(topic2);

        session.getTransaction().commit();


        session = factory.openSession();
        session.beginTransaction();
        Welder welder3 = (Welder) session.get(Welder.class,1L);
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
        System.out.println("Welder was fetched from Db with name: "+welder3.getName()+
                "\n qualification: "+welder3.getQualification().getType()+
                "\n birthday: "+ format.format(welder3.getBirthday()));






    }

}
