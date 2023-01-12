drop database if exists spotiflyP;
create database spotiflyP;
use spotiflyP;
create table usuario(
	usuario varchar(10) primary key,
    ps blob,
    tipo enum('A','U')
)engine innodb;
insert into usuario values ('admin',sha2('admin',512),'A');
insert into usuario values ('rosa',sha2('rosa',512),'U');
insert into usuario values ('pili',sha2('pili',512),'U');
create table artista(
	id int primary key auto_increment,
    nombre varchar(50)  not null,
    genero enum('POP', 'ROCK','OTROS') not null ,
    fechaL date not null, 
    seguir boolean default true not null,
    usuario varchar(10) not null,
    unique (nombre, usuario),
    foreign key (usuario) references usuario(usuario)
		on update cascade on delete cascade
)engine innodb;
insert into artista values
	(null, 'a1', 'pop', 20000201,true, 'rosa'),
    (null, 'a2', 'pop', 20000201,true, 'rosa'),
    (null, 'a1', 'pop', 20000201,true, 'pili');
create table album(
	id int primary key auto_increment,
    titulo varchar(100) not null,
    artista int  not null,
    anio int not null,
    unique(titulo, artista),
    foreign key (artista) references artista(id)
		on update cascade on delete cascade
)engine innodb;
insert into album values
	(null, 'album 1', 1, 2000),
    (null, 'album 2', 1, 2001),
    (null, 'album 1', 2, 2010),
	(null, 'album 2', 2, 2011),
    (null, 'album 1', 3, 2020),
	(null, 'album 2', 3, 2021);
create table cancion(
	titulo varchar(100) not null,
    album int not null,
    valoracion float,
    primary key(titulo, album),
    foreign key (album) references album(id)
		on update cascade on delete cascade
)engine innodb;
insert into cancion values 
	('c1',1, 1),
    ('c2',1, 8),
    ('c1',2, 1),
    ('c2',2, 8),
    ('c1',3, 1),
    ('c2',3, 8),
    ('c1',4, 1),
    ('c2',4, 8),
    ('c1',5, 1),
    ('c2',5, 8),
    ('c1',6, 1),
    ('c2',6, 8);
create table tablaLog(
	id int primary key auto_increment,
    usuario varchar(10) not null,
    fecha datetime not null,
    texto varchar(100),
    usBorrado varchar(10) not null,
    foreign key (usuario) references usuario(usuario)
		on update cascade on delete restrict
)engine innodb;


