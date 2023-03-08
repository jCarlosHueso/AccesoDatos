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
)inherits (persona);



-- Insertamos algunos equipos
INSERT INTO equipo (nombre, director)
VALUES ('Mercedes-AMG Petronas Formula One Team', 'Toto Wolff'),
       ('Red Bull Racing Honda', 'Christian Horner'),
       ('Scuderia Ferrari Mission Winnow', 'Mattia Binotto'),
	   ('McLaren Racing', 'Zak Brown'),
	   ('Aston Martin Aramco Cognizant', 'Mike Krack');

-- Insertamos algunas carreras
INSERT INTO carrera (codigo, cir, podio)
VALUES ('ESP', ROW('Circuito de Barcelona-Catalunya', 66, 16, 4.655), '{{"Lewis Hamilton", "Mercedes-AMG Petronas Formula One Team"}, {"Max Verstappen", "Red Bull Racing Honda"}, {"Valtteri Bottas", "Mercedes-AMG Petronas Formula One Team"}}'),
       ('ITA', ROW('Autodromo Nazionale di Monza', 53, 11, 5.793), '{{"Daniel Ricciardo", "McLaren Racing"}, {"Lando Norris", "McLaren Racing"}, {"Valtteri Bottas", "Mercedes-AMG Petronas Formula One Team"}}'),
       ('MON', ROW('Circuit de Monaco', 78, 19, 3.337), '{{"Max Verstappen", "Red Bull Racing Honda"}, {"Carlos Sainz", "Scuderia Ferrari Mission Winnow"}, {"Lando Norris", "McLaren Racing"}}');

-- Insertamos algunas personas (pilotos y mecánicos)
	  
	  INSERT INTO piloto (codigo, nombre, edad, nacionalidad, equipo, dorsal, carrerasGanadas, podios)
VALUES (1, 'Lewis Hamilton', 37, 'Reino Unido', 'Mercedes-AMG Petronas Formula One Team', 44, 103, 177),
       (2, 'Max Verstappen', 24, 'Países Bajos', 'Red Bull Racing Honda',1, 35, 77),
       (3, 'Valtteri Bottas', 32, 'Finlandia', 'Mercedes-AMG Petronas Formula One Team', 77, 9, 58),
       (4, 'Sergio Pérez', 31, 'México', 'Red Bull Racing Honda', 11, 2, 11),
       (5, 'Charles Leclerc', 24, 'Mónaco', 'Scuderia Ferrari Mission Winnow', 5, 2, 14),
       (6, 'Carlos Sainz', 27, 'España', 'Scuderia Ferrari Mission Winnow', 55, 2, 7),
       (7, 'Daniel Ricciardo', 32, 'Australia', 'McLaren Racing', 3, 8, 32),
       (8, 'Lando Norris', 22, 'Reino Unido', 'McLaren Racing', 4, 1, 9),
	   (9, 'Fernando Alonso', 41, 'España', 'Aston Martin Aramco Cognizant', 14, 32, 99),
       (10, 'Lance Stroll', 24, 'Canada', 'Aston Martin Aramco Cognizant', 4, 0, 3);
	   
	   INSERT INTO mecanico (codigo, nombre, edad, nacionalidad, equipo, especialidad, aniosExp)
VALUES (12, 'Andrew Shovlin', 43, 'Reino Unido', 'Mercedes-AMG Petronas Formula One Team', 'Ingeniero de Carrera', 20),
       (13, 'Jonathan Wheatley', 54, 'Reino Unido', 'Red Bull Racing Honda', 'Director de Carrera', 30),
       (14, 'Jock Clear', 58, 'Reino Unido', 'Scuderia Ferrari Mission Winnow', 'Ingeniero de Carrera', 30),
       (15, 'Andrea Stella', 46, 'Italia', 'McLaren Racing', 'Director de Carrera', 20);

	   
	   
	   