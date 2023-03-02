drop database if exists tenis;
create database tenis;
use tenis;

create table jugador(
	codigo int primary key auto_increment,	
    nombre varchar(100) not null
)engine innodb;
insert into jugador(nombre)  values 
('Nadal'),
('Badosa'),
('Muguruza'),
('Alacaraz'),
('Ferrer'),
('Suárez');

create table partido(
	codigo int primary key auto_increment,
    fecha date not null,
    numSets int not null default 3,
	ganador int null,
    foreign key(ganador) references jugador(codigo)
)engine innodb;
create table jugador_partido(
	partido int,
    jugador int not null,
    resultado varchar(100) null,
    primary key(partido,jugador),
    foreign key(partido) references partido(codigo),
    foreign key(jugador) references jugador(codigo)    
)engine innodb;
			
            
            
            
select * from partido;
ganadorselect * from jugador;