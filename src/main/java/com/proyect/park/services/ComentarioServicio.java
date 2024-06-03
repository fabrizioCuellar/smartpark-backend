package com.proyect.park.services;

import com.proyect.park.models.dto.ComentarioDto;
import com.proyect.park.models.request.ComentarioRequest;

import java.util.List;
import java.util.Optional;

public interface ComentarioServicio {
    List<ComentarioDto> obtener();

    Optional<ComentarioDto> encontrar(Long id);

    ComentarioDto guardar(ComentarioDto comentario);

    Optional<ComentarioDto> actualizar(ComentarioRequest comentarioRequest, Long id);

    void eliminar(Long id);
}
