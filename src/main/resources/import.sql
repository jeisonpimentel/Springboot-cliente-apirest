INSERT INTO regiones(id, nombre) VALUES(1, 'Sudamérica');
INSERT INTO regiones(id, nombre) VALUES(2, 'Centroamérica');
INSERT INTO regiones(id, nombre) VALUES(3, 'Norteamérica');
INSERT INTO regiones(id, nombre) VALUES(4, 'Europa');
INSERT INTO regiones(id, nombre) VALUES(5, 'Asia');
INSERT INTO regiones(id, nombre) VALUES(6, 'Africa');
INSERT INTO regiones(id, nombre) VALUES(7, 'Oceanía');
INSERT INTO regiones(id, nombre) VALUES(8, 'Artártida');

INSERT INTO clientes(region_id, nombre, apellido, email, create_at) VALUES(1, 'Jeison', 'Pimentel', 'jeisonalvis10@gmail.com', '2018-01-01');
INSERT INTO clientes(region_id, nombre, apellido, email, create_at) VALUES(2, 'Jennifer', 'Pimentel', 'jenniferpimentel54@gmail.com', '2018-11-16');
INSERT INTO clientes(region_id, nombre, apellido, email, create_at) VALUES(1, 'Jessica', 'Pimentel', 'jessicapimentel@gmail.com', '2018-01-25');
INSERT INTO clientes(region_id, nombre, apellido, email, create_at) VALUES(2, 'Liah', 'Pimentel', 'liahcata27@gmail.com', '2018-07-27');
INSERT INTO clientes(region_id, nombre, apellido, email, create_at) VALUES(3, 'Virginia', 'Casique', 'virgi07_casique@gmail.com', '2018-03-07');
INSERT INTO clientes(region_id, nombre, apellido, email, create_at) VALUES(1, 'Miriam', 'Alvis', 'miriamalvis10@gmail.com', '2018-02-10');
INSERT INTO clientes(region_id, nombre, apellido, email, create_at) VALUES(5, 'Douglas', 'Marrero', 'marrerodouglas@gmail.com', '2018-07-25');
INSERT INTO clientes(region_id, nombre, apellido, email, create_at) VALUES(8, 'Julio', 'Marrero', 'juliomarrero@gmail.com', '2018-11-07');
INSERT INTO clientes(region_id, nombre, apellido, email, create_at) VALUES(6, 'Yerkon', 'Ochoa', 'yerkonochoa@gmail.com', '2018-11-18');
INSERT INTO clientes(region_id, nombre, apellido, email, create_at) VALUES(5, 'Carlos', 'Estepa', 'carlosestepa@gmail.com', '2018-01-22');


/* Creamos algunos usuarios con sus roles */
INSERT INTO usuarios(username, password, enabled, nombre, apellido, email) VALUES('jeison', '$2a$10$mELYrgNsm.C4MDdIo0SHL.LNm5IZ0YZIh8QXcNacJjY7MvXn5YHZi', 1, 'Jeison', 'Pimentel', 'jeisonalvis10@gmail.com');
INSERT INTO usuarios(username, password, enabled, nombre, apellido, email) VALUES('admin', '$2a$10$qeV38x4EOkEZFv/hExCpsuU8T.Yvw/mYMHrZpnGWCTciDpVTTaEqG', 1, 'Carlos', 'Estepa', 'carlosestepa@gmail.com');

INSERT INTO roles(nombre) VALUES('ROLE_USER');
INSERT INTO roles(nombre) VALUES('ROLE_ADMIN');

INSERT INTO usuario_roles(usuario_id, role_id) VALUES(1, 1);
INSERT INTO usuario_roles(usuario_id, role_id) VALUES(2, 2);
INSERT INTO usuario_roles(usuario_id, role_id) VALUES(2, 1);


