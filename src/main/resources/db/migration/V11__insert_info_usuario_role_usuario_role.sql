INSERT INTO usuario (nome, email, password) VALUES ('admin', 'admin@email.com', '$2a$12$KCNvhQUzL5h0Tv4LaG4MMeY43G4SaQcawQwBvY6kkR71epaBmsJqS');
INSERT INTO role (nome) VALUES ('ADMIN');
INSERT INTO usuario_role (usuario_id, role_id) VALUES (2, 2);