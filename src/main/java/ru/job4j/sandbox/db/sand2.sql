create table students(
    id serial primary key, "name" text
);

create table subjects(
    id serial primary key, "name" text
);

create table students_subjects(
    id serial primary key,
    mark numeric(3, 1),
    student_id int references students(id),
    subject_id int references subjects(id)
);

insert into students("name") values ('Аня'), ('Ваня'), ('Боря');
insert into subjects("name") values ('Математика'), ('Русский'), ('Информатика');

insert into students_subjects(student_id, subject_id, mark) values (1, 1, 1), (1, 2, 4), (1, 3, 7);
insert into students_subjects(student_id, subject_id, mark) values (2, 1, 2), (2, 2, 5), (2, 3, 8);
insert into students_subjects(student_id, subject_id, mark) values (3, 1, 3), (3, 2, 6), (3, 3, 9);

select s."name", sb."name", ss.mark from students_subjects  ss
join subjects sb on sb.id = subject_id
join students s on s.id = student_id

select avg(mark) from students_subjects;
select min(mark) from students_subjects;
select max(mark) from students_subjects;

select s."name", avg(ss.mark)
from students_subjects as ss
join subjects s
on ss.subject_id = s.id
group by s."name";

select s."name", avg(ss.mark)
from students_subjects as ss
join students s
on ss.student_id = s.id
group by s."name";

select s."name", avg(ss.mark)
from students_subjects as ss
join subjects s
on ss.subject_id = s.id
group by s."name"
having avg(ss.mark) > 4.2;
