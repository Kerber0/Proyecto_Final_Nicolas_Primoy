package com.proyecto.reservas.service;

import com.proyecto.reservas.dto.HabitacionRequestDTO;
import com.proyecto.reservas.entity.Habitacion;
import com.proyecto.reservas.entity.Hotel;
import com.proyecto.reservas.repository.HabitacionRepository;
import com.proyecto.reservas.repository.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class HabitacionService {

    @Autowired
    private HabitacionRepository habitacionRepository;
    @Autowired
    private HotelRepository hotelRepository;


    public String crearHabitacion(HabitacionRequestDTO habitacion) {
        try {

            // <-- EXTRA: VALIDAR CAMPOS VACIOS O INVALIDOS
            if (datosInvalidos(habitacion)) {
                return "ERROR: TODOS LOS CAMPOS SON OBLIGATORIOS";
            }

            Optional<Hotel> hotelActual = hotelRepository.findById(habitacion.getHotelId());

            if (hotelActual.isEmpty()) {
                return "HOTEL NO ENCONTRADO";
            }

            Habitacion nuevaHabitacion = new Habitacion();

            nuevaHabitacion.setNumeroHabitacion(habitacion.getNumeroHabitacion());
            nuevaHabitacion.setTipo(habitacion.getTipo());
            nuevaHabitacion.setPrecio(habitacion.getPrecio());
            nuevaHabitacion.setDisponible(habitacion.isDisponible());
            nuevaHabitacion.setHotel(hotelActual.get());

            habitacionRepository.save(nuevaHabitacion);

            return "HABITACION CREADA CON EXITO";

        } catch (Exception e) {
            return "ERROR AL CREAR HABITACION";
        }
    }


    public String actualizarHabitacion(HabitacionRequestDTO habitacion) {

        try {

            // <-- EXTRA: VALIDAR CAMPOS VACIOS
            if (datosInvalidos(habitacion)) {
                return "ERROR: TODOS LOS CAMPOS SON OBLIGATORIOS";
            }

            Optional<Habitacion> habitacionActual =
                    habitacionRepository.findById(habitacion.getId());

            if (habitacionActual.isEmpty()) {
                return "HABITACION NO ENCONTRADA";
            }

            Optional<Hotel> hotelActual =
                    hotelRepository.findById(habitacion.getHotelId());

            if (hotelActual.isEmpty()) {
                return "HOTEL NO ENCONTRADO";
            }

            Habitacion actualizacionHabitacion = habitacionActual.get();

            actualizacionHabitacion.setNumeroHabitacion(
                    habitacion.getNumeroHabitacion()
            );

            actualizacionHabitacion.setTipo(habitacion.getTipo());

            actualizacionHabitacion.setPrecio(habitacion.getPrecio());

            actualizacionHabitacion.setDisponible(
                    habitacion.isDisponible()
            );

            actualizacionHabitacion.setHotel(hotelActual.get());

            habitacionRepository.save(actualizacionHabitacion);

            return "HABITACION ACTUALIZADA CON EXITO";

        } catch (Exception e) {

            return "ERROR AL ACTUALIZAR HABITACION";
        }
    }


    public String eliminarHabitacion(Integer id) {
        try {

            Optional<Habitacion> habitacionActual = habitacionRepository.findById(id);
            if (habitacionActual.isEmpty()) {
                return "HABITACION NO ENCONTRADA";
            }
            habitacionRepository.delete(habitacionActual.get());
            return "HABITACION ELIMINADA CON EXITO";

        }catch (Exception e) {
            return "ERROR AL ELIMINAR HABITACION";
        }

    }

    // <-- EXTRA: VALIDACION DATOS HABITACION
    private boolean datosInvalidos(HabitacionRequestDTO habitacion) {

        return habitacion == null

                || habitacion.getTipo() == null
                || habitacion.getTipo().isBlank()

                || habitacion.getHotelId() == null
                || habitacion.getHotelId() <= 0

                || habitacion.getNumeroHabitacion() == null
                || habitacion.getNumeroHabitacion() <= 0

                || habitacion.getPrecio() == null
                || habitacion.getPrecio() <= 0;
    }


}

