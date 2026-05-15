package com.proyecto.comentarios.controller;

import com.proyecto.comentarios.dto.ComentarioRequestDTO;
import com.proyecto.comentarios.dto.ComentarioResponseDTO;
import com.proyecto.comentarios.dto.LoginRequestDTO;
import com.proyecto.comentarios.service.ComentarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class ComentarioController {

    @Autowired
    private ComentarioService comentarioService;

    @MutationMapping
    public ComentarioResponseDTO crearComentario(@Argument ComentarioRequestDTO request) {
        return comentarioService.crearComentario(request);
    }

    @MutationMapping
    public String eliminarComentarios() {
        return comentarioService.eliminarComentarios();
    }

    @MutationMapping
    public String eliminarComentarioDeUsuario(@Argument String comentarioId) {
        return comentarioService.eliminarComentarioDeUsuario(comentarioId);
    }

    @QueryMapping
    public List<ComentarioResponseDTO> listarComentariosHotel(@Argument String nombreHotel) {
        return comentarioService.listarComentariosHotel(nombreHotel);
    }

    @QueryMapping
    public List<ComentarioResponseDTO> listarComentariosUsuario(@Argument LoginRequestDTO login) {
        return comentarioService.listarComentariosUsuario(login);
    }

    @QueryMapping
    public List<ComentarioResponseDTO> mostrarComentarioUsuarioReserva(@Argument Integer reservaId) {
        return comentarioService.mostrarComentarioUsuarioReserva(reservaId);
    }

    @QueryMapping
    public Double puntuacionMediaHotel(@Argument String nombreHotel) {
        return comentarioService.puntuacionMediaHotel(nombreHotel);
    }

    @QueryMapping
    public Double puntuacionesMediasUsuario(@Argument LoginRequestDTO login) {
        return comentarioService.puntuacionesMediasUsuario(login);
    }
}
