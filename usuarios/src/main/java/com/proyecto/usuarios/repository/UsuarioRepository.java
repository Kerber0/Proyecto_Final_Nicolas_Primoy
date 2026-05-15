package com.proyecto.usuarios.repository;

import com.proyecto.usuarios.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    List<Usuario> findAllByNombreAndContrasena(String nombre, String contrasena);

    List<Usuario> findAllByNombre(String nombre);


    // <-- EXTRA
    boolean existsByCorreoElectronico(String correoElectronico);
    // <-- EXTRA
    boolean existsByCorreoElectronicoAndIdNot(String correoElectronico, Integer id);
}