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
    fechaL date not null, -- Fecha de lanzamiento
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

delimiter //
-- Devuelve el tipo del usuario si us y pas son correctos
-- si no, devuleve NE
create function login(us varchar(10), pas varchar(10)) 
	returns varchar(2) deterministic
begin
	declare vTipo varchar(1);
    
	select tipo
		into vTipo
        from usuario
        where usuario = us and ps = sha2(pas,512);
	if(vtipo is null) then
		return 'NE';
    else
		return vTipo;
    end if;
end//    
create procedure borrarUsuario(usAdmin varchar(10), usBorrar varchar(10))
begin
	declare vTexto varchar(100);
    declare vNA, vNAl, vNC int;
	
    start transaction;
	-- Calcular datos a borrar
    -- Nº Artista
    select count(*)
		into vNA
		from artista where usuario = usBorrar;
    -- Nº Álbumes
    select count(*)
		into vNAl
		from artista ar inner join album al on ar.id = al.artista
        where ar.usuario = usBorrar;
	-- Nº Canciones
    select count(*)
		into vNC
		from artista ar inner join album al on ar.id = al.artista
			inner join cancion c on c.album = al.id
        where ar.usuario = usBorrar;
    
    delete from usuario where usuario = usBorrar;
    
    if (row_count()=1) then 
		set vTexto = concat('Se ha borrado ',row_count(),' usuario ',
		vNA, ' artistas ', vNAl, ' álbumes ', vNC, ' canciones');
		insert into tablalog values (null,usAdmin,
		now(),vTexto, usBorrar);
        if(row_count()=1) then 
			commit;
		else
			rollback;
			set vTexto = "Error al insertar en log";
        end if;
	else
		rollback;
        set vTexto = "Error al borrar el usuario";
    end if;
    select vTexto;
    
end//
