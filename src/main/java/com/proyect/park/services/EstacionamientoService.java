package com.proyect.park.services;

import com.proyect.park.models.dto.EstacionamientoDto;
import com.proyect.park.models.dto.ReservaDto;
import com.proyect.park.models.request.EstacionamientoRequest;
import com.proyect.park.models.request.ReservaRequest;

import java.util.List;
import java.util.Optional;

public interface EstacionamientoService {

    List<EstacionamientoDto> obtener();

    Optional<EstacionamientoDto> encontrar(Long id);

    EstacionamientoDto guardar(EstacionamientoDto reserva);

    Optional<EstacionamientoDto> actualizar(EstacionamientoRequest estacionamientoRequest, Long id);

    void eliminar(Long id);
}
