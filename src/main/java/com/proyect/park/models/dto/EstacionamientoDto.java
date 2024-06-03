package com.proyect.park.models.dto;

public class EstacionamientoDto {
    private Long id;
    private Boolean estado;
    private String descripcion;
    private Double precio;
    private Float largo;
    private Float ancho;
    private Boolean techado;
    private Integer calificacion;
    private Long zonaAparcamiento;

    public EstacionamientoDto(Long id, Boolean estado, String descripcion, Double precio, Float largo, Float ancho, Boolean techado, Integer calificacion, Long zonaAparcamiento) {
        this.id = id;
        this.estado = estado;
        this.descripcion = descripcion;
        this.precio = precio;
        this.largo = largo;
        this.ancho = ancho;
        this.techado = techado;
        this.calificacion = calificacion;
        this.zonaAparcamiento = zonaAparcamiento;
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

    public void setPrecio(Double precio) {
        this.precio = precio;
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

    public Long getZonaAparcamiento() {
        return zonaAparcamiento;
    }

    public void setZonaAparcamiento(Long zonaAparcamiento) {
        this.zonaAparcamiento = zonaAparcamiento;
    }
}
