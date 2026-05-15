package com.proyecto.comentarios.repository;

import com.proyecto.comentarios.entity.Comentario;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ComentarioRepository
        extends MongoRepository<Comentario, String> {

    List<Comentario> findByHotelId(Integer hotelId);

    List<Comentario> findByUsuarioId(Integer usuarioId);

    List<Comentario> findByReservaId(Integer reservaId);

    Optional<Comentario> findByUsuarioIdAndHotelIdAndReservaId(
            Integer usuarioId,
            Integer hotelId,
            Integer reservaId
    );
}