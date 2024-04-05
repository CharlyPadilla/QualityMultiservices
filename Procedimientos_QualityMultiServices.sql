-- ************* SCRIPT DE PROCEDIMIENTOS ************* --

-- Procedimiento registrar a un usuario tipo cliente
DROP PROCEDURE IF EXISTS insertarCliente;
DELIMITER $$
CREATE PROCEDURE insertarCliente (
	-- OUT idUsuario INT,
    IN nombreUsuario VARCHAR(50),
    IN imagenPerfil LONGTEXT,
    IN ciudad VARCHAR(60),
    IN correo VARCHAR(80),
    IN contrasenia VARCHAR(60)
    -- out idCliente INT
)
BEGIN 
	INSERT INTO usuario VALUES (0, nombreUsuario, imagenPerfil, ciudad, correo, contrasenia);
    INSERT INTO cliente VALUES (0, (SELECT idUsuario FROM usuario ORDER BY idUsuario DESC LIMIT 1));
END $$
DELIMITER ; 



--     ---PROCEDIMIENTOS PARA PETICIÓN---     --

-- Procedimiento para registrar una publicación tipo petición
DROP PROCEDURE IF EXISTS registrarPublicacionPeticion;
DELIMITER $$
CREATE PROCEDURE registrarPublicacionPeticion(
	IN idUsuario INT,
	IN descripcion VARCHAR(255),
    IN cadenaFoto LONGTEXT,
    IN idOficio INT
    -- OUT idPeticionInsertada INT
)
BEGIN
	INSERT INTO publicacion (idUsuario, descripcion, fechaCreacion) VALUES (idUsuario, descripcion, (SELECT CURRENT_TIMESTAMP()));
    INSERT INTO peticion (idOficioBuscado, idPublicacion) VALUES 
		(idOficio, (SELECT idPublicacion FROM publicacion ORDER BY idPublicacion DESC LIMIT 1));
	INSERT INTO fotoPublicacion VALUES (0, cadenaFoto, (SELECT idPublicacion FROM publicacion ORDER BY idPublicacion DESC LIMIT 1));
    SELECT idPeticion FROM peticion ORDER BY idPeticion DESC LIMIT 1;
END $$
DELIMITER ;
/* CALL registrarPublicacionPeticion(
    1, -- ID del usuario
    'Descripción de la publicación', -- Descripción de la publicación
    'Cadena de foto en formato largo', -- Cadena de foto en formato largo
    1 -- ID del oficio
);*/


-- Procedimineto para mostrar los datos de las publicaciones tipo petición de un usuario
DROP PROCEDURE IF EXISTS mostrarPublicacionesPeticion;
DELIMITER $$
CREATE PROCEDURE mostrarPublicacionesPeticion(
	IN idUsuario INT
)
BEGIN 
	SELECT 
        u.nombreUsuario AS nombreUsuario,
        p.idPublicacion,
        p.descripcion AS descripcionPublicacion,
        p.fechaCreacion AS fechaCreacion,
        p.fechaEdicion AS fechaEdicion,
        pet.idPeticion,
        o.nombreOficio AS nombreOficio,
        fp.cadenaFoto AS fotoPublicacion
    FROM 
        usuario u
        JOIN publicacion p ON u.idUsuario = p.idUsuario
        JOIN peticion pet ON p.idPublicacion = pet.idPublicacion
        JOIN oficio o ON pet.idOficioBuscado = o.idOficio
		JOIN fotoPublicacion fp ON p.idPublicacion = fp.idPublicacion
    WHERE 
        u.idUsuario = idUsuario;
END $$
DELIMITER ;
-- CALL mostrarPublicacionesPeticion(1);


-- Procedimineto para mostrar los datos de UNA publicación tipo petición de un usuario
DROP PROCEDURE IF EXISTS mostrarPublicacionPeticion;
DELIMITER $$
CREATE PROCEDURE mostrarPublicacionPeticion(
	IN idPeticion INT
)
BEGIN 
	SELECT 
        u.nombreUsuario AS nombreUsuario,
        p.idPublicacion,
        p.descripcion AS descripcionPublicacion,
        p.fechaCreacion AS fechaCreacion,
        p.fechaEdicion AS fechaEdicion,
        pet.idPeticion,
        o.nombreOficio AS nombreOficio,
        fp.cadenaFoto AS fotoPublicacion
    FROM 
        peticion pet
        JOIN publicacion p ON pet.idPublicacion = p.idPublicacion
        JOIN usuario u ON p.idUsuario = u.idUsuario
        JOIN oficio o ON pet.idOficioBuscado = o.idOficio
		JOIN fotoPublicacion fp ON p.idPublicacion = fp.idPublicacion
    WHERE 
        pet.idPeticion = idPeticion;
END $$
DELIMITER ;
-- CALL mostrarPublicacionPeticion(1);

	
-- Procedimiento para actualizar una publicación tipo petición
DROP PROCEDURE IF EXISTS actualizarPublicacionPeticion;
DELIMITER $$
CREATE PROCEDURE actualizarPublicacionPeticion(
	IN in_idPeticion INT,
	IN in_descripcion VARCHAR(255),
    IN in_cadenaFoto LONGTEXT,
    IN in_idOficio INT
)
BEGIN
	 DECLARE in_idPublicacion INT;
        
    -- Obtener el id de la publicación asociada al anuncio
    SELECT idPublicacion INTO in_idPublicacion FROM peticion WHERE idPeticion = in_idPeticion;

	UPDATE publicacion 
		SET descripcion=in_descripcion, fechaEdicion= (SELECT CURRENT_TIMESTAMP()) WHERE idPublicacion= in_idPublicacion;
    
    UPDATE peticion SET idOficioBuscado= in_idOficio WHERE idPublicacion= in_idPublicacion;
    
    UPDATE fotoPublicacion SET cadenaFoto = in_cadenaFoto WHERE idPublicacion= in_idPublicacion;
    
    SELECT in_idPeticion;
	/*CALL mostrarPublicacionPeticion((SELECT idPeticion FROM peticion 
    WHERE idPublicacion = (SELECT idPublicacion FROM publicacion ORDER BY fechaEdicion DESC LIMIT 1)));*/

END $$
DELIMITER ;
/*CALL actualizarPublicacionPeticion(
    1, -- ID de la petición
    'Descripción de petición', -- Descripción de la publicación
    'Cadena de foto en formato LONGTEXT', -- Cadena de foto en formato largo
    2 -- ID del oficio
);*/

-- Procedimiento para eliminar una publicación tipo petición
DROP PROCEDURE IF EXISTS eliminarPublicacionPeticion;
DELIMITER $$
CREATE PROCEDURE eliminarPublicacionPeticion(
    IN idPeticionEliminar INT
)
BEGIN
	DECLARE numPubAntesEliminacion INT;
	DECLARE numPubDespuesEliminacion INT;
    DECLARE idPublicacionEliminar INT;
    
    SET numPubAntesEliminacion = (SELECT COUNT(*) FROM peticion);
    
    -- Obtener el id de la publicación asociada a la petición
    SELECT idPublicacion INTO idPublicacionEliminar FROM peticion WHERE idPeticion = idPeticionEliminar;

    -- Eliminar registros de la tabla fotoPublicacion relacionados con la publicación a eliminar
    DELETE FROM fotoPublicacion WHERE idPublicacion = idPublicacionEliminar;

    -- Eliminar registros de la tabla peticion relacionados con la publicación a eliminar
    DELETE FROM peticion WHERE idPeticion = idPeticionEliminar;

    -- Eliminar la publicación de la tabla publicacion
    DELETE FROM publicacion WHERE idPublicacion = idPublicacionEliminar;
    
    SET numPubDespuesEliminacion = (SELECT COUNT(*) FROM peticion);
    SELECT numPubAntesEliminacion, numPubDespuesEliminacion;
END$$
DELIMITER ;
-- CALL eliminarPublicacionPeticion(10);



--     ---PROCEDIMIENTOS PARA ANUNCIO---     --

-- Procedimiento para registrar una publicación tipo anuncio
DROP PROCEDURE IF EXISTS registrarPublicacionAnuncio;
DELIMITER $$
CREATE PROCEDURE registrarPublicacionAnuncio(
	IN idUsuario INT,
	IN descripcion VARCHAR(255),
    IN cadenaFoto LONGTEXT,
    IN idOficio INT
    -- OUT idPeticionInsertada INT
)
BEGIN
	INSERT INTO publicacion (idUsuario, descripcion, fechaCreacion) VALUES (idUsuario, descripcion, (SELECT CURRENT_TIMESTAMP()));
    INSERT INTO anuncio (idOficioOfrecido, idPublicacion) VALUES 
		(idOficio, (SELECT idPublicacion FROM publicacion ORDER BY idPublicacion DESC LIMIT 1));
	INSERT INTO fotoPublicacion VALUES (0, cadenaFoto, (SELECT idPublicacion FROM publicacion ORDER BY idPublicacion DESC LIMIT 1));
    SELECT idAnuncio FROM anuncio ORDER BY idAnuncio DESC LIMIT 1;
END $$
DELIMITER ;
 /*CALL registrarPublicacionAnuncio(
    2, -- ID del usuario
    'Descripción de la publicación', -- Descripción de la anuncio
    'Cadena de foto LONG', -- Cadena de foto en formato LARGE
    2 -- ID del oficio
);*/


-- Procedimineto para mostrar los datos de las publicaciones tipo anuncio de un usuario
DROP PROCEDURE IF EXISTS mostrarPublicacionesAnuncio;
DELIMITER $$
CREATE PROCEDURE mostrarPublicacionesAnuncio(
	IN idUsuario INT
)
BEGIN 
	SELECT 
        u.nombreUsuario,
        p.idPublicacion,
        p.descripcion AS descripcionPublicacion,
        p.fechaCreacion,
        p.fechaEdicion,
        an.idAnuncio,
        o.nombreOficio,
        fp.cadenaFoto AS fotoPublicacion
    FROM 
        usuario u
        JOIN publicacion p ON u.idUsuario = p.idUsuario
        JOIN anuncio an ON p.idPublicacion = an.idPublicacion
        JOIN oficio o ON an.idOficioOfrecido = o.idOficio
		JOIN fotoPublicacion fp ON p.idPublicacion = fp.idPublicacion
    WHERE 
        u.idUsuario = idUsuario;
END $$
DELIMITER ;
-- CALL mostrarPublicacionesAnuncio(2); -- Se pasa el id del usuario


-- Procedimineto para mostrar los datos de UNA publicación tipo anuncio de un usuario
DROP PROCEDURE IF EXISTS mostrarPublicacionAnuncio;
DELIMITER $$
CREATE PROCEDURE mostrarPublicacionAnuncio(
	IN idAnuncio INT
)
BEGIN 
	SELECT 
        u.nombreUsuario AS nombreUsuario,
        p.idPublicacion,
        p.descripcion AS descripcionPublicacion,
        p.fechaCreacion AS fechaCreacion,
        p.fechaEdicion AS fechaEdicion,
        a.idAnuncio,
        o.nombreOficio AS nombreOficio,
        fp.cadenaFoto AS fotoPublicacion
    FROM 
        anuncio a
        JOIN publicacion p ON a.idPublicacion = p.idPublicacion
        JOIN usuario u ON p.idUsuario = u.idUsuario
        JOIN oficio o ON a.idOficioOfrecido = o.idOficio
		JOIN fotoPublicacion fp ON p.idPublicacion = fp.idPublicacion
    WHERE 
        a.idAnuncio = idAnuncio;
END $$
DELIMITER ;
-- CALL mostrarPublicacionAnuncio(5);



-- Procedimiento para actualizar una publicación tipo anuncio
DROP PROCEDURE IF EXISTS actualizarPublicacionAnuncio;
DELIMITER $$
CREATE PROCEDURE actualizarPublicacionAnuncio(
	IN in_idAnuncio INT,
	IN in_descripcion VARCHAR(255),
    IN in_cadenaFoto LONGTEXT,
    IN in_idOficio INT
)
BEGIN
   DECLARE in_idPublicacion INT;
        
    -- Obtener el id de la publicación asociada al anuncio
    SELECT idPublicacion INTO in_idPublicacion FROM anuncio WHERE idAnuncio = in_idAnuncio;

	UPDATE publicacion 
		SET descripcion=in_descripcion, fechaEdicion= (SELECT CURRENT_TIMESTAMP()) WHERE idPublicacion= in_idPublicacion;
    
    UPDATE anuncio SET idOficioOfrecido= in_idOficio WHERE idPublicacion= in_idPublicacion;
    
    UPDATE fotoPublicacion SET cadenaFoto = in_cadenaFoto WHERE idPublicacion= in_idPublicacion;
    
    SELECT in_idAnuncio;
	/*CALL mostrarPublicacionAnuncio((SELECT idAnuncio FROM anuncio 
    WHERE idPublicacion = (SELECT idPublicacion FROM publicacion ORDER BY fechaEdicion DESC LIMIT 1)));*/
	
END $$
DELIMITER ;
/*CALL actualizarPublicacionAnuncio(
    8, -- ID del anuncio
    'Descripción anuncio', -- Descripción de la publicación
    'Cadena de foto en LONGTEXT', -- Cadena de foto en formato largo
    2 -- ID del oficio
);*/


-- Procedimiento para eliminar una publicación tipo anuncio
DROP PROCEDURE IF EXISTS eliminarPublicacionAnuncio;
DELIMITER $$
CREATE PROCEDURE eliminarPublicacionAnuncio(
    IN idAnuncioEliminar INT
)
BEGIN
	DECLARE numPubAntesEliminacion INT;
	DECLARE numPubDespuesEliminacion INT;
    DECLARE idPublicacionEliminar INT;
    
    SET numPubAntesEliminacion = (SELECT COUNT(*) FROM anuncio);
    
    -- Obtener el id de la publicación asociada a la anuncio
    SELECT idPublicacion INTO idPublicacionEliminar FROM anuncio WHERE idAnuncio = idAnuncioEliminar;

    -- Eliminar registros de la tabla fotoPublicacion relacionados con la publicación a eliminar
    DELETE FROM fotoPublicacion WHERE idPublicacion = idPublicacionEliminar;

    -- Eliminar registros de la tabla anuncio relacionados con la publicación a eliminar
    DELETE FROM anuncio WHERE idAnuncio = idAnuncioEliminar;

    -- Eliminar la publicación de la tabla publicacion
    DELETE FROM publicacion WHERE idPublicacion = idPublicacionEliminar;
    
    SET numPubDespuesEliminacion = (SELECT COUNT(*) FROM anuncio);
    SELECT numPubAntesEliminacion, numPubDespuesEliminacion;
END$$
DELIMITER ;
 -- CALL eliminarPublicacionAnuncio(2);


/*
-- Procedimiento para registrar una publicación tipo petición con más de una foto
DROP PROCEDURE IF EXISTS registrarPublicacionPeticion;
DELIMITER $$
CREATE PROCEDURE registrarPublicacionPeticion(
	IN idUsuario INT,
	IN descripcion VARCHAR(255),
    IN JSONCadenaFoto JSON,
    IN idOficio INT
)
BEGIN
	DECLARE i INT;  SET i =0;
	INSERT INTO publicacion (idUsuario, descripcion, fechaCreacion) VALUES (idUsuario, descripcion, NOW());
    INSERT INTO peticion (idOficioBuscado, idPublicacion) VALUES 
		(idOficio, (SELECT idPublicacion FROM publicacion ORDER BY idPublicacion DESC LIMIT 1));
        
        WHILE i < JSON_LENGTH(JSONCadenaFoto) DO
			INSERT INTO fotoPublicacion (idPublicacion, cadenaFoto) VALUES (idPublicacion, 
				(SELECT JSON_EXTRACT(JSONCadenaFoto, i)));
		END WHILE;
END $$
DELIMITER ;
	CALL registrarPublicacionPeticion(
    3, -- ID del usuario
    'Descripción de la publicación', -- Descripción de la publicación
    '["foto1.jpg", "foto2.jpg", "foto3.jpg"]', -- Cadena JSON con las fotos
    1 -- ID del oficio
);
SELECT * FROM publicacion;
 */

