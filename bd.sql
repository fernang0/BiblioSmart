CREATE DATABASE IF NOT EXISTS biblioteca_mvp;
USE biblioteca_mvp;

CREATE TABLE usuario (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    rut VARCHAR(20),
    nombre_completo VARCHAR(100),
    correo VARCHAR(100),
    contrasena VARCHAR(255),
    tipo_usuario ENUM('ALUMNO', 'PROFESOR', 'ADMINISTRADOR'),
    rol ENUM('LECTOR', 'BIBLIOTECARIO', 'ADMINISTRADOR'),
    estado_cuenta VARCHAR(50),
    fecha_registro TIMESTAMP,
    deuda INT
);

CREATE TABLE libro (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    titulo VARCHAR(255) NOT NULL,
    autores VARCHAR(255),
    editorial VARCHAR(255),
    anio INT,
    descripcion TEXT,
    isbn VARCHAR(50) NOT NULL UNIQUE,
    categoria VARCHAR(100) NOT NULL,
    activo BOOLEAN DEFAULT TRUE,
    motivo_baja VARCHAR(255),
    fecha_baja DATE
);

-- Tabla POLITICA_PRESTAMO
CREATE TABLE politica_prestamo (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    dias_maximos INT NOT NULL,
    dias_tolerancia INT NOT NULL,
    dias_renovaciones INT NOT NULL,
    dias_por_dia INT NOT NULL
);

-- Tabla EJEMPLAR
CREATE TABLE ejemplar (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    codigo_ejemplar VARCHAR(50) NOT NULL UNIQUE,
    estado ENUM('DISPONIBLE','PRESTADO','RESERVADO','DANADO','EXTRAVIADO','BAJA') NOT NULL,
    ubicacion VARCHAR(255),
    libro_id BIGINT NOT NULL,
    FOREIGN KEY (libro_id) REFERENCES libro(id)
);

-- Tabla PRESTAMO
CREATE TABLE prestamo (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    ejemplar_id BIGINT NOT NULL,
    usuario_id BIGINT NOT NULL,
    fecha_prestamo DATE NOT NULL,
    fecha_limite DATE NOT NULL,
    fecha_devolucion DATE,
    dias_atraso INT,
    multa INT,
    politica_prestamo_id BIGINT NOT NULL,
    FOREIGN KEY (ejemplar_id) REFERENCES ejemplar(id),
    FOREIGN KEY (usuario_id) REFERENCES usuario(id),
    FOREIGN KEY (politica_prestamo_id) REFERENCES politica_prestamo(id)
);

-- Tabla RESERVA
CREATE TABLE reserva (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    ejemplar_id BIGINT NOT NULL,
    usuario_id BIGINT NOT NULL,
    fecha_reserva DATE NOT NULL,
    estado ENUM('ACTIVA','EXPIRADA','NOTIFICADA') NOT NULL,
    FOREIGN KEY (ejemplar_id) REFERENCES ejemplar(id),
    FOREIGN KEY (usuario_id) REFERENCES usuario(id)
);

-- Tabla NOTIFICACION
CREATE TABLE notificacion (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    destinatario_id BIGINT NOT NULL,
    mensaje TEXT NOT NULL,
    tipo ENUM('RECORDATORIO','ATRASO','DISPONIBILIDAD') NOT NULL,
    fecha_envio DATE NOT NULL,
    FOREIGN KEY (destinatario_id) REFERENCES usuario(id)
);

-- Tabla REPORTE
CREATE TABLE reporte (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    tipo ENUM('LIBROS_MAS_PRESTADOS','USUARIOS_ACTIVOS','USUARIOS_BLOQUEADOS','PRESTAMOS_EN_CURSO') NOT NULL,
    fecha_generacion DATE NOT NULL,
    generado_por_id BIGINT NOT NULL,
    FOREIGN KEY (generado_por_id) REFERENCES usuario(id)
);

--Poblado

-- Inserción de USUARIOS (contraseñas en texto plano)
INSERT INTO usuario (rut, nombre_completo, correo, contrasena, tipo_usuario, rol, estado_cuenta, fecha_registro, deuda)
VALUES 
('12345678-9', 'Juan Pérez', 'juan@example.com', 'juan123', 'ALUMNO', 'LECTOR', 'ACTIVO', NOW(), 0),
('98765432-1', 'Ana Torres', 'ana@example.com', 'ana456', 'PROFESOR', 'BIBLIOTECARIO', 'ACTIVO', NOW(), 500),
('19283746-5', 'Carlos Soto', 'carlos@example.com', 'admin789', 'ADMINISTRADOR', 'ADMINISTRADOR', 'ACTIVO', NOW(), 0);

-- Inserción de POLÍTICAS DE PRÉSTAMO
INSERT INTO politica_prestamo (dias_maximos, dias_tolerancia, dias_renovaciones, dias_por_dia)
VALUES 
(14, 3, 2, 7),
(7, 2, 1, 4);

-- Inserción de EJEMPLARES
INSERT INTO ejemplar (codigo_ejemplar, estado, ubicacion, libro_id)
VALUES 
('EJEMP001', 'DISPONIBLE', 'Estante A1', 1),
('EJEMP002', 'PRESTADO', 'Estante B2', 1),
('EJEMP003', 'RESERVADO', 'Estante C3', 2);

-- Inserción de PRÉSTAMOS
INSERT INTO prestamo (ejemplar_id, usuario_id, fecha_prestamo, fecha_limite, fecha_devolucion, dias_atraso, multa, politica_prestamo_id)
VALUES 
(2, 1, '2025-06-01', '2025-06-15', '2025-06-17', 2, 1000, 1);

-- Inserción de RESERVAS
INSERT INTO reserva (ejemplar_id, usuario_id, fecha_reserva, estado)
VALUES 
(3, 1, '2025-06-20', 'ACTIVA');

-- Inserción de NOTIFICACIONES
INSERT INTO notificacion (destinatario_id, mensaje, tipo, fecha_envio)
VALUES 
(1, 'Tu reserva sigue activa. Retira el ejemplar en 24 horas.', 'RECORDATORIO', '2025-06-21'),
(2, 'Tienes un atraso en la devolución del ejemplar.', 'ATRASO', '2025-06-18');

-- Inserción de REPORTES
INSERT INTO reporte (tipo, fecha_generacion, generado_por_id)
VALUES 
('LIBROS_MAS_PRESTADOS', '2025-06-22', 3),
('USUARIOS_BLOQUEADOS', '2025-06-22', 3);


--Poblado completo de libros

INSERT INTO libro (titulo, autores, editorial, anio, descripcion, isbn, categoria, activo, motivo_baja, fecha_baja) VALUES
('Cien Años de Soledad', 'Gabriel García Márquez', 'Sudamericana', 1967,
 'Una obra maestra del realismo mágico que narra la historia multigeneracional de la familia Buendía en el pueblo ficticio de Macondo. La novela explora temas como la soledad, el destino y el tiempo, entrelazando lo fantástico y lo real de manera magistral.', 
 '978-84-376-0494-7', 'Ficción', TRUE, NULL, NULL),

('1984', 'George Orwell', 'Secker & Warburg', 1949,
 'Una novela distópica que describe un mundo totalitario y opresivo bajo la vigilancia constante del Gran Hermano. Es una crítica poderosa a los regímenes autoritarios y a la manipulación de la verdad.', 
 '978-0-452-28423-4', 'Ficción', TRUE, NULL, NULL),

('Rayuela', 'Julio Cortázar', 'Editorial Sudamericana', 1963,
 'Una novela innovadora que rompe con la narrativa tradicional y permite múltiples lecturas. La historia sigue a Horacio Oliveira en su búsqueda existencial a través de París y Buenos Aires, explorando la identidad y el absurdo de la vida.', 
 '978-84-376-0495-4', 'Ficción', TRUE, NULL, NULL),

('Don Quijote de la Mancha', 'Miguel de Cervantes', 'Francisco de Robles', 1605,
 'Considerada la primera novela moderna, narra las aventuras del ingenioso hidalgo Don Quijote y su fiel escudero Sancho Panza. Es una sátira de los libros de caballería y una profunda reflexión sobre la locura y la realidad.', 
 '978-84-376-0496-1', 'Clásicos', TRUE, NULL, NULL),

('El Principito', 'Antoine de Saint-Exupéry', 'Reynal & Hitchcock', 1943,
 'Una fábula poética y filosófica sobre un niño que viaja de planeta en planeta, encontrando personajes que representan distintas facetas humanas. Aborda temas universales como la amistad, el amor y la inocencia.', 
 '978-0-15-601219-5', 'Infantil', TRUE, NULL, NULL),

('Crimen y Castigo', 'Fiódor Dostoievski', 'The Russian Messenger', 1866,
 'Una novela psicológica que explora la mente atormentada de Raskólnikov, un joven que comete un asesinato y lucha con la culpa y la redención. Aborda temas como la moralidad, el sufrimiento y la justicia.', 
 '978-0-14-044913-6', 'Ficción', TRUE, NULL, NULL),

('Orgullo y Prejuicio', 'Jane Austen', 'T. Egerton', 1813,
 'Una comedia romántica que explora las costumbres sociales y las relaciones personales en la Inglaterra georgiana. Se centra en Elizabeth Bennet y su relación con el señor Darcy, analizando prejuicios y orgullo.', 
 '978-0-19-283355-4', 'Romántico', TRUE, NULL, NULL),

('Fahrenheit 451', 'Ray Bradbury', 'Ballantine Books', 1953,
 'Una novela distópica que presenta una sociedad donde los libros están prohibidos y los "bomberos" queman cualquier libro encontrado. Reflexiona sobre la censura, la ignorancia y el poder del conocimiento.', 
 '978-0-7432-4722-1', 'Ficción', TRUE, NULL, NULL),

('La Sombra del Viento', 'Carlos Ruiz Zafón', 'Planeta', 2001,
 'Una novela de misterio ambientada en la Barcelona de la posguerra que mezcla intriga, amor y literatura. La historia sigue a Daniel Sempere en la búsqueda de la verdad detrás de un libro maldito.', 
 '978-84-080-7905-7', 'Misterio', TRUE, NULL, NULL),

('El Código Da Vinci', 'Dan Brown', 'Doubleday', 2003,
 'Un thriller que combina arte, historia y religión, donde el profesor Robert Langdon investiga una conspiración que puede cambiar el curso de la historia. Ritmo trepidante y acertijos en cada página.', 
 '978-0-385-50420-8', 'Thriller', TRUE, NULL, NULL);







INSERT INTO libro (titulo, autores, editorial, anio, descripcion, isbn, categoria, activo, motivo_baja, fecha_baja) VALUES
-- 1
('El amor en los tiempos del cólera', 'Gabriel García Márquez', 'Alfaguara', 1985,
 'Una historia de amor apasionado y duradero en la costa caribeña, que desafía el paso del tiempo.',
 '978-84-204-5284-7', 'Ficción', TRUE, NULL, NULL),
-- 2
('La casa de los espíritus', 'Isabel Allende', 'Plaza & Janés', 1982,
 'Crónica familiar que mezcla lo mágico, lo histórico y lo político en Chile.',
 '978-84-01-33434-7', 'Ficción', TRUE, NULL, NULL),
-- 3
('Pedro Páramo', 'Juan Rulfo', 'Editorial Jus', 1955,
 'Viaje al pueblo de Comala y encuentro con los muertos que habitan en él.',
 '978-84-206-0934-3', 'Ficción', TRUE, NULL, NULL),
-- 4
('El retrato de Dorian Gray', 'Oscar Wilde', 'Lippincott', 1890,
 'Un joven eternamente bello, un pacto oscuro y consecuencias fatales.',
 '978-84-376-0497-8', 'Clásicos', TRUE, NULL, NULL),
-- 5
('Matar a un ruiseñor', 'Harper Lee', 'J. B. Lippincott & Co.', 1960,
 'Historia sobre la injusticia racial asistida por la mirada infantil.',
 '978-0-06-112008-4', 'Ficción', TRUE, NULL, NULL),
-- 6
('El señor de los anillos', 'J.R.R. Tolkien', 'Allen & Unwin', 1954,
 'Épica fantástica sobre la comunidad del anillo y la lucha contra Sauron.',
 '978-0-261-10237-0', 'Fantasía', TRUE, NULL, NULL),
-- 7
('Harry Potter y la piedra filosofal', 'J.K. Rowling', 'Bloomsbury', 1997,
 'Un niño descubre que es mago y asiste a la escuela Hogwarts.',
 '978-0-7475-3269-9', 'Fantasía', TRUE, NULL, NULL),
-- 8
('La Odisea', 'Homero', 'Anónimo (traducción)', -800,
 'El nostos de Ulises enfrentando monstruos, dioses y el mar para volver a Ítaca.',
 '978-84-317-1026-7', 'Clásicos', TRUE, NULL, NULL),
-- 9
('La Iliada', 'Homero', 'Anónimo (traducción)', -750,
 'Relato de la guerra de Troya centrado en Aquiles y el conflicto con Héctor.',
 '978-84-317-1025-0', 'Clásicos', TRUE, NULL, NULL),
-- 10
('El Principito', 'Antoine de Saint-Exupéry', 'Reynal & Hitchcock', 1943,
 'Fábula poética sobre un niño que recorre planetas. [Repetido en ejemplo]', 
 '978-84-08-07906-4', 'Infantil', TRUE, NULL, NULL),
-- 11
('Viaje al centro de la Tierra', 'Jules Verne', 'Pierre-Jules Hetzel', 1864,
 'Aventura científica hacia lo desconocido bajo la superficie terrestre.',
 '978-84-8164-918-9', 'Ciencia ficción', TRUE, NULL, NULL),
-- 12
('Veinte mil leguas de viaje submarino', 'Jules Verne', 'Pierre-Jules Hetzel', 1870,
 'El Capitán Nemo y su submarino Nautilus recorren los océanos.',
 '978-84-8164-919-6', 'Ciencia ficción', TRUE, NULL, NULL),
-- 13
('Un mundo feliz', 'Aldous Huxley', 'Chatto & Windus', 1932,
 'Distopía sobre una sociedad controlada por bioingeniería y consumo.',
 '978-0-06-085052-4', 'Ficción', TRUE, NULL, NULL),
-- 14
('El guardián entre el centeno', 'J.D. Salinger', 'Little, Brown and Company', 1951,
 'El viaje personal de Holden Caulfield por Nueva York y su crisis adolescente.',
 '978-0-316-76948-0', 'Ficción', TRUE, NULL, NULL),
-- 15
('Las mil y una noches', 'Varios autores', 'editorial anónima', 1706,
 'Colección de cuentos fantásticos contados por Sherezade al sultán.',
 '978-84-207-5053-2', 'Ficción', TRUE, NULL, NULL),
-- 16
('La divina comedia', 'Dante Alighieri', 'Johannes Numeister', 1321,
 'Relato épico de viaje al Infierno, Purgatorio y Paraíso.',
 '978-84-206-8403-8', 'Clásicos', TRUE, NULL, NULL),
-- 17
('Madame Bovary', 'Gustave Flaubert', 'Revue de Paris', 1856,
 'Retrato trágico de una mujer insatisfecha con su vida provinciana.',
 '978-84-206-8396-3', 'Clásicos', TRUE, NULL, NULL),
-- 18
('Moby Dick', 'Herman Melville', 'Harper & Brothers', 1851,
 'La obsesión del Capitán Ahab por la gran ballena blanca.',
 '978-0-14-243724-7', 'Clásicos', TRUE, NULL, NULL),
-- 19
('Hamlet', 'William Shakespeare', 'N/A', 1603,
 'Tragedia acerca del príncipe que duda y busca vengar a su padre.',
 '978-84-376-0498-5', 'Clásicos', TRUE, NULL, NULL),
-- 20
('Macbeth', 'William Shakespeare', 'N/A', 1606,
 'Tragedia sobre la ambición y el poder que corrompen.',
 '978-84-376-0499-2', 'Clásicos', TRUE, NULL, NULL),
-- 23
('Crimen y castigo', 'Fiódor Dostoievski', 'The Russian Messenger', 1866,
 'Raskólnikov asesina a una usurera y enfrenta las consecuencias morales.',
 '978-84-206-7701-6', 'Clásicos', TRUE, NULL, NULL),
-- 24
('Anna Karénina', 'León Tolstói', 'The Russian Messenger', 1877,
 'Drama romántico y social sobre una mujer atrapada entre la pasión y la moral.',
 '978-84-206-7715-3', 'Clásicos', TRUE, NULL, NULL),
-- 25
('Guerra y paz', 'León Tolstói', 'The Russian Messenger', 1869,
 'Épica sobre la invasión napoleónica a Rusia y la aristocracia rusa.',
 '978-84-206-7683-5', 'Clásicos', TRUE, NULL, NULL),
-- 26
('1984', 'George Orwell', 'Secker & Warburg', 1949,
 'Distopía sobre vigilancia estatal, control mental y manipulación de la verdad.',
 '978-84-376-0495-5', 'Ficción', TRUE, NULL, NULL),
-- 27
('Rebelión en la granja', 'George Orwell', 'Secker & Warburg', 1945,
 'Fábula política sobre una granja gobernada por animales revolucionarios.',
 '978-84-206-8210-2', 'Ficción', TRUE, NULL, NULL),
-- 28
('Rayuela', 'Julio Cortázar', 'Editorial Sudamericana', 1963,
 'Novela experimental con múltiples formas de lectura sobre la vida y el arte.',
 '978-84-376-0496-0', 'Ficción', TRUE, NULL, NULL),
-- 29
('Cien años de soledad', 'Gabriel García Márquez', 'Sudamericana', 1967,
 'Saga de la familia Buendía en el mítico pueblo de Macondo.',
 '978-84-376-0494-1', 'Ficción', TRUE, NULL, NULL),
-- 30
('Fahrenheit 451', 'Ray Bradbury', 'Ballantine Books', 1953,
 'Distopía donde los libros están prohibidos y se queman para eliminar el pensamiento crítico.',
 '978-84-233-5842-3', 'Ciencia ficción', TRUE, NULL, NULL),
-- 31
('El nombre de la rosa', 'Umberto Eco', 'Bompiani', 1980,
 'Misterio medieval en una abadía benedictina con tintes filosóficos.',
 '978-84-663-0223-6', 'Ficción', TRUE, NULL, NULL),
-- 32
('Ensayo sobre la ceguera', 'José Saramago', 'Editorial Caminho', 1995,
 'Una epidemia de ceguera blanca azota una ciudad, revelando la condición humana.',
 '978-84-8310-101-2', 'Ficción', TRUE, NULL, NULL),
-- 33
('La tregua', 'Mario Benedetti', 'Editorial Arca', 1960,
 'Historia de un viudo rutinario que encuentra un último amor.',
 '978-84-8383-008-1', 'Ficción', TRUE, NULL, NULL),
-- 34
('Sobre héroes y tumbas', 'Ernesto Sabato', 'Losada', 1961,
 'Exploración de la locura, la identidad y la historia argentina.',
 '978-84-206-7706-1', 'Ficción', TRUE, NULL, NULL),
-- 35
('El túnel', 'Ernesto Sabato', 'Sudamericana', 1948,
 'Confesión de un pintor sobre el asesinato de la mujer que amaba.',
 '978-84-206-7707-8', 'Ficción', TRUE, NULL, NULL),
-- 36
('Don Quijote de la Mancha', 'Miguel de Cervantes', 'Francisco de Robles', 1605,
 'Caballero que confunde la realidad con su imaginación idealista.',
 '978-84-206-3451-4', 'Clásicos', TRUE, NULL, NULL),
-- 37
('El Aleph', 'Jorge Luis Borges', 'Editorial Losada', 1949,
 'Colección de cuentos filosóficos, metafísicos y sorprendentes.',
 '978-84-206-2624-3', 'Ficción', TRUE, NULL, NULL),
-- 38
('Ficciones', 'Jorge Luis Borges', 'Sur', 1944,
 'Obra clave con relatos que exploran los laberintos del tiempo, el infinito y la literatura.',
 '978-84-206-2625-0', 'Ficción', TRUE, NULL, NULL),
-- 39
('El extranjero', 'Albert Camus', 'Gallimard', 1942,
 'Un hombre indiferente a la vida y la muerte enfrenta el juicio moral de la sociedad.',
 '978-84-206-4544-2', 'Ficción', TRUE, NULL, NULL),
-- 40
('La peste', 'Albert Camus', 'Gallimard', 1947,
 'Crónica de una epidemia en Orán que revela lo mejor y peor de los humanos.',
 '978-84-206-7686-6', 'Ficción', TRUE, NULL, NULL),
-- 41
('Los miserables', 'Victor Hugo', 'A. Lacroix, Verboeckhoven & Cie.', 1862,
 'Redención, injusticia social y revolución en la Francia del siglo XIX.',
 '978-84-206-7670-5', 'Clásicos', TRUE, NULL, NULL),
-- 42
('Notre-Dame de París', 'Victor Hugo', 'Gosselin', 1831,
 'Historia trágica del jorobado Quasimodo y la gitana Esmeralda.',
 '978-84-206-7680-4', 'Clásicos', TRUE, NULL, NULL),
-- 43
('Los hermanos Karamázov', 'Fiódor Dostoievski', 'Russki Vestnik', 1880,
 'Relato filosófico sobre fe, duda, razón, moral y crimen.',
 '978-84-206-7681-1', 'Clásicos', TRUE, NULL, NULL),
-- 44
('El idiota', 'Fiódor Dostoievski', 'Russki Vestnik', 1869,
 'El príncipe Myshkin, símbolo de la bondad, en un mundo corrupto.',
 '978-84-206-7682-8', 'Clásicos', TRUE, NULL, NULL),
-- 45
('Ulises', 'James Joyce', 'Sylvia Beach', 1922,
 'Un día en la vida de Leopold Bloom en Dublín, narrado con complejidad innovadora.',
 '978-84-206-7687-3', 'Clásicos', TRUE, NULL, NULL),
-- 46
('Dublineses', 'James Joyce', 'Grant Richards Ltd.', 1914,
 'Cuentos breves sobre la parálisis emocional y social en Dublín.',
 '978-84-206-7688-0', 'Clásicos', TRUE, NULL, NULL),
-- 47
('Lolita', 'Vladimir Nabokov', 'Olympia Press', 1955,
 'Una controvertida historia de obsesión narrada por Humbert Humbert.',
 '978-84-206-7690-3', 'Ficción', TRUE, NULL, NULL),
-- 48
('Trópico de Cáncer', 'Henry Miller', 'Obelisk Press', 1934,
 'Autobiografía ficcional sobre la vida bohemia en París.',
 '978-84-206-7691-0', 'Ficción', TRUE, NULL, NULL),
-- 49
('La insoportable levedad del ser', 'Milan Kundera', 'Gallimard', 1984,
 'Relato filosófico sobre el amor y la identidad en el contexto del totalitarismo.',
 '978-84-206-7692-7', 'Ficción', TRUE, NULL, NULL),
-- 50
('El lobo estepario', 'Hermann Hesse', 'S. Fischer Verlag', 1927,
 'Novela introspectiva sobre la dualidad humana y la búsqueda espiritual.',
 '978-84-206-7693-4', 'Ficción', TRUE, NULL, NULL);

