package com.proyecto.usuarios.service;

import com.proyecto.usuarios.dto.LoginRequestDTO;
import com.proyecto.usuarios.dto.UsuarioRequestDTO;
import com.proyecto.usuarios.entity.Usuario;
import com.proyecto.usuarios.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public String crearUsuario(UsuarioRequestDTO usuario) {
        try {

            // <-- EXTRA: VALIDAR CAMPOS VACIOS
            if (datosInvalidos(usuario)) {
                return "ERROR: TODOS LOS CAMPOS SON OBLIGATORIOS";
            }

            // <-- EXTRA: VALIDAR EMAIL REPETIDO
            if (usuarioRepository.existsByCorreoElectronico(usuario.getCorreoElectronico())) {
                return "ERROR: YA EXISTE UN USUARIO CON ESE EMAIL";
            }

            Usuario usuarioNuevo = new Usuario();

            usuarioNuevo.setNombre(usuario.getNombre());
            usuarioNuevo.setCorreoElectronico(usuario.getCorreoElectronico());
            usuarioNuevo.setContrasena(usuario.getContrasena());
            usuarioNuevo.setDireccion(usuario.getDireccion());

            usuarioRepository.save(usuarioNuevo);

            return "USUARIO CREADO CON EXITO";

        } catch (Exception e) {
            return "ERROR AL CREAR USUARIO";
        }
    }

    public String actualizarUsuario(UsuarioRequestDTO usuario) {

        try {

            // <-- EXTRA: VALIDAR CAMPOS VACIOS
            if (datosInvalidos(usuario)) {
                return "ERROR: TODOS LOS CAMPOS SON OBLIGATORIOS";
            }

            Optional<Usuario> usuarioActual = usuarioRepository.findById(usuario.getUsuarioId());

            if (usuarioActual.isPresent()) {

                // <-- EXTRA: VALIDAR EMAIL EN USO
                if (usuarioRepository.existsByCorreoElectronicoAndIdNot(
                        usuario.getCorreoElectronico(),
                        usuario.getUsuarioId()
                )) {
                    return "ERROR: ESE EMAIL YA ESTA EN USO";
                }

                Usuario actualizacion = usuarioActual.get();

                actualizacion.setNombre(usuario.getNombre());
                actualizacion.setCorreoElectronico(usuario.getCorreoElectronico());
                actualizacion.setDireccion(usuario.getDireccion());
                actualizacion.setContrasena(usuario.getContrasena());

                usuarioRepository.save(actualizacion);

                return "USUARIO ACTUALIZADO CON EXITO";
            }

            return "USUARIO NO ENCONTRADO";

        } catch (Exception e) {
            return "ERROR AL ACTUALIZAR USUARIO";
        }
    }

    public String eliminarUsuario(LoginRequestDTO usuario) {

        try {

            // <-- EXTRA: VALIDAR CAMPOS VACIOS
            if (loginInvalido(usuario)) {
                return "ERROR: NOMBRE Y CONTRASENA SON OBLIGATORIOS";
            }

            // <-- EXTRA: SE USA LISTA PARA PREVENIR MULTIPLES RESULTADOS
            List<Usuario> usuarios =
                    usuarioRepository.findAllByNombreAndContrasena(
                            usuario.getNombre(),
                            usuario.getContrasena()
                    );


            if (!usuarios.isEmpty()) {


                usuarioRepository.delete(usuarios.getFirst());

                return "USUARIO ELIMINADO CON EXITO";
            }

            return "USUARIO NO ENCONTRADO";

        } catch (Exception e) {
            return "ERROR AL ELIMINAR USUARIO";
        }
    }

    public boolean validarUsuario(LoginRequestDTO usuario) {

        // <-- EXTRA: VALIDAR CAMPOS VACIOS
        if (loginInvalido(usuario)) {
            return false;
        }

        return !usuarioRepository
                .findAllByNombreAndContrasena(
                        usuario.getNombre(),
                        usuario.getContrasena()
                )
                .isEmpty();
    }

    public String obtenerInfoUsuarioPorId(Integer id) {

        try {

            Optional<Usuario> usuarioActual = usuarioRepository.findById(id);

            if (usuarioActual.isPresent()) {

                return usuarioActual.get().getNombre();
            }

        } catch (Exception e) {

            return "ERROR AL BUSCAR USUARIO";
        }

        return "NOT_FOUND";
    }

    public String obtenerInfoUsuarioPorNombre(String nombre) {

        try {

            // <-- EXTRA: SE USA LISTA PARA PREVENIR MULTIPLES RESULTADOS
            List<Usuario> usuarios = usuarioRepository.findAllByNombre(nombre);

            if (!usuarios.isEmpty()) {

                // <-- EXTRA: DEVUELVE EL PRIMERO
                return String.valueOf(usuarios.getFirst().getId());
            }

        } catch (Exception e) {

            return "ERROR AL BUSCAR USUARIO";
        }

        return "NOT_FOUND";
    }

    public boolean checkIfExist(Integer id) {

        return usuarioRepository.existsById(id);
    }

    // <-- EXTRA: VALIDACION DATOS USUARIO
    private boolean datosInvalidos(UsuarioRequestDTO usuario) {

        return usuario == null
                || usuario.getNombre() == null || usuario.getNombre().isBlank()
                || usuario.getCorreoElectronico() == null || usuario.getCorreoElectronico().isBlank()
                || usuario.getDireccion() == null || usuario.getDireccion().isBlank()
                || usuario.getContrasena() == null || usuario.getContrasena().isBlank();
    }

    // <-- EXTRA: VALIDACION LOGIN
    private boolean loginInvalido(LoginRequestDTO usuario) {

        return usuario == null
                || usuario.getNombre() == null || usuario.getNombre().isEmpty()
                || usuario.getContrasena() == null || usuario.getContrasena().isEmpty();
    }
}