drop database if exists ServiHogar;
create database ServiHogar;
use ServiHogar;

create table servicio(
	codigo varchar(3) primary key,
    descripcion varchar(100) not null,
    horaServicio float not null,
    horaEmpleado float not null
) engine innodb;

insert into servicio values ('LIM','Limpieza',12.00, 8.00), 
						('FON','Fontanería',15.00, 10.00),
                        ('CAR','Carpintería',20.00, 17.00),
                        ('CME','Carpintería Metálica',19.00,13.00), 
                        ('ALB','Albañilería',18.00, 16.00), 
                        ('ELE','Electricidad',18.00, 15.00), 
                        ('GEN','Genérico',21.00, 10.00);
                        
create table cliente(
	nif varchar(9) primary key,
    nombre varchar(100) not null,
    telefono varchar(100)    
) engine innodb;
insert into cliente values ('100A','José Fernández','611223344'),('200A','Copiex','622334455');

    
create table presupuesto(
	codigo  int primary key auto_increment,
    fecha date not null,
    cliente varchar(9) not null,
    foreign key (cliente) references cliente(nif) on update cascade on delete restrict
)engine innodb;

create table detalle_presupuesto(
	presupuesto int,
    servicio varchar(3),
    descripcion varchar(100) not null,
    horas int not null,
    importe float not null,
    primary key(presupuesto, servicio),
    foreign key (presupuesto) references presupuesto(codigo) on update cascade on delete restrict,
    foreign key (servicio) references servicio(codigo) on update cascade on delete restrict
) engine innoDB;
insert into presupuesto values (1, 20230201, '100A'),(2, 20230302, '100A'),(3, 20230302, '200A');
insert into detalle_presupuesto values (1, 'LIM','Limpieza Casa Campo',10,120),(1, 'GEN','Colocar 2 lámparas',2,42),
 (2, 'ELE','Instalación Enchufe',2,36),(2, 'ALB','Instalación Canaleta',2,36), (3, 'LIM','Limpieza Garajes',5,60),(3, 'CME','Colocar ventana',3,57);


    select * from presupuesto;
    

