/* tabla clientes */
INSERT INTO regiones (id, nombre) VALUES (1, 'Sudamérica');
INSERT INTO regiones (id, nombre) VALUES (2, 'Centroamérica');
INSERT INTO regiones (id, nombre) VALUES (3, 'Norteamérica');
INSERT INTO regiones (id, nombre) VALUES (4, 'Europa');
INSERT INTO regiones (id, nombre) VALUES (5, 'Asia');
INSERT INTO regiones (id, nombre) VALUES (6, 'Africa');
INSERT INTO regiones (id, nombre) VALUES (7, 'Oceanía');
INSERT INTO regiones (id, nombre) VALUES (8, 'Antártida');
INSERT INTO clientes (region_id, nombre, apellidos, email, create_at) values(1, "Adriel", "Aliaga Benavides", "a@a.aa", "2022-04-22");
INSERT INTO clientes (region_id, nombre, apellidos, email, create_at) values(2, "Alejandro", "Aliaga Benavides", "a@a.aa", "2022-04-22");
INSERT INTO clientes (region_id, nombre, apellidos, email, create_at) values(2, "Yudisel", "Santana Pacheco", "b@a.aa", "2022-04-22");
INSERT INTO clientes (region_id, nombre, apellidos, email, create_at) values(2, "Amelia", "Aliaga Santana", "b@a.aa", "2022-04-22");
INSERT INTO clientes (region_id, nombre, apellidos, email, create_at) values(3, "Lazara", "Aliaga Benavides", "a@a.aa", "2022-04-22");
INSERT INTO clientes (region_id, nombre, apellidos, email, create_at) values(1, "Segundo", "Aliaga Cespedes", "a@a.aa", "2022-04-22");
INSERT INTO clientes (region_id, nombre, apellidos, email, create_at) values(1, "Teresa", "Benavides Cutiño", "a@a.aa", "2022-04-22");
INSERT INTO clientes (region_id, nombre, apellidos, email, create_at) values(4, "Lilian", "Blanco Aliaga", "a@a.aa", "2022-04-22");
INSERT INTO clientes (region_id, nombre, apellidos, email, create_at) values(5, "Liliannis", "Blanco Aliaga", "a@a.aa", "2022-04-22");

/* Creamos algunos usuarios con sus roles */
INSERT INTO `usuarios` (username, password, enabled, nombre, apellidos, email) VALUES ('adriel','$2a$10$3CHelALs8bIcoXvL5MCnFubZ/uSjv/Kab06Olc4reVGEKgioyR4YO',1,'Adriel', 'Aliaga Benavides', 'a@a.aa');
INSERT INTO `usuarios` (username, password, enabled, nombre, apellidos, email) VALUES ('admin','$2a$10$h8jru8X4JhCsFkXG7t1Ifedtvo0ZL6Jb3jQJlQxWAwKKpZqijomTW',1,'Adriel Alejandro', 'Aliaga Benavides', 'a@a.aa');

INSERT INTO `roles` (nombre) VALUES ('ROLE_USER');
INSERT INTO `roles` (nombre) VALUES ('ROLE_ADMIN');

INSERT INTO `usuarios_roles` (usuario_id, roles_id) VALUES (1, 1);
INSERT INTO `usuarios_roles` (usuario_id, roles_id) VALUES (2, 2);
INSERT INTO `usuarios_roles` (usuario_id, roles_id) VALUES (2, 1);