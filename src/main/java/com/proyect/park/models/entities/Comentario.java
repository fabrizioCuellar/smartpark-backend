package com.proyect.park.models.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "comentarios")
public class Comentario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 200)
    private String descripcion;

    private Integer punto;

    @ManyToOne
    @JoinColumn(name = "estacionamiento_id")
    private Estacionamiento estacionamiento;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    public Comentario(Long id, String descripcion, Integer punto, Estacionamiento estacionamiento, Usuario usuario) {
        this.id = id;
        this.descripcion = descripcion;
        this.punto = punto;
        this.estacionamiento = estacionamiento;
        this.usuario = usuario;
    }

    public Comentario() {

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

    public Estacionamiento getEstacionamiento() {
        return estacionamiento;
    }

    public void setEstacionamiento(Estacionamiento estacionamiento) {
        this.estacionamiento = estacionamiento;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
