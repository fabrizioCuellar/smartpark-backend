package com.proyect.park.models.request;

public class ZonaAparcamientoRequest {

    private Long id;
    private String nombre;
    private String direccion;
    private Integer numeroEstacionamiento;
    private String descripcion;
    private String pais;
    private String localizacion;
    private String imagen;
    private Long usuario;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Integer getNumeroEstacionamiento() {
        return numeroEstacionamiento;
    }

    public void setNumeroEstacionamiento(Integer numeroEstacionamiento) {
        this.numeroEstacionamiento = numeroEstacionamiento;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getLocalizacion() {
        return localizacion;
    }

    public void setLocalizacion(String localizacion) {
        this.localizacion = localizacion;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public Long getUsuario() {
        return usuario;
    }

    public void setUsuario(Long usuario) {
        this.usuario = usuario;
    }
}
