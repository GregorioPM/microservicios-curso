INSERT INTO `usuarios` (username, password, enabled, nombre, apellido, email) VALUES ('andres','$2a$10$EfRd1Yrowm.FgB/vwRDGmuFL5SfDkmVpsfQqZpTViwxKrWkp5UzrK',1, 'Andres', 'Guzman','profesor@bolsadeideas.com');
INSERT INTO `usuarios` (username, password, enabled, nombre, apellido, email) VALUES ('admin','$2a$10$cl88ZP5ecimaSkAq3y07IOrnya9G5QQ7x7qCTL2sehtCP2UfoWPaO',1, 'John', 'Doe','jhon.doe@bolsadeideas.com');

INSERT INTO `roles` (nombre) VALUES ('ROLE_USER');
INSERT INTO `roles` (nombre) VALUES ('ROLE_ADMIN');

INSERT INTO `usuarios_roles` (usuario_id, role_id) VALUES (1, 1);
INSERT INTO `usuarios_roles` (usuario_id, role_id) VALUES (2, 2);
INSERT INTO `usuarios_roles` (usuario_id, role_id) VALUES (2, 1);