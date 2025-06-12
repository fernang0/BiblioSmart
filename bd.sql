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

