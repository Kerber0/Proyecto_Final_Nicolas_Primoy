package com.proyecto.reservas.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReservaRequestDTO {
    private String nombre;
    private String contrasena;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private Integer habitacionId;

}
