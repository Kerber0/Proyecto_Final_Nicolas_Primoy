package com.proyecto.reservas.client;

import com.proyecto.reservas.dto.LoginRequestDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "usuarios")
public interface UsuarioClient {

    @PostMapping("/usuarios/validar")
    boolean validarUsuario(@RequestBody LoginRequestDTO usuario);

    @GetMapping("/usuarios/info/nombre/{nombre}")
    String obtenerIdUsuarioPorNombre(@PathVariable String nombre);

    @GetMapping("/usuarios/checkIfExist/{id}")
    boolean checkIfExist(@PathVariable Integer id);

}
