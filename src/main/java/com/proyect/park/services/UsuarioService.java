package com.proyect.park.services;

import com.proyect.park.models.dto.UsuarioDto;
import com.proyect.park.models.entities.Usuario;
import com.proyect.park.models.request.UsuarioRequest;

import java.util.List;
import java.util.Optional;

public interface UsuarioService {
    List<UsuarioDto> obtenerUsuarios() ;
    Optional<UsuarioDto> encontrarUsuario(Long id);

    UsuarioDto guardarUsuario(Usuario usuario);

    Optional<UsuarioDto> actualizarUsuario(UsuarioRequest usuario, Long id);

    void eliminarUsuario(Long id);
}
