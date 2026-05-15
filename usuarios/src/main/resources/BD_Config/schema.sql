CREATE TABLE IF NOT EXISTS usuario (
    usuario_id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100),
    correo_electronico VARCHAR(255),
    direccion VARCHAR(255),
    contrasena VARCHAR(255)
);
