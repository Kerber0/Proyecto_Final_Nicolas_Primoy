package com.proyecto.reservas.repository;

import com.proyecto.reservas.entity.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReservaRepository  extends JpaRepository<Reserva,Integer> {

    List<Reserva> findByUsuarioId(Integer id);

    List<Reserva> findByEstado(String estado);

    Optional<Reserva> findByIdAndUsuarioId(Integer id, Integer usuarioId);

    List<Reserva> findAllByHabitacion_Id(Integer habitacionId);

    boolean existsByUsuarioId(Integer usuarioId);

}
