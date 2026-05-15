package com.proyecto.usuarios.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioRequestDTO {
    private int usuarioId;
    private String nombre;
    private String correoElectronico;
    private String direccion;
    private String contrasena;
}
