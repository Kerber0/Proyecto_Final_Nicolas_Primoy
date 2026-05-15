package com.proyecto.comentarios.client;

import com.proyecto.comentarios.dto.LoginRequestDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "usuarios")
public interface UsuarioClient {

    @PostMapping("/usuarios/validar")
    boolean validarUsuario(@RequestBody LoginRequestDTO usuario);

    @GetMapping("/usuarios/info/nombre/{nombre}")
    String obtenerIdUsuarioPorNombre(@PathVariable String nombre);
}