package com.proyect.park.models.dto.mapper;

import com.proyect.park.models.dto.VehiculoDto;
import com.proyect.park.models.entities.Vehiculo;

public class DtoMapperVehiculo {

    private Vehiculo vehiculo;

    public DtoMapperVehiculo() {
    }

    public static DtoMapperVehiculo builder() {
        return new DtoMapperVehiculo();
    }

    public DtoMapperVehiculo setVehiculo(Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
        return this;
    }

    public VehiculoDto build() {
        if (vehiculo == null) {
            throw new RuntimeException("DEBE PASAR EL ENTITY VEHICULO");
        }

        Long usuario = vehiculo.getUsuario() != null ? vehiculo.getUsuario().getId():null;
        Long vehiculoId = vehiculo.getTipoVehiculo() != null ? vehiculo.getTipoVehiculo().getId():null;


        return new VehiculoDto(
                this.vehiculo.getId(),
                usuario,
                vehiculo.getPlaca(),
                vehiculo.getMarca(),
                vehiculoId
        );
    }
}
