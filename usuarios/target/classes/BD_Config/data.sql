INSERT INTO usuario (nombre, correo_electronico, direccion, contrasena)
SELECT 'Juan Pérez', 'juan@example.com', 'Calle Principal 123', 'clave123'
WHERE NOT EXISTS (
    SELECT 1
    FROM usuario
    WHERE correo_electronico = 'juan@example.com'
);

INSERT INTO usuario (nombre, correo_electronico, direccion, contrasena)
SELECT 'María López', 'maria@example.com', 'Avenida Secundaria 456', 'secreto456'
WHERE NOT EXISTS (
    SELECT 1
    FROM usuario
    WHERE correo_electronico = 'maria@example.com'
);

INSERT INTO usuario (nombre, correo_electronico, direccion, contrasena)
SELECT 'Carlos Rodriguez', 'carlos@example.com', 'Plaza Central 789', 'password789'
WHERE NOT EXISTS (
    SELECT 1
    FROM usuario
    WHERE correo_electronico = 'carlos@example.com'
);

INSERT INTO usuario (nombre, correo_electronico, direccion, contrasena)
SELECT 'Ana Martínez', 'ana@example.com', 'Calle del Parque 987', 'abcxyz'
WHERE NOT EXISTS (
    SELECT 1
    FROM usuario
    WHERE correo_electronico = 'ana@example.com'
);
