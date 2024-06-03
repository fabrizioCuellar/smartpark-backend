package com.proyect.park.models.request;

import com.proyect.park.models.entities.Estacionamiento;
import com.proyect.park.models.entities.Usuario;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

public class ComentarioRequest {

    private Long id;
    private String descripcion;
    private Integer punto;
    private Long estacionamiento;

    private Long usuario;

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
