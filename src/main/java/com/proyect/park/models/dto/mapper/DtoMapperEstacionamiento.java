package com.proyect.park.models.dto.mapper;

import com.proyect.park.models.dto.EstacionamientoDto;
import com.proyect.park.models.entities.Estacionamiento;

public class DtoMapperEstacionamiento {

    private Estacionamiento estacionamiento;

    public DtoMapperEstacionamiento() {
    }

    public static DtoMapperEstacionamiento builder() {
        return new DtoMapperEstacionamiento();
    }

    public DtoMapperEstacionamiento setEstacionamiento(Estacionamiento estacionamiento) {
        this.estacionamiento = estacionamiento;
        return this;
    }

    public EstacionamientoDto build() {
        if (estacionamiento == null) {
            throw new RuntimeException("DEBE PASAR EL ENTITY ESTACIONAMIENTO");
        }

        Long zonaAparcamientoId = estacionamiento.getZonaAparcamiento() != null ? estacionamiento.getZonaAparcamiento().getId():null;

        return new EstacionamientoDto(
                this.estacionamiento.getId(),
                estacionamiento.getEstado(),
                estacionamiento.getDescripcion(),
                estacionamiento.getPrecio(),
                estacionamiento.getLargo(),
                estacionamiento.getAncho(),
                estacionamiento.getTechado(),
                estacionamiento.getCalificacion(),
                zonaAparcamientoId
                //estacionamiento.getComentarios()
        );
    }

}
