delete from welder;
delete from organization;
delete from education;

insert into education (id, education)
                   values(100, 'среднее'),
                         (101, 'среднее-специальное'),
                         (102, 'среднее-техническое'),
                         (103, 'высшее');

insert into organization (id, name, address, phone)
                   values(100, 'IBM', 'Michigan city', '100-123-456'),
                         (101, 'Microsoft', 'New-York city', '100-456-789');
insert into welder (id, name, surname, second_name, birthday, date_begin, education, job, qualification, organization_id, address, document_number)
                   values(100, 'Иван','Иванов','Иванович', '1984-12-20', '2000-01-01', 'среднее-специальное', 'сварщик', 'электросварщик', 100,'Харьков','17-005' ),
                         (101, 'Петр','Петров','Иванович', '1987-10-10', '2001-01-01', 'среднее', 'сварщик', 'электросварщик', 100,'Харьков','17-008' ),
                         (102, 'Максим','Ковалев','Петрович', '1990-11-02', '2000-01-01', 'высшее', 'сварщик', 'газосварщик', 101,'Киев','17-010' ),
                         (103, 'Том','Андерсон','Джонович', '1985-08-05', '2003-01-01', 'неполное высшее', 'сварщик', 'электро-газосварщик', 101,'Киев','17-012' );