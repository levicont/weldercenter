delete from welder;
delete from organization;
insert into organization (id, name, address, phone)
                   values(100, 'IBM', 'Michigan city', '100-123-456'),
                         (101, 'Microsoft', 'New-York city', '100-456-789');
insert into welder (id, name, surname, second_name, birthday, date_begin, education, job, qualification, organization_id, address, document_number)
                   values(100, '����','������','��������', '1984-12-20', '2000-01-01', '�������-�����������', '�������', '��������������', 100,'�������','17-005' ),
                         (101, '����','������','��������', '1987-10-10', '2001-01-01', '�������', '�������', '��������������', 100,'�������','17-008' ),
                         (102, '������','�������','��������', '1990-11-02', '2000-01-01', '������', '�������', '�����������', 101,'����','17-010' );