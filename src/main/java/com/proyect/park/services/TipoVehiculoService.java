package com.proyect.park.services;

import com.proyect.park.models.dto.TipoVehiculoDto;
import com.proyect.park.models.entities.TipoVehiculo;

import java.util.List;
import java.util.Optional;

public interface TipoVehiculoService {
    List<TipoVehiculoDto> obtener() ;
    Optional<TipoVehiculoDto> encontrar(Long id);

    TipoVehiculoDto guardar(TipoVehiculo tipoVehiculo);

    Optional<TipoVehiculoDto>  actualizar(TipoVehiculo tipoVehiculo, Long id);

    void eliminar(Long id);
}
