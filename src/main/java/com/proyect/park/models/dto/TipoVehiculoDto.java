package com.proyect.park.models.dto;

import com.proyect.park.models.entities.TipoVehiculo;
import com.proyect.park.models.entities.Vehiculo;

import java.util.List;

public class TipoVehiculoDto {
    private Long id;

    private String tipo;


    public TipoVehiculoDto(Long id, String tipo) {
        this.id = id;
        this.tipo = tipo;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

}

