package com.proyect.park.models.dto;

import java.time.LocalDateTime;

public class ReservaDto {
    private Long id;
    private LocalDateTime inicioReserva;
    private Integer horas;
    private Long vehiculo;

    public ReservaDto(Long id, LocalDateTime inicioReserva, Integer horas, Long vehiculo) {
        this.id = id;
        this.inicioReserva = inicioReserva;
        this.horas = horas;
        this.vehiculo = vehiculo;
    }

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
