package com.proyect.park.services;

import com.proyect.park.models.dto.ZonaAparcamientoDto;
import com.proyect.park.models.request.ZonaAparcamientoRequest;

import java.util.List;
import java.util.Optional;

public interface ZonaAparcamientoService {
    List<ZonaAparcamientoDto> obtener();

    Optional<ZonaAparcamientoDto> encontrar(Long id);

    ZonaAparcamientoDto guardar(ZonaAparcamientoDto zonaAparcamientoDto);

    Optional<ZonaAparcamientoDto> actualizar(ZonaAparcamientoRequest zonaAparcamientoRequest, Long id);

    void eliminar(Long id);

}
