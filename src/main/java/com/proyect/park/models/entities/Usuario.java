package com.proyect.park.models.entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "usuarios")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50)
    private String fullname;

    @Column(length = 10)
    private String password;

    private Long cellphone;

    @Column(length = 50)
    private String email;

    private Integer type;

    @ManyToMany
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"),
            uniqueConstraints = {@UniqueConstraint(columnNames = {"user_id","role_id"})}
    )
    private List<Role> roles;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Vehiculo> vehiculos;


    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ZonaAparcamiento> zonaAparcamientos;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comentario> comentarios;

    public Usuario() {
    }

    public Usuario(Long id, String fullname, String password, Long cellphone, String email, Integer type, List<Role> roles, List<Vehiculo> vehiculos, List<ZonaAparcamiento> zonaAparcamientos, List<Comentario> comentarios) {
        this.id = id;
        this.fullname = fullname;
        this.password = password;
        this.cellphone = cellphone;
        this.email = email;
        this.type = type;
        this.roles = roles;
        this.vehiculos = vehiculos;
        this.zonaAparcamientos = zonaAparcamientos;
        this.comentarios = comentarios;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getCellphone() {
        return cellphone;
    }

    public void setCellphone(Long cellphone) {
        this.cellphone = cellphone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public List<Vehiculo> getVehiculos() {
        return vehiculos;
    }

    public void setVehiculos(List<Vehiculo> vehiculos) {
        this.vehiculos = vehiculos;
    }

    public List<ZonaAparcamiento> getZonaApartamientos() {
        return zonaAparcamientos;
    }

    public void setZonaApartamientos(List<ZonaAparcamiento> zonaAparcamientos) {
        this.zonaAparcamientos = zonaAparcamientos;
    }

    public List<ZonaAparcamiento> getZonaAparcamientos() {
        return zonaAparcamientos;
    }

    public void setZonaAparcamientos(List<ZonaAparcamiento> zonaAparcamientos) {
        this.zonaAparcamientos = zonaAparcamientos;


    }

    public List<Comentario> getComentarios() {
        return comentarios;
    }

    public void setComentarios(List<Comentario> comentarios) {
        this.comentarios = comentarios;
    }
}
