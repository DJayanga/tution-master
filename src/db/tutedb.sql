DROP DATABASE tution_master;

CREATE DATABASE tution_master;

USE tution_master;

CREATE TABLE class
(
    class_id   int AUTO_INCREMENT not null,
    class_name varchar(100)       not null,
    year_name  varchar(100)       not null,
    CONSTRAINT PRIMARY KEY (class_id)
);

CREATE TABLE student
(
    stu_id       varchar(100) not null,
    full_name    varchar(100) not null,
    address      varchar(100),
    gender       varchar(100) not null,
    telephone_no varchar(100),
    class_id     int          not null,
    CONSTRAINT PRIMARY KEY (stu_id),
    CONSTRAINT FOREIGN KEY (class_id) REFERENCES class (class_id)
        ON UPDATE CASCADE ON DELETE CASCADE
);

Insert into class
values (0, 'Grade 06', '2019');
Insert into class
values (0, 'Grade 07', '2019');
Insert into class
values (0, 'Grade 08', '2019');
Insert into class
values (0, 'Grade 09', '2019');
Insert into class
values (0, 'Grade 10', '2019');
Insert into class
values (0, 'Grade 11', '2019');
Insert into class
values (0, 'Grade 06', '2020');
Insert into class
values (0, 'Grade 07', '2020');
Insert into class
values (0, 'Grade 08', '2020');
Insert into class
values (0, 'Grade 09', '2020');
Insert into class
values (0, 'Grade 10', '2020');
Insert into class
values (0, 'Grade 11', '2020');

Insert into student
values ('STU-00000001', 'Dileepa Nipun', 'Walapothugoda Watta, Nayapamula, Baddegama.', 'male', '0771234567', 1);
Insert into student
values ('STU-00000002', 'Sapumal Vithanage', 'Galle Rd, Dikwalla, Galle', 'male', '0751234567', 1);
Insert into student
values ('STU-00000003', 'Dayasiri Perera', 'Manampita Junciton, Manampita.', 'male', '0761234567', 1);

Insert into student
values ('STU-00000004', 'Ranasinghe Premadasa', 'Walapothugoda Watta, Nayapamula, Baddegama.', 'male', '0781234567', 2);
Insert into student
values ('STU-00000005', 'Mithun Wijenayake', 'Manampita Junciton, Manampita.', 'male', '0761234567', 2);
Insert into student
values ('STU-00000006', 'Sara Dalas Nileka', 'Benthotage Mawata, Benthota Junction, Benthota', 'female', '0751234567', 2);

Insert into student
values ('STU-00000007', 'Nimal Ashinsana', 'Galle Rd, Dikwalla, Galle', 'male', '0781234567', 7);
Insert into student
values ('STU-00000008', 'Monika Ruwanpathirana', 'Benthotage Mawata, Benthota Junction, Benthota', 'female', '0781234567', 7);
Insert into student
values ('STU-00000009', 'Dalas Athukorala', 'Manampita Junciton, Manampita.', 'male', '0751234567', 7);

Insert into student
values ('STU-00000010', 'Athma Liyanage', 'Manampita Junciton, Manampita.', 'male', '0761234567', 11);
Insert into student
values ('STU-00000011', 'Nimal Lansa', 'Galle Rd, Dikwalla, Galle', 'male', '0781234567', 11);
Insert into student
values ('STU-00000012', 'Manasiri Gamage', 'Benthotage Mawata, Benthota Junction, Benthota', 'male', '0751234567', 11);

SELECT * FROM student A INNER JOIN class B ON A.class_id = B.class_id;

SELECT * FROM student A INNER JOIN class B ON A.class_id = B.class_id WHERE A.full_name LIKE '%Sa%';
SELECT * FROM student A INNER JOIN class B ON A.class_id = B.class_id WHERE A.telephone_no LIKE '%07%';
SELECT * FROM student A INNER JOIN class B ON A.class_id = B.class_id WHERE B.year_name='2019' AND B.class_name='Grade 07';

SELECT c.class_name, c.year_name, COUNT(s.stu_id)
FROM student s
         LEFT JOIN class c ON c.class_id = s.class_id AND c.year_name = '2020'
GROUP BY c.class_id;

SELECT c.class_name, c.year_name, COUNT(s.stu_id)
FROM student s
         LEFT JOIN class c ON c.class_id = s.class_id
GROUP BY c.class_id;

SELECT c.class_name, c.year_name, COUNT(s.stu_id) FROM student s LEFT JOIN class c ON c.class_id = s.class_id WHERE c.year_name='2020' GROUP BY c.year_name;

# DELETE FROM class
# WHERE Not EXISTS
#     (
#         SELECT *
#         FROM student As B
#         Where B.class_id = 12
#     )