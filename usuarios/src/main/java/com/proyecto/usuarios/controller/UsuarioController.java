package com.proyecto.usuarios.controller;

import com.proyecto.usuarios.dto.LoginRequestDTO;
import com.proyecto.usuarios.dto.UsuarioRequestDTO;
import com.proyecto.usuarios.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/registrar")
    public String crearUsuario(@RequestBody UsuarioRequestDTO usuario) {
        return usuarioService.crearUsuario(usuario);
    }

    @PutMapping("/registrar")
    public String actualizarUsuario(@RequestBody UsuarioRequestDTO usuario) {
        return usuarioService.actualizarUsuario(usuario);
    }

    @DeleteMapping
    public String eliminarUsuario(@RequestBody LoginRequestDTO usuario) {
        return usuarioService.eliminarUsuario(usuario);
    }

    @PostMapping("/validar")
    public boolean validarUsuario(@RequestBody LoginRequestDTO usuario) {
        return usuarioService.validarUsuario(usuario);
    }

    @GetMapping("/info/id/{id}")
    public String obtenerInfoUsuarioPorId(@PathVariable Integer id) {
        return usuarioService.obtenerInfoUsuarioPorId(id);
    }

    @GetMapping("/info/nombre/{nombre}")
    public String obtenerInfoUsuarioPorNombre(@PathVariable String nombre) {
        return usuarioService.obtenerInfoUsuarioPorNombre(nombre);
    }

    @GetMapping("/checkIfExist/{id}")
    public boolean checkIfExist(@PathVariable Integer id) {
        return usuarioService.checkIfExist(id);
    }
}