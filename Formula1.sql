
create type circuito as(
	nombre varchar(50),
	nVueltas int,
	curvas int,
	longitud float
);

create table equipo(
	nombre varchar(100) primary key,
	director varchar(100) not null
);

create table carrera(
	codigo varchar(3) primary key,
	cir circuito not null,
	podio text[][]
);
create table persona(
	codigo int primary key,
	nombre varchar(100) not null,
	edad int not null,
	nacionalidad varchar(50) not null,
	equipo varchar(100) not null,
foreign key (equipo) references equipo(nombre) on update cascade on delete restrict
);

create table piloto(
	dorsal int not null,
	carrerasGanadas int not null,
	podios int not null
) inherits (persona);

create table mecanico(
	especialidad varchar(50) not null,
	aniosExp int not null
)inherits (persona)

