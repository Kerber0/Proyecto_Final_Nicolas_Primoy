CREATE TABLE IF NOT EXISTS hotel (
    hotel_id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100),
    direccion VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS habitacion (
    habitacion_id INT AUTO_INCREMENT PRIMARY KEY,
    hotel_id INT,
    numero_habitacion INT,
    tipo VARCHAR(50),
    precio DECIMAL(10, 2),
    disponible BOOLEAN,
    CONSTRAINT fk_habitacion_hotel
        FOREIGN KEY (hotel_id) REFERENCES hotel (hotel_id)
);

CREATE TABLE IF NOT EXISTS reserva (
    reserva_id INT AUTO_INCREMENT PRIMARY KEY,
    usuario_id INT,
    habitacion_id INT,
    fecha_inicio DATE,
    fecha_fin DATE,
    estado VARCHAR(20),
    CONSTRAINT fk_reserva_habitacion
        FOREIGN KEY (habitacion_id) REFERENCES habitacion (habitacion_id)
);
