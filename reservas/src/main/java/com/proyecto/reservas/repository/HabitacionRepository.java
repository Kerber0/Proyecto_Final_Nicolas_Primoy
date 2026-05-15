package com.proyecto.reservas.repository;
import java.util.List;
import com.proyecto.reservas.entity.Habitacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface HabitacionRepository extends JpaRepository<Habitacion, Integer> {


    List<Habitacion> findByDisponible(boolean disponible);

    Optional<Habitacion> findByNumeroHabitacion(int numeroHabitacion);

    List<Habitacion> findAllByHotel_Id(Integer hotelId);

}
