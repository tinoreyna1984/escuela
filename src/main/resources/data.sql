insert into rol (nombre_rol) values ("USER");
insert into rol (nombre_rol) values ("ADMIN");

insert into usuario (nombre, apellido, doc_identidad, fecha_nacimiento, username, password, email, rol_id) values ('Tino', 'Reyna', '0933084527', '1984-05-05', 'tinoreyna1984', 'Tino20010878', 'tinoreyna1984@gmail.com', 2);
insert into usuario (nombre, apellido, doc_identidad, fecha_nacimiento, username, password, email, rol_id) values ('Gabriela', 'Lorentzen', '0911559977', '1990-10-17', 'gabylorentzen', 'Tino20010878', 'gabylorentzen@mail.com', 1);
insert into usuario (nombre, apellido, doc_identidad, fecha_nacimiento, username, password, email, rol_id) values ('Pepe', 'Trueno', '0911223344', '1984-06-10', 'ptrueno', 'ptrueno', 'ptrueno@mail.com', 1);
insert into usuario (nombre, apellido, doc_identidad, fecha_nacimiento, username, password, email, rol_id) values ('Armando', 'Paredes', '0922113344', '1980-07-11', 'aparedes', 'aparedes', 'aparedes@mail.com', 1);
insert into usuario (nombre, apellido, doc_identidad, fecha_nacimiento, username, password, email, rol_id) values ('Luis', 'Ramirez', '0911224433', '1977-11-23', 'luisramirez', 'luisramirez', 'luisramirez@mail.com', 1);
insert into usuario (nombre, apellido, doc_identidad, fecha_nacimiento, username, password, email, rol_id) values ('Juan', 'Tenorio', '0911332244', '1998-05-27', 'jtenorio', 'jtenorio', 'jtenorio@mail.com', 1);
insert into usuario (nombre, apellido, doc_identidad, fecha_nacimiento, username, password, email, rol_id) values ('Sergio', 'Pozo', '0933221144', '2001-09-14', 'sergiopozo', 'sergiopozo', 'sergiopozo@mail.com', 1);
insert into usuario (nombre, apellido, doc_identidad, fecha_nacimiento, username, password, email, rol_id) values ('Carlos', 'Vela', '0911442233', '2001-03-18', 'carlosvela', 'carlosvela', 'carlosvela@mail.com', 1);
insert into usuario (nombre, apellido, doc_identidad, fecha_nacimiento, username, password, email, rol_id) values ('Peter', 'Pan', '0910203040', '2002-04-21', 'peterpan', 'peterpan', 'peterpan@mail.com', 1);

insert into estudiante(usuario_id) values (2);
insert into estudiante(usuario_id) values (3);
insert into estudiante(usuario_id) values (4);
insert into estudiante(usuario_id) values (5);
insert into estudiante(usuario_id) values (6);

insert into profesor(usuario_id) values (7);
insert into profesor(usuario_id) values (8);
insert into profesor(usuario_id) values (9);

insert into curso(nombre_curso, horas_teoria, horas_practica) values ("Programacion basica", 3, 2);
insert into curso(nombre_curso, horas_teoria, horas_practica) values ("HTML + CSS", 3, 2);
insert into curso(nombre_curso, horas_teoria, horas_practica) values ("JavaScript + Node.js", 3, 2);
insert into curso(nombre_curso, horas_teoria, horas_practica) values ("React + Next.js", 3, 2);
insert into curso(nombre_curso, horas_teoria, horas_practica) values ("Angular", 3, 2);
insert into curso(nombre_curso, horas_teoria, horas_practica) values ("PHP + Laravel", 3, 2);

insert into estudiante_curso (curso_id, estudiante_id) values (1, 1);
insert into estudiante_curso (curso_id, estudiante_id) values (1, 2);
insert into estudiante_curso (curso_id, estudiante_id) values (1, 3);
insert into estudiante_curso (curso_id, estudiante_id) values (1, 4);
insert into estudiante_curso (curso_id, estudiante_id) values (1, 5);
insert into estudiante_curso (curso_id, estudiante_id) values (2, 1);
insert into estudiante_curso (curso_id, estudiante_id) values (2, 2);
insert into estudiante_curso (curso_id, estudiante_id) values (2, 3);
insert into estudiante_curso (curso_id, estudiante_id) values (3, 1);
insert into estudiante_curso (curso_id, estudiante_id) values (3, 2);

insert into horario (curso_id, profesor_id) values (1,1);
insert into horario (curso_id, profesor_id) values (1,2);
insert into horario (curso_id, profesor_id) values (1,3);
insert into horario (curso_id, profesor_id) values (2,1);
insert into horario (curso_id, profesor_id) values (2,2);
insert into horario (curso_id, profesor_id) values (3,1);