package com.proyecto.reservas.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "habitacion")
@Getter
@Setter
public class Habitacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "habitacion_id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "hotel_id")
    private Hotel hotel;

    @Column(name = "numero_habitacion")
    private Integer numeroHabitacion;

    @Column(name = "tipo", length = 50)
    private String tipo;

    @Column(name = "precio", precision = 10, scale = 2)
    private Double precio;

    @Column(name = "disponible")
    private Boolean disponible;
}