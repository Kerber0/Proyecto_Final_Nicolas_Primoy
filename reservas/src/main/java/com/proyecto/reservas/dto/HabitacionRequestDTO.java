package com.proyecto.reservas.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class HabitacionRequestDTO {
        private Integer id;
        private Integer numeroHabitacion;
        private String tipo;
        private BigDecimal precio;
        private Integer hotelId;
        private boolean disponible;
}
