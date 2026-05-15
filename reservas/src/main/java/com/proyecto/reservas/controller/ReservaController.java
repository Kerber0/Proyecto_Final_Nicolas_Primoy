package com.proyecto.reservas.controller;

import com.proyecto.reservas.dto.EstadoRequestDTO;
import com.proyecto.reservas.dto.LoginRequestDTO;
import com.proyecto.reservas.dto.ReservaRequestDTO;
import com.proyecto.reservas.dto.ReservaResponseDTO;
import com.proyecto.reservas.service.ReservaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reservas")
public class ReservaController {

    @Autowired
    private ReservaService reservaService;


    @PostMapping
    public String crearReserva(@RequestBody ReservaRequestDTO reserva){
        return reservaService.crearReserva(reserva);
    }

    @PatchMapping
    public String cambiarEstado(@RequestBody EstadoRequestDTO reserva){
        return reservaService.cambiarEstado(reserva);
    }
    @GetMapping
    public List<ReservaResponseDTO> listarReservasUsuario(
            @RequestBody LoginRequestDTO usuario) {

        return reservaService.listarReservasUsuario(usuario);
    }

    @GetMapping("/{estado}")
    public List<ReservaResponseDTO> listarReservasSegunEstado(
            @PathVariable String estado) {

        return reservaService.listarReservasSegunEstado(estado);
    }

    @GetMapping("/check/{idUsuario}/{idHotel}/{idReserva}")
    public boolean checkReserva(
            @PathVariable Integer idUsuario,
            @PathVariable Integer idHotel,
            @PathVariable Integer idReserva) {

        return reservaService.checkReserva(
                idUsuario,
                idHotel,
                idReserva
        );
    }

}
