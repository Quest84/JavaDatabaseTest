DROP DATABASE IF EXISTS NEGOCIO;

CREATE DATABASE NEGOCIO;

USE NEGOCIO;

CREATE TABLE PRODUCTO(

	id int PRIMARY KEY AUTO_INCREMENT,
       	Nombre varchar(40),
	Marca varchar(40),
	Precio float,
	Stock int
);

INSERT INTO PRODUCTO (Nombre, Marca, Precio, Stock) VALUES
	( "Manguera", "Marca1", 100, 90 ),
	( "Manguera", "Marca2", 200, 80 ),
	( "Manguera", "Marca3", 300, 70 ),
	( "Manguera", "Marca4", 400, 60 ),
	( "Manguera", "Marca5", 500, 50 ),
	( "Manguera", "Marca6", 600, 40 ),
	( "Manguera", "Marca7", 700, 30 ),
	( "Manguera", "Marca8", 800, 20 ),
	( "Manguera", "Marca9", 900, 10 ),

	( "Aceite", "Marca1", 100, 20 ),
	( "Aceite", "Marca2", 100, 20 ),
	( "Aceite", "Marca3", 100, 20 ),
	( "Aceite", "Marca4", 100, 20 ),
	( "Aceite", "Marca5", 100, 20 ),
	( "Aceite", "Marca6", 100, 20 ),
	( "Aceite", "Marca7", 100, 20 ),
	( "Aceite", "Marca8", 100, 20 ),
	( "Aceite", "Marca9", 100, 20 ),

	( "Bujias", "Marca1", 100, 20 ),
	( "Bujias", "Marca2", 100, 20 ),
	( "Bujias", "Marca3", 100, 20 ),
	( "Bujias", "Marca4", 100, 20 ),
	( "Bujias", "Marca5", 100, 20 ),
	( "Bujias", "Marca6", 100, 20 ),
	( "Bujias", "Marca7", 100, 20 ),
	( "Bujias", "Marca8", 100, 20 ),
	( "Bujias", "Marca9", 100, 20 );
