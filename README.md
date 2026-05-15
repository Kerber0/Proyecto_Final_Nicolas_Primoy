# Proyecto Final - Sistema de Reservas y Comentarios

Alumno: Nicolás Primoy

## Descripción general

Este proyecto implementa un sistema basado en microservicios para la gestión de usuarios, reservas de hoteles y comentarios sobre reservas.

El sistema está formado por cinco módulos principales:

- Servidor Eureka
- API Gateway
- Microservicio de Usuarios
- Microservicio de Reservas
- Microservicio de Comentarios

La comunicación entre servicios se realiza mediante Eureka y Gateway. Los servicios de usuarios y reservas implementan API REST, mientras que el servicio de comentarios implementa GraphQL.

## Módulos implementados

### 1. Eureka

Módulo encargado del registro y descubrimiento de microservicios.

- Nombre del servicio: eureka
- Puerto: 8500

### 2. Gateway

Módulo que actúa como punto de entrada al sistema.

- Nombre del servicio: gateway
- Puerto: 8080
- Permite acceder a los demás microservicios registrados mediante Eureka.

### 3. Usuarios

Microservicio encargado de la gestión de usuarios.

- Nombre del servicio: usuarios
- Puerto: 8502
- API REST
- Base de datos MySQL: usuariosProyecto
- Ruta raíz: /usuarios

Funcionalidades principales:

- Crear usuario
- Actualizar usuario
- Eliminar usuario
- Validar usuario
- Obtener nombre de usuario por ID
- Obtener ID de usuario por nombre
- Comprobar si existe un usuario

### 4. Reservas

Microservicio encargado de gestionar hoteles, habitaciones y reservas.

- Nombre del servicio: reservas
- Puerto: 8501
- API REST
- Base de datos MySQL: reservasProyecto
- Ruta raíz: /reservas

Funcionalidades principales:

- Crear, actualizar y eliminar hoteles
- Crear, actualizar y eliminar habitaciones
- Crear reservas
- Cambiar estado de una reserva
- Listar reservas de un usuario
- Listar reservas según estado
- Comprobar si una reserva pertenece a un usuario y hotel determinados

### 5. Comentarios

Microservicio encargado de gestionar comentarios sobre reservas.

- Nombre del servicio: comentarios
- Puerto: 8503
- API GraphQL
- GraphiQL habilitado
- Endpoint GraphQL: /comentarios
- Base de datos MongoDB: comentariosProyecto
- Colección: comentarios

Funcionalidades principales:

- Crear comentario
- Eliminar todos los comentarios
- Eliminar comentario de un usuario
- Listar comentarios de un hotel
- Listar comentarios de un usuario
- Mostrar comentario de un usuario en una reserva
- Calcular puntuación media de un hotel
- Calcular puntuación media de un usuario

## Bases de datos utilizadas

### MySQL

Se utilizan dos bases de datos MySQL:

- usuariosProyecto
- reservasProyecto

Los scripts de creación e inserción de datos se encuentran dentro de los recursos de cada microservicio.

### MongoDB

Se utiliza la base de datos:

- comentariosProyecto

Con la colección:

- comentarios

## Tecnologías utilizadas

- Java
- Spring Boot
- Spring Web
- Spring Data JPA
- Spring Data MongoDB
- Spring GraphQL
- Spring Cloud Gateway
- Eureka Server
- Eureka Client
- Maven
- MySQL
- MongoDB

## Orden recomendado de ejecución

1. Eureka
2. Usuarios
3. Reservas
4. Comentarios
5. Gateway

## Puertos

| Módulo | Puerto |
|---|---|
| Eureka | 8500 |
| Gateway | 8080 |
| Reservas | 8501 |
| Usuarios | 8502 |
| Comentarios | 8503 |

## Puntuación máxima a la que se opta

Se han implementado los cinco módulos solicitados en la especificación del proyecto, por lo que se opta a la puntuación máxima correspondiente al proyecto completo.

## Notas

El proyecto sigue una estructura basada en controlador, servicio y repositorio en los microservicios que lo requieren.

Los métodos han sido nombrados siguiendo las funcionalidades indicadas en la especificación.