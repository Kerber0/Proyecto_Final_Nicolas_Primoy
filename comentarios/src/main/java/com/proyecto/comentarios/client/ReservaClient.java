package com.proyecto.comentarios.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "reservas")
public interface ReservaClient {

    @PostMapping("/reservas/hotel/id/{nombre}")
    String obtenerIdApartirNombre(@PathVariable String nombre);

    @PostMapping("/reservas/hotel/nombre/{id}")
    String obtenerNombreAPartirId(@PathVariable Integer id);

    @GetMapping("/reservas/check/{idUsuario}/{idHotel}/{idReserva}")
    boolean checkReserva(
            @PathVariable Integer idUsuario,
            @PathVariable Integer idHotel,
            @PathVariable Integer idReserva
    );
}
