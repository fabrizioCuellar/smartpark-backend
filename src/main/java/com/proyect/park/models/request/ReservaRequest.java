package com.proyect.park.models.request;

import com.proyect.park.models.entities.Reserva;
import com.proyect.park.models.entities.TipoVehiculo;
import com.proyect.park.models.entities.Usuario;
import com.proyect.park.models.entities.Vehiculo;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;


public class ReservaRequest {

    private Long id;
    private LocalDateTime inicioReserva;
    private Integer horas;
    private Long vehiculo;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getInicioReserva() {
        return inicioReserva;
    }

    public void setInicioReserva(LocalDateTime inicioReserva) {
        this.inicioReserva = inicioReserva;
    }

    public Integer getHoras() {
        return horas;
    }

    public void setHoras(Integer horas) {
        this.horas = horas;
    }

    public Long getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(Long vehiculo) {
        this.vehiculo = vehiculo;
    }
}
