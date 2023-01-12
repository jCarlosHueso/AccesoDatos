drop database if exists FibraNaval ;
create database FibraNaval;
use FibraNaval;

create table cliente(
	idC int auto_increment  key,
    dni varchar(9) not null unique,    
    nombreC varchar(100) not null,
    fechaNac date not null,
	direccion varchar(100) not null,
    cp int not null default 10300
)engine InnoDB;
insert into cliente(dni,nombreC,fechaNac, direccion) values
	('1A','Ana Díaz',20000101,'Calle Antonio Concha, 1'),
	('2A','Luisa Amor',20000201,'Calle Pablo Luengo, 2'),
	('3A','Gema Contreas',20000301,'Ronda Sur, 1'),
	('4A','Margarita Flores',20000401,'Calle Deporte, 1'),
	('5A','Mónica Vaz',20000501,'Paseo de la Estación, 1'),
	('6A','Pilar Sanz',20000601,'Avenida de las Angustias, 1'),
    ('7A','Lucía Vilalr',20000701,'Calle Las Minas, 1');
    
create table servicio(
	idS int auto_increment  key,
    nombreS varchar(200) not null unique,    
    precio float not null
)engine InnoDB;

insert into servicio(nombreS, precio) values 
('Fibra 100',30.00),
('Fibra 300',40.00),
('Fibra 600',50.00),
('1ª Linea Móvil',20.00),
('2º Linea Móvil',20.00),
('Llamadas Ilimitadas',15.00),
('Datos Ilimitados 1ª Línea Móvil',15.00),
('Datos Ilimitados 2ª Línea Móvil',15.00),
('Identificación de llamadas',2.00),
('Televisión',20.00),
('Cine',10.0),
('Series',10.00),
('Deportes',10.00),
('Pack Fibra 600 + 1 Linea Movil + Llamadas Ilimitadas',80.00),
('Pack Fibra 600 + 2 Linea Movil + Llamadas Ilimitadas + Datos Ilimitados', 100.00);

create table servicioContratado(
	cliente int not null,
    servicio  int not null,
    fechaAlta date not null,
    fechaBaja date null,
    primary key(cliente, servicio),
    foreign key (cliente) references cliente(idC) on update cascade on delete restrict,
    foreign key (servicio) references servicio(idS) on update cascade on delete restrict
)engine InnoDB;

create table facturaGeneral(
	idF int primary key auto_increment,
    fecha date not null,
    cliente int not null,
    foreign key (cliente) references cliente(idC) on update cascade on delete restrict
)engine InnoDB;

create table facturaDetalle(
	factura int,
    servicio int,
    precio float not null,
    primary key(factura,servicio),
    foreign key (factura) references facturaGeneral(idF) on update cascade on delete restrict,
    foreign key (servicio) references servicio(idS) on update cascade on delete restrict
)engine InnoDB;

delimiter //
create procedure obtenerServiciosCliente(pIdC int)
begin
	select *
		from servicioContratado sc inner join servicio s on sc.servicio = s.idS
        where cliente = pIdC and fechaBaja is null;
end//