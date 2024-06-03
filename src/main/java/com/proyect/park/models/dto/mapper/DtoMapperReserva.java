package com.proyect.park.models.dto.mapper;

import com.proyect.park.models.dto.ReservaDto;
import com.proyect.park.models.entities.Reserva;

public class DtoMapperReserva {
    private Reserva reserva;

    public DtoMapperReserva() {
    }

    public static DtoMapperReserva builder() {
        return new DtoMapperReserva();
    }

    public DtoMapperReserva setReserva(Reserva reserva) {
        this.reserva = reserva;
        return this;
    }

    public ReservaDto build() {
        if (reserva == null) {
            throw new RuntimeException("DEBE PASAR EL ENTITY RESERVA");
        }

        Long vehiculoId = reserva.getVehiculo() != null ? reserva.getVehiculo().getId() :null;
       // Long espacioId = reserva.getEspacio() != null ? reserva.getEspacio().getId() :null;
        return new ReservaDto(
                this.reserva.getId(),
                this.reserva.getInicioReserva(),
                this.reserva.getHoras(),
                vehiculoId
         //       espacioId
        );
    }
}
