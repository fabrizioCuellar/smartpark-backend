package com.proyect.park.models.entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "estacionamiento")
public class Estacionamiento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Boolean estado;

    @Column(length = 200)
    private String descripcion;

    @Column(columnDefinition = "DECIMAL(5, 2)")
    private Double precio;

    private Float largo;

    private Float ancho;
    private Boolean techado;

    private Integer calificacion;

    @ManyToOne
    @JoinColumn(name = "zona_aparcamiento_id")
    private ZonaAparcamiento zonaAparcamiento;

    @OneToMany(mappedBy = "estacionamiento", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comentario> comentarios;

    @OneToMany(mappedBy = "estacionamiento", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Reserva> reservas;

    public Estacionamiento() {
    }

    public Estacionamiento(Long id, Boolean estado, String descripcion, Double precio, Float largo, Float ancho, Boolean techado, Integer calificacion, ZonaAparcamiento zonaAparcamiento, List<Comentario> comentarios, List<Reserva> reservas) {
        this.id = id;
        this.estado = estado;
        this.descripcion = descripcion;
        this.precio = precio;
        this.largo = largo;
        this.ancho = ancho;
        this.techado = techado;
        this.calificacion = calificacion;
        this.zonaAparcamiento = zonaAparcamiento;
        this.comentarios = comentarios;
        this.reservas = reservas;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double price) {
        this.precio = price;
    }

    public Float getLargo() {
        return largo;
    }

    public void setLargo(Float largo) {
        this.largo = largo;
    }

    public Float getAncho() {
        return ancho;
    }

    public void setAncho(Float ancho) {
        this.ancho = ancho;
    }

    public Boolean getTechado() {
        return techado;
    }

    public void setTechado(Boolean techado) {
        this.techado = techado;
    }

    public Integer getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(Integer calificacion) {
        this.calificacion = calificacion;
    }

    public ZonaAparcamiento getZonaAparcamiento() {
        return zonaAparcamiento;
    }

    public void setZonaAparcamiento(ZonaAparcamiento zonaAparcamiento) {
        this.zonaAparcamiento = zonaAparcamiento;
    }

    public List<Reserva> getReservas() {
        return reservas;
    }

    public void setReservas(List<Reserva> reservas) {
        this.reservas = reservas;
    }

    public List<Comentario> getComentarios() {
        return comentarios;
    }

    public void setComentarios(List<Comentario> comentarios) {
        this.comentarios = comentarios;
    }
}
