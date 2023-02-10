drop database if exists saneamientos;
create database saneamientos;
use saneamientos;
create table animal(
	codigo int auto_increment primary key not null,
    nombre varchar(50)  not null,
    fechaN date not null,
    raza varchar(50) not null,
    bloqueado boolean default false
)engine Innodb;

insert into animal values
 (null, 'Paloma', 20000204, 'Limousin',default),
 (null, 'Lucero', 20021024, 'Suizo',default),
 (null, 'Estrella', 20020916, 'Limousin',default),
 (null, 'Mari', 20100730, 'Limousin',default),
 (null, 'Pepe', 20100428, 'Charolais',default);
 
create table veterinario(
	colegiado int primary key not null,
    nombre varchar(50) not null
)engine Innodb;

insert into veterinario values
(12345,'Pablo López'),
(23418,'María Calas'),
(91287,'Darío Martín'),
(65389,'Lucía Palma');

create table saneamiento(
	id varchar(10) primary key not null,
    fecha date  not null,
    veterinario int  not null,
    foreign key (veterinario) references veterinario(colegiado) on update cascade on delete restrict 
)engine Innodb;
insert into saneamiento values
('SEP-2000', 20000915, 65389),
('DIC-2002', 20021209, 12345);

create table resultadoSaneamiento(
	saneamiento varchar(10)  not null,
    animal int  not null,
    tuberculosis boolean default false,
    brucelosis boolean default false,
    fechaResultado date,
    primary key(saneamiento, animal),
    foreign key (saneamiento) references saneamiento(id) on update cascade on delete restrict,
    foreign key (animal) references animal(codigo) on update cascade on delete restrict
)engine Innodb;

insert into resultadoSaneamiento values
('SEP-2000',1,true,false,null),
('DIC-2002',1,false,false,null),
('DIC-2002',2,false,false,null),
('DIC-2002',3,true,true,null);