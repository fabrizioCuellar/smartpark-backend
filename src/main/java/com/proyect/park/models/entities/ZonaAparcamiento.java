package com.proyect.park.models.entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "zona_aparcamientos")
public class ZonaAparcamiento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 30)
    private String nombre;

    @Column(length = 50)
    private String direccion;
    @Column(name = "numero_estacionamiento")
    private Integer numeroEstacionamiento;

    @Column(length = 200)
    private String descripcion;

    @Column(length = 20)
    private String pais;

    @Column(length = 20)
    private String localizacion;

    @Column(name = "imagen", length = 300)
    private String imagen;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @OneToMany(mappedBy = "zonaAparcamiento", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Estacionamiento> estacinamientos;

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

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
