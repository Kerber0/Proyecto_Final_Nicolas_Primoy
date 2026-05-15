package com.proyecto.comentarios.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ComentarioResponseDTO {

    private String nombreHotel;

    private Integer reservaId;

    private Double puntuacion;

    private String comentario;
}