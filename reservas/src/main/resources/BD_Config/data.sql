INSERT INTO hotel (hotel_id, nombre, direccion)
SELECT 1, 'Hotel A', 'Calle Principal 123'
WHERE NOT EXISTS (
    SELECT 1
    FROM hotel
    WHERE hotel_id = 1
);

INSERT INTO hotel (hotel_id, nombre, direccion)
SELECT 2, 'Hotel B', 'Avenida Secundaria 456'
WHERE NOT EXISTS (
    SELECT 1
    FROM hotel
    WHERE hotel_id = 2
);

INSERT INTO hotel (hotel_id, nombre, direccion)
SELECT 3, 'Hotel C', 'Plaza Central 789'
WHERE NOT EXISTS (
    SELECT 1
    FROM hotel
    WHERE hotel_id = 3
);

INSERT INTO habitacion (habitacion_id, hotel_id, numero_habitacion, tipo, precio, disponible)
SELECT 1, 1, 101, 'Individual', 75.00, TRUE
WHERE NOT EXISTS (
    SELECT 1
    FROM habitacion
    WHERE habitacion_id = 1
);

INSERT INTO habitacion (habitacion_id, hotel_id, numero_habitacion, tipo, precio, disponible)
SELECT 2, 1, 102, 'Doble', 120.00, TRUE
WHERE NOT EXISTS (
    SELECT 1
    FROM habitacion
    WHERE habitacion_id = 2
);

INSERT INTO habitacion (habitacion_id, hotel_id, numero_habitacion, tipo, precio, disponible)
SELECT 3, 2, 103, 'Suite', 200.00, FALSE
WHERE NOT EXISTS (
    SELECT 1
    FROM habitacion
    WHERE habitacion_id = 3
);

INSERT INTO habitacion (habitacion_id, hotel_id, numero_habitacion, tipo, precio, disponible)
SELECT 4, 3, 104, 'Individual', 80.00, TRUE
WHERE NOT EXISTS (
    SELECT 1
    FROM habitacion
    WHERE habitacion_id = 4
);

INSERT INTO habitacion (habitacion_id, hotel_id, numero_habitacion, tipo, precio, disponible)
SELECT 5, 3, 105, 'Doble', 130.00, TRUE
WHERE NOT EXISTS (
    SELECT 1
    FROM habitacion
    WHERE habitacion_id = 5
);

INSERT INTO reserva (usuario_id, habitacion_id, fecha_inicio, fecha_fin, estado)
SELECT 1, 1, '2024-02-15', '2024-02-20', 'Confirmada'
WHERE NOT EXISTS (
    SELECT 1
    FROM reserva
    WHERE usuario_id = 1
      AND habitacion_id = 1
      AND fecha_inicio = '2024-02-15'
      AND fecha_fin = '2024-02-20'
      AND estado = 'Confirmada'
);

INSERT INTO reserva (usuario_id, habitacion_id, fecha_inicio, fecha_fin, estado)
SELECT 2, 2, '2024-03-10', '2024-03-15', 'Pendiente'
WHERE NOT EXISTS (
    SELECT 1
    FROM reserva
    WHERE usuario_id = 2
      AND habitacion_id = 2
      AND fecha_inicio = '2024-03-10'
      AND fecha_fin = '2024-03-15'
      AND estado = 'Pendiente'
);

INSERT INTO reserva (usuario_id, habitacion_id, fecha_inicio, fecha_fin, estado)
SELECT 3, 3, '2024-04-01', '2024-04-05', 'Cancelada'
WHERE NOT EXISTS (
    SELECT 1
    FROM reserva
    WHERE usuario_id = 3
      AND habitacion_id = 3
      AND fecha_inicio = '2024-04-01'
      AND fecha_fin = '2024-04-05'
      AND estado = 'Cancelada'
);

INSERT INTO reserva (usuario_id, habitacion_id, fecha_inicio, fecha_fin, estado)
SELECT 1, 4, '2024-05-15', '2024-05-20', 'Pendiente'
WHERE NOT EXISTS (
    SELECT 1
    FROM reserva
    WHERE usuario_id = 1
      AND habitacion_id = 4
      AND fecha_inicio = '2024-05-15'
      AND fecha_fin = '2024-05-20'
      AND estado = 'Pendiente'
);

INSERT INTO reserva (usuario_id, habitacion_id, fecha_inicio, fecha_fin, estado)
SELECT 2, 5, '2024-06-01', '2024-06-05', 'Confirmada'
WHERE NOT EXISTS (
    SELECT 1
    FROM reserva
    WHERE usuario_id = 2
      AND habitacion_id = 5
      AND fecha_inicio = '2024-06-01'
      AND fecha_fin = '2024-06-05'
      AND estado = 'Confirmada'
);
