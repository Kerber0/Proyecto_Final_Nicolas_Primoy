package com.proyecto.reservas.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReservaResponseDTO {
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private Integer habitacionId;

}