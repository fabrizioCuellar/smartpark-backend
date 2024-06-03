package com.proyect.park.services;

import com.proyect.park.models.dto.VehiculoDto;
import com.proyect.park.models.entities.Vehiculo;
import com.proyect.park.models.request.VehiculoRequest;

import java.util.List;
import java.util.Optional;

public interface VehiculoService {

    List<VehiculoDto> obtener() ;
    Optional<VehiculoDto> encontrar(Long id);

    VehiculoDto guardar(VehiculoDto vehiculo);

    Optional<VehiculoDto> actualizar(VehiculoRequest vehiculo, Long id);

    void eliminar(Long id);
}
