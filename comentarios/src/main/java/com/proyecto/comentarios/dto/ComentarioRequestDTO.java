package com.proyecto.comentarios.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ComentarioRequestDTO {

    private String nombre;

    private String contrasena;

    private String nombreHotel;

    private Integer reservaId;

    private Double puntuacion;

    private String comentario;
}