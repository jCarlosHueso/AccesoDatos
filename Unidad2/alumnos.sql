drop database if exists alumnos;
create database alumnos;
use alumnos;

create table alumnos(
	codigo int primary key auto_increment,
    nombre varchar(20) not null,
    fechaN date not null
)engine innodb;
create table notas(
	alumno int not null,
    asig varchar(5) not null,
    nota int not null,
    primary key(alumno,asig),
    foreign key (alumno) references alumnos(codigo) 
       on update cascade on delete restrict
)engine innodb;