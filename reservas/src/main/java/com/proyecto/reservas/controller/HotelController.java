package com.proyecto.reservas.controller;

import com.proyecto.reservas.dto.HotelRequestDTO;
import com.proyecto.reservas.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/reservas/hotel")
public class HotelController {

    @Autowired
    public HotelService hotelService;

    @PostMapping
    public String crearHotel(@RequestBody HotelRequestDTO hotel){
        return hotelService.crearHotel(hotel);
    }

    @PatchMapping
    public String actualizarHotel(@RequestBody HotelRequestDTO hotel){
        return hotelService.actualizarHotel(hotel);
    }

    @DeleteMapping("/{id}")
    public String eliminarHotel(@PathVariable Integer id){
        return hotelService.eliminarHotel(id);
    }

    @PostMapping("/id/{nombre}")
    public String obtenerIdApartirNombre(@PathVariable String nombre){
        return hotelService.obtenerIdApartirNombre(nombre);
    }

    @PostMapping("/nombre/{id}")
    public String obtenerNombreAPartirId(@PathVariable Integer id){
        return hotelService.obtenerNombreAPartirId(id);
    }



}
