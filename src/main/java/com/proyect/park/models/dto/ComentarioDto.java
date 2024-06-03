package com.proyect.park.models.dto;

import jakarta.persistence.Column;

public class ComentarioDto {
    private Long id;
    private String descripcion;
    private Integer punto;
    private Long estacionamiento;
    private Long usuario;

    public ComentarioDto(Long id, String descripcion, Integer punto, Long estacionamiento, Long usuario) {
        this.id = id;
        this.descripcion = descripcion;
        this.punto = punto;
        this.estacionamiento = estacionamiento;
        this.usuario = usuario;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Integer getPunto() {
        return punto;
    }

    public void setPunto(Integer punto) {
        this.punto = punto;
    }

    public Long getEstacionamiento() {
        return estacionamiento;
    }

    public void setEstacionamiento(Long estacionamiento) {
        this.estacionamiento = estacionamiento;
    }

    public Long getUsuario() {
        return usuario;
    }

    public void setUsuario(Long usuario) {
        this.usuario = usuario;
    }
}
