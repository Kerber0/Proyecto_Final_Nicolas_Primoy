package com.proyecto.reservas.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class HabitacionRequestDTO {
        private Integer id;
        private Integer numeroHabitacion;
        private String tipo;
        private Double precio;
        private Integer hotelId;
        private boolean disponible;
}
