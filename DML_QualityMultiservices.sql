-- **** INSERCIÓN DE DATOS ****

-- Inserción de datos en la tabla oficio
INSERT INTO oficio VALUES (0, "Plomero");
INSERT INTO oficio VALUES (0, "Fontanero");
INSERT INTO oficio VALUES (0, "Herrero");
INSERT INTO oficio VALUES (0, "Albañil");
INSERT INTO oficio VALUES (0, "Enfermero");

SELECT * FROM oficio;

-- Inserción de datos en la tabla cliente
CALL insertarCliente('Pio', '....', 'Leon Gto', 'carlos@gmail.com', '123456');
CALL insertarCliente('Carlos', '....', 'Silao Gto', 'Ari@gmail.com', '123456');
CALL insertarCliente('Gabo', '....', 'Irapuato Gto', 'gabo@gmail.com', '123456');
CALL insertarCliente('Milton', '....', 'La Roncha Gto', 'pinia@gmail.com', '123456');
CALL insertarCliente('Luis', '....', 'Washinton', 'garcia@gmail.com', '123456');
CALL insertarCliente('Raul', '....', 'Houston', 'garcia@gmail.com', '123456');
