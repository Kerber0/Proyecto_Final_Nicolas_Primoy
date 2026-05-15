package com.proyecto.usuarios.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "reservas")
public interface ReservaClient {

    @GetMapping("/reservas/usuario/{idUsuario}")
    boolean usuarioTieneReservas(@PathVariable Integer idUsuario);
}
