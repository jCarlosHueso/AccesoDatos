-- Database: instituto

-- DROP DATABASE IF EXISTS instituto;

CREATE DATABASE instituto
    WITH
    OWNER = postgres
    ENCODING = 'UTF8'
    LC_COLLATE = 'en_US.UTF-8'
    LC_CTYPE = 'en_US.UTF-8'
    TABLESPACE = pg_default
    CONNECTION LIMIT = -1
    IS_TEMPLATE = False;
	-- Crear Base de datos instituto

-- Crear tipo de datos compuesto
create type miDireccion as(
	tipoVia varchar(50),
	nombre varchar(100),
	cp int
);

-- Crear tabla personas
create table persona(
	id serial primary key,
	nombre varchar(100) not null,
	dir miDireccion not null,
	fechaN date not null
);

create table alumno(
	numExpe int unique not null,
	curso varchar(100) not null,
	primary key(id)
)inherits (persona);

insert into alumno values 
	(default,'Pedro Pérez',('C\','Calle 1',10300),'2000/01/22',1234,'2DAM'),
	(default,'Sandra Sires',('Avda','Burgos 1',10300),'2000/04/02',4123,'2DAM'),
	(default,'Luna Sol',('Plaza','España 1',10310),'2000/10/14',9876,'2DAM');

create table profesor(
	departamento varchar(100) not null,
	salario float not null,
	primary key(id)
)inherits (persona);

insert into profesor values 
	(default,'Isabel Méndez',('C\','Calle 1',10400),'1980/01/12','Informática',1000.00),
	(default,'Carlos Núñez',('C\','Calle 4',10001),'1982/11/12','FOL',1100.00),
	(default,'Celia Cruz',('C\','Calle 5',10400),'1980/03/12','Informática',1200.00);

create table asignatura(
	codigo varchar(10) primary key,
	nombre varchar(100) not null
);
insert into asignatura values
	('AD','Acceso Datos'),
	('DI','Desarrollo de Interfaces'),
	('PMDM','Programación Multimedia y de Dispositivos Móviles');

create table nota(
	alumno int,
	asignatura varchar(10),
	notas text[][],
	primary key (alumno, asignatura),
	foreign key (alumno) references alumno(id) on update cascade
	 					 on delete restrict,
	foreign key (asignatura) references asignatura(codigo) on update cascade
	 					 on delete restrict
);