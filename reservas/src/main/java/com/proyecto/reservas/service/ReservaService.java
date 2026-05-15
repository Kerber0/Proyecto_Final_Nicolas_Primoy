package com.proyecto.reservas.service;

import com.proyecto.reservas.client.UsuarioClient;
import com.proyecto.reservas.dto.EstadoRequestDTO;
import com.proyecto.reservas.dto.LoginRequestDTO;
import com.proyecto.reservas.dto.ReservaRequestDTO;
import com.proyecto.reservas.dto.ReservaResponseDTO;
import com.proyecto.reservas.entity.Habitacion;
import com.proyecto.reservas.entity.Reserva;
import com.proyecto.reservas.repository.HabitacionRepository;
import com.proyecto.reservas.repository.ReservaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ReservaService {

    @Autowired
    private ReservaRepository reservaRepository;
    @Autowired
    private HabitacionRepository habitacionRepository;
    @Autowired
    private UsuarioClient usuarioClient;


    public String crearReserva(ReservaRequestDTO reserva) {
        try {

            // <-- EXTRA: VALIDAR DATOS DE LA RESERVA
            if (datosInvalidos(reserva)) {
                return "ERROR: TODOS LOS CAMPOS SON OBLIGATORIOS";
            }

            // <-- EXTRA: VALIDAR USUARIO
            LoginRequestDTO login = new LoginRequestDTO(
                    reserva.getNombre(),
                    reserva.getContrasena()
            );

            if (!usuarioClient.validarUsuario(login)) {
                return "USUARIO NO VALIDO";
            }

            String idUsuarioString =
                    usuarioClient.obtenerIdUsuarioPorNombre(reserva.getNombre());

            Integer idUsuario = Integer.parseInt(idUsuarioString);

            Optional<Habitacion> habitacionActual =
                    habitacionRepository.findById(reserva.getHabitacionId());

            if (habitacionActual.isEmpty()) {
                return "HABITACION NO ENCONTRADA";
            }

            if (habitacionOcupada(
                    reserva.getHabitacionId(),
                    reserva.getFechaInicio(),
                    reserva.getFechaFin()
            )) {
                return "HABITACION OCUPADA EN ESAS FECHAS";
            }

            Reserva nuevaReserva = new Reserva();

            nuevaReserva.setUsuarioId(idUsuario);
            nuevaReserva.setHabitacion(habitacionActual.get());
            nuevaReserva.setFechaInicio(reserva.getFechaInicio());
            nuevaReserva.setFechaFin(reserva.getFechaFin());
            nuevaReserva.setEstado("Pendiente");

            reservaRepository.save(nuevaReserva);

            return "RESERVA CREADA CON EXITO";

        } catch (Exception e) {
            return "ERROR AL CREAR RESERVA";
        }
    }

    public String cambiarEstado(EstadoRequestDTO estadoReserva) {

        try {

            if (estadoReserva == null
                    || estadoReserva.getReservaId() == null
                    || estadoReserva.getReservaId() <= 0
                    || estadoReserva.getEstado() == null
                    || estadoReserva.getEstado().isBlank()) {

                return "ERROR: TODOS LOS CAMPOS SON OBLIGATORIOS";
            }

            Optional<Reserva> reservaActual =
                    reservaRepository.findById(estadoReserva.getReservaId());

            if (reservaActual.isEmpty()) {
                return "RESERVA NO ENCONTRADA";
            }

            Reserva reserva = reservaActual.get();

            reserva.setEstado(estadoReserva.getEstado());

            reservaRepository.save(reserva);

            return "ESTADO DE RESERVA CAMBIADO CON EXITO";

        } catch (Exception e) {
            return "ERROR AL CAMBIAR ESTADO DE RESERVA";
        }
    }

    public List<ReservaResponseDTO> listarReservasUsuario(LoginRequestDTO login) {

        try {

            // <-- EXTRA: VALIDAR LOGIN VACIO
            if (login == null
                    || login.getNombre() == null || login.getNombre().isBlank()
                    || login.getContrasena() == null || login.getContrasena().isBlank()) {
                return new ArrayList<>();
            }

            // <-- EXTRA: VALIDAR USUARIO
            if (!usuarioClient.validarUsuario(login)) {
                System.out.println("ERROR: USUARIO NO VALIDO");
                return new ArrayList<>();
            }

            String idUsuarioString =
                    usuarioClient.obtenerIdUsuarioPorNombre(login.getNombre());

            // <-- EXTRA: VALIDAR USUARIO EXISTENTE
            if (idUsuarioString.equals("NOT_FOUND")) {
                return new ArrayList<>();
            }

            Integer userId = Integer.parseInt(idUsuarioString);

            List<Reserva> reservasUsuario =
                    reservaRepository.findByUsuarioId(userId);

            List<ReservaResponseDTO> reservasResponse =
                    new ArrayList<>();

            for (Reserva reserva : reservasUsuario) {

                ReservaResponseDTO dto = new ReservaResponseDTO();

                dto.setFechaInicio(reserva.getFechaInicio());
                dto.setFechaFin(reserva.getFechaFin());

                if (reserva.getHabitacion() != null) {
                    dto.setHabitacionId(reserva.getHabitacion().getId());
                }

                reservasResponse.add(dto);
            }

            return reservasResponse;

        } catch (Exception e) {

            return new ArrayList<>();
        }
    }


    public List<ReservaResponseDTO> listarReservasSegunEstado(String estado) {

        try {

            // <-- EXTRA: VALIDAR ESTADO VACIO
            if (estado == null || estado.isBlank()) {
                System.out.println("ERROR: ESTADO OBLIGATORIO");
                return new ArrayList<>();
            }

            List<Reserva> reservasEstado =
                    reservaRepository.findByEstado(estado);

            if (reservasEstado.isEmpty()) {
                System.out.println("NO EXISTEN RESERVAS CON ESE ESTADO");
                return new ArrayList<>();
            }

            List<ReservaResponseDTO> reservasResponse =
                    new ArrayList<>();

            for (Reserva reserva : reservasEstado) {

                ReservaResponseDTO dto = new ReservaResponseDTO();

                dto.setFechaInicio(reserva.getFechaInicio());
                dto.setFechaFin(reserva.getFechaFin());

                if (reserva.getHabitacion() != null) {
                    dto.setHabitacionId(reserva.getHabitacion().getId());
                }

                reservasResponse.add(dto);
            }

            return reservasResponse;

        } catch (Exception e) {

            System.out.println("ERROR AL LISTAR RESERVAS POR ESTADO");
            return new ArrayList<>();
        }
    }

    public boolean checkReserva(
            Integer idUsuario,
            Integer idHotel,
            Integer idReserva) {

        // <-- EXTRA: VALIDAR IDS INVALIDOS
        if (idUsuario == null || idUsuario <= 0
                || idHotel == null || idHotel <= 0
                || idReserva == null || idReserva <= 0) {

            return false;
        }

        Optional<Reserva> reservaActual =
                reservaRepository.findByIdAndUsuarioId(
                        idReserva,
                        idUsuario
                );

        if (reservaActual.isEmpty()) {
            return false;
        }

        Reserva reserva = reservaActual.get();

        // <-- EXTRA: VALIDAR HABITACION Y HOTEL
        if (reserva.getHabitacion() == null
                || reserva.getHabitacion().getHotel() == null) {

            return false;
        }

        // <-- EXTRA: NO PERMITIR RESERVAS CANCELADAS
        if (reserva.getEstado().equalsIgnoreCase("Cancelada")) {
            return false;
        }

        return reserva.getHabitacion()
                .getHotel()
                .getId()
                .equals(idHotel);
    }


    // <-- EXTRA: VALIDACION DATOS RESERVA
    private boolean datosInvalidos(ReservaRequestDTO reserva) {

        return reserva == null
                || reserva.getFechaInicio() == null
                || reserva.getFechaFin() == null
                || reserva.getHabitacionId() == null
                || reserva.getHabitacionId() <= 0
                || reserva.getFechaInicio().isAfter(reserva.getFechaFin());
    }

    // <-- EXTRA: VALIDAR QUE LA HABITACION NO ESTE OCUPADA
    private boolean habitacionOcupada(Integer habitacionId, LocalDate fechaInicio, LocalDate fechaFin) {

        List<Reserva> reservas = reservaRepository.findAllByHabitacion_Id(habitacionId);

        for (Reserva reserva : reservas) {

            boolean seCruzanFechas =
                    !fechaFin.isBefore(reserva.getFechaInicio())
                            && !fechaInicio.isAfter(reserva.getFechaFin());

            if (seCruzanFechas && !reserva.getEstado().equalsIgnoreCase("Cancelada")) {
                return true;
            }
        }

        return false;
    }


}
