package com.proyecto.reservas.service;

import com.proyecto.reservas.dto.HotelRequestDTO;
import com.proyecto.reservas.entity.Habitacion;
import com.proyecto.reservas.entity.Hotel;
import com.proyecto.reservas.repository.HabitacionRepository;
import com.proyecto.reservas.repository.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HotelService {
    @Autowired
    private HotelRepository hotelRepository;
    @Autowired
    private HabitacionRepository habitacionRepository;

    public String crearHotel(HotelRequestDTO hotel) {
        try {
            if(datosInvalidos(hotel)) {
                return "ERROR: TODOS LOS CAMPOS SON OBLIGATORIOS";
            }
            Hotel nuevoHotel = new Hotel();

            nuevoHotel.setNombre(hotel.getNombre());
            nuevoHotel.setDireccion(hotel.getDireccion());

            hotelRepository.save(nuevoHotel);
            return "HOTEL CREADO CON EXITO";

        }catch(Exception e){
            return "ERROR AL CREAR HOTEL";
        }
    }

    public String actualizarHotel(HotelRequestDTO hotel) {

        try {

            if (datosInvalidos(hotel)) {
                return "ERROR: TODOS LOS CAMPOS SON OBLIGATORIOS";
            }

            Optional<Hotel> hotelActual = hotelRepository.findById(hotel.getId());

            if (hotelActual.isEmpty()) {
                return "HOTEL NO ENCONTRADO";
            }

            Hotel actualizacionHotel = hotelActual.get();

            actualizacionHotel.setNombre(hotel.getNombre());
            actualizacionHotel.setDireccion(hotel.getDireccion());

            hotelRepository.save(actualizacionHotel);

            return "HOTEL ACTUALIZADO CON EXITO";

        } catch (Exception e) {

            return "ERROR AL ACTUALIZAR HOTEL";
        }
    }

    public String eliminarHotel(Integer id) {
        try {
            Optional<Hotel> hotelActual = hotelRepository.findById(id);

            if (hotelActual.isEmpty()) {
                return "HOTEL NO ENCONTRADO";
            }

            List<Habitacion> habitaciones = habitacionRepository.findAllByHotel_Id(id);

            habitacionRepository.deleteAll(habitaciones);

            hotelRepository.delete(hotelActual.get());

            return "HOTEL ELIMINADO CON EXITO";

        } catch (Exception e) {
            return "ERROR AL ELIMINAR HOTEL";
        }
    }


    public String obtenerIdApartirNombre(String nombre) {
        try {
            // <-- EXTRA: SE USA LISTA PARA PREVENIR MULTIPLES RESULTADOS
            List<Hotel> hotels = hotelRepository.findByNombre(nombre);

            if (hotels.isEmpty()) {
                return "HOTEL NO ENCONTRADO";
            }
            // <-- EXTRA: DEVUELVE EL PRIMERO
            return String.valueOf(hotels.getFirst().getId());

        }catch(Exception e){
            return "ERROR AL BUSCAR HOTEL";
        }
    }

    public String obtenerNombreAPartirId(Integer id) {
        try {
            Optional<Hotel> hotelActual = hotelRepository.findById(id);

            if (hotelActual.isEmpty()) {
                return "HOTEL NO ENCONTRADO";
            }

            return hotelActual.get().getNombre();

        }catch(Exception e){
            return "ERROR AL BUSCAR HOTEL";
        }
    }

    // <-- EXTRA: VALIDACION DATOS HOTEL
    private boolean datosInvalidos(HotelRequestDTO hotel) {
        return hotel == null
                || hotel.getNombre() == null  || hotel.getNombre().isEmpty()
                || hotel.getDireccion() == null  || hotel.getDireccion().isEmpty();
    }
}
