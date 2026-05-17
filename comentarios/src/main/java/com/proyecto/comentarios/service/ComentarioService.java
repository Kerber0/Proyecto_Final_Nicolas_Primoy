package com.proyecto.comentarios.service;

import com.proyecto.comentarios.client.ReservaClient;
import com.proyecto.comentarios.client.UsuarioClient;
import com.proyecto.comentarios.dto.ComentarioRequestDTO;
import com.proyecto.comentarios.dto.ComentarioResponseDTO;
import com.proyecto.comentarios.dto.LoginRequestDTO;
import com.proyecto.comentarios.entity.Comentario;
import com.proyecto.comentarios.repository.ComentarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class ComentarioService {

    @Autowired
    private ComentarioRepository comentarioRepository;

    @Autowired
    private UsuarioClient usuarioClient;

    @Autowired
    private ReservaClient reservaClient;

    public ComentarioResponseDTO crearComentario(ComentarioRequestDTO request) {

        if (datosInvalidos(request)) {
            throw new IllegalArgumentException("ERROR: TODOS LOS CAMPOS SON OBLIGATORIOS");
        }

        LoginRequestDTO login =
                new LoginRequestDTO(
                        request.getNombre(),
                        request.getContrasena()
                );

        if (!usuarioClient.validarUsuario(login)) {
            throw new SecurityException("USUARIO NO VALIDO");
        }

        String usuarioIdString =
                usuarioClient.obtenerIdUsuarioPorNombre(request.getNombre());

        if (!esNumeroValido(usuarioIdString)) {
            throw new NoSuchElementException("USUARIO NO ENCONTRADO");
        }

        String hotelIdString =
                reservaClient.obtenerIdApartirNombre(request.getNombreHotel());

        if (!esNumeroValido(hotelIdString)) {
            throw new NoSuchElementException("HOTEL NO ENCONTRADO");
        }

        Integer usuarioId = Integer.parseInt(usuarioIdString);
        Integer hotelId = Integer.parseInt(hotelIdString);

        if (!reservaClient.checkReserva(
                usuarioId,
                hotelId,
                request.getReservaId()
        )) {
            throw new IllegalStateException("LA RESERVA NO ES VALIDA PARA ESE USUARIO Y HOTEL");
        }

        Optional<Comentario> comentarioExistente =
                comentarioRepository.findByUsuarioIdAndHotelIdAndReservaId(
                        usuarioId,
                        hotelId,
                        request.getReservaId()
                );

        if (comentarioExistente.isPresent()) {
            throw new IllegalStateException("YA EXISTE UN COMENTARIO PARA ESA RESERVA");
        }

        Comentario comentarioNuevo = new Comentario();

        comentarioNuevo.setUsuarioId(usuarioId);
        comentarioNuevo.setHotelId(hotelId);
        comentarioNuevo.setReservaId(request.getReservaId());
        comentarioNuevo.setPuntuacion(request.getPuntuacion());
        comentarioNuevo.setComentario(request.getComentario().trim());
        comentarioNuevo.setFechaCreacion(Instant.now());

        comentarioRepository.save(comentarioNuevo);

        return mapearComentario(comentarioNuevo);
    }

    public String eliminarComentarios() {
        comentarioRepository.deleteAll();
        return "COMENTARIOS ELIMINADOS CON EXITO";
    }

    public String eliminarComentarioDeUsuario(String comentarioId) {

        if (comentarioId == null || comentarioId.isBlank()) {
            return "COMENTARIO NO ENCONTRADO";
        }

        if (!comentarioRepository.existsById(comentarioId)) {
            return "COMENTARIO NO ENCONTRADO";
        }

        comentarioRepository.deleteById(comentarioId);

        return "COMENTARIO ELIMINADO CON EXITO";
    }

    public List<ComentarioResponseDTO> listarComentariosHotel(String nombreHotel) {

        if (nombreHotel == null || nombreHotel.isBlank()) {
            return new ArrayList<>();
        }

        String hotelIdString = reservaClient.obtenerIdApartirNombre(nombreHotel);

        if (!esNumeroValido(hotelIdString)) {
            return new ArrayList<>();
        }

        Integer hotelId = Integer.parseInt(hotelIdString);

        return mapearComentarios(comentarioRepository.findByHotelId(hotelId));
    }

    public List<ComentarioResponseDTO> listarComentariosUsuario(LoginRequestDTO login) {

        if (loginInvalido(login)) {
            return new ArrayList<>();
        }

        if (!usuarioClient.validarUsuario(login)) {
            return new ArrayList<>();
        }

        String usuarioIdString =
                usuarioClient.obtenerIdUsuarioPorNombre(login.getNombre());

        if (!esNumeroValido(usuarioIdString)) {
            return new ArrayList<>();
        }

        Integer usuarioId = Integer.parseInt(usuarioIdString);

        return mapearComentarios(comentarioRepository.findByUsuarioId(usuarioId));
    }

    public List<ComentarioResponseDTO> mostrarComentarioUsuarioReserva(Integer reservaId) {

        if (reservaId == null || reservaId <= 0) {
            return new ArrayList<>();
        }

        return mapearComentarios(comentarioRepository.findByReservaId(reservaId));
    }

    public Double puntuacionMediaHotel(String nombreHotel) {

        List<ComentarioResponseDTO> comentarios = listarComentariosHotel(nombreHotel);

        if (comentarios.isEmpty()) {
            return 0.0;
        }

        double suma = 0.0;

        for (ComentarioResponseDTO comentario : comentarios) {
            suma += comentario.getPuntuacion();
        }

        return suma / comentarios.size();
    }

    public Double puntuacionesMediasUsuario(LoginRequestDTO login) {

        List<ComentarioResponseDTO> comentarios = listarComentariosUsuario(login);

        if (comentarios.isEmpty()) {
            return 0.0;
        }

        double suma = 0.0;

        for (ComentarioResponseDTO comentario : comentarios) {
            suma += comentario.getPuntuacion();
        }

        return suma / comentarios.size();
    }

    private List<ComentarioResponseDTO> mapearComentarios(List<Comentario> comentarios) {

        List<ComentarioResponseDTO> response = new ArrayList<>();

        for (Comentario comentario : comentarios) {
            response.add(mapearComentario(comentario));
        }

        return response;
    }

    private ComentarioResponseDTO mapearComentario(Comentario comentario) {

        String nombreHotel =
                reservaClient.obtenerNombreAPartirId(comentario.getHotelId());

        return new ComentarioResponseDTO(
                nombreHotel,
                comentario.getReservaId(),
                comentario.getPuntuacion(),
                comentario.getComentario()
        );
    }

    private boolean datosInvalidos(ComentarioRequestDTO request) {

        return request == null
                || request.getNombre() == null || request.getNombre().isBlank()
                || request.getContrasena() == null || request.getContrasena().isBlank()
                || request.getNombreHotel() == null || request.getNombreHotel().isBlank()
                || request.getReservaId() == null || request.getReservaId() <= 0
                || request.getPuntuacion() == null
                || request.getPuntuacion() < 0 || request.getPuntuacion() > 5
                || request.getComentario() == null || request.getComentario().isBlank();
    }

    private boolean loginInvalido(LoginRequestDTO login) {

        return login == null
                || login.getNombre() == null || login.getNombre().isBlank()
                || login.getContrasena() == null || login.getContrasena().isBlank();
    }

    private boolean esNumeroValido(String valor) {

        if (valor == null || valor.isBlank()) {
            return false;
        }

        try {
            Integer.parseInt(valor);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
