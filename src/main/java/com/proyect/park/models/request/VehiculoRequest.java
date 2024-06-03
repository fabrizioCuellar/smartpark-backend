package com.proyect.park.models.request;

public class VehiculoRequest {

    private Long id;
    private Long usuario;
    private String placa;
    private String marca;
    private Long tipoVehiculo;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUsuario() {
        return usuario;
    }

    public void setUsuario(Long usuario) {
        this.usuario = usuario;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public Long getTipoVehiculo() {
        return tipoVehiculo;
    }

    public void setTipoVehiculo(Long tipoVehiculo) {
        this.tipoVehiculo = tipoVehiculo;
    }
}
