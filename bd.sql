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
