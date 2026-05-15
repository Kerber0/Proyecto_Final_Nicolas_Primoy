package com.proyecto.reservas.controller;

import com.proyecto.reservas.dto.HabitacionRequestDTO;
import com.proyecto.reservas.service.HabitacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/reservas/habitacion")
public class HabitacionController {

    @Autowired
    private HabitacionService habitacionService;

    @PostMapping
    public String crearHabitacion(@RequestBody HabitacionRequestDTO  habitacion) {
        return habitacionService.crearHabitacion(habitacion);
    }

    @PatchMapping
    public String actualizarHabitacion(@RequestBody HabitacionRequestDTO  habitacion) {
        return habitacionService.actualizarHabitacion(habitacion);
    }

    @DeleteMapping("/{id}")
    public String eliminarHabitacion(@PathVariable Integer id) {
        return habitacionService.eliminarHabitacion(id);
    }

}