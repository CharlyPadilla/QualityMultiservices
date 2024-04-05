DROP DATABASE IF EXISTS qualityMultiServices;
CREATE DATABASE qualityMultiServices;
USE qualityMultiServices;

-- *****CREACIÓN DE TABLAS****
-- Creación de la tabla padre Usuario
CREATE TABLE usuario (
	idUsuario INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	nombreUsuario VARCHAR(50),
    imagenPerfil LONGTEXT,
    ciudad VARCHAR(60),
    correo VARCHAR(80),
    contrasenia VARCHAR(60)
);

-- Creación de la tabla del usuario tipo vendedor
CREATE TABLE vendedor(
	idVendedor INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    oficio VARCHAR(40),
    aniosExperiencia INT,
    idUsuario INT,
    FOREIGN KEY (idUsuario) REFERENCES usuario(idUsuario)
);

-- Creación de la tabla del usuario tipo cliente
CREATE TABLE cliente(
	idCliente INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    idUsuario INT,
	FOREIGN KEY (idUsuario) REFERENCES usuario(idUsuario)
);

-- Creación de la tabla padre publicacion
CREATE TABLE publicacion(
	idPublicacion INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    idUsuario INT NOT NULL,
	descripcion VARCHAR(255),
    fechaCreacion DATE,
    fechaEdicion DATE,
    FOREIGN KEY (idUsuario) REFERENCES usuario(idUsuario)
);

-- Creación de la tabla que contendrá las fotos de las publicaciones
CREATE TABLE fotoPublicacion(
	idFotoPublicacion INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    cadenaFoto LONGTEXT,
    idPublicacion INT,
    FOREIGN KEY (idPublicacion) REFERENCES publicacion(idPublicacion)
);

-- Creación de la tabla oficios
CREATE TABLE oficio(
	idOficio INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    nombreOficio VARCHAR(50)
);

-- Creación de la tabla petición, hija de la tabla publicación
CREATE TABLE peticion(
	idPeticion INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    idOficioBuscado INT NOT NULL,
    idPublicacion INT,
	FOREIGN KEY (idPublicacion) REFERENCES publicacion(idPublicacion),
    FOREIGN KEY (idOficioBuscado) REFERENCES oficio(idOficio)
); 

-- Creación de la tabla anuncio, hija de la tabla publicación
CREATE TABLE anuncio(
	idAnuncio INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    idOficioOfrecido INT NOT NULL,
    idPublicacion INT,
    FOREIGN KEY (idPublicacion) REFERENCES publicacion(idPublicacion),
	FOREIGN KEY (idOficioOfrecido) REFERENCES oficio(idOficio)
);

-- Creación de la tabla chat
CREATE TABLE chat(
	idChat INT NOT NULL AUTO_INCREMENT PRIMARY KEY ,
    idCliente INT,
    idVendedor INT,
    fechaInicioConversacion DATE,
    FOREIGN KEY (idCliente) REFERENCES cliente(idCliente),
    FOREIGN KEY (idVendedor) REFERENCES vendedor(idVendedor)
);

-- Creación de la tabla mensaje, derivada de la tabla chat.
CREATE TABLE mensaje(
	idMensaje INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    idChat INT,
    fecha DATE,
    FOREIGN KEY (idChat) REFERENCES chat(idChat)
)
