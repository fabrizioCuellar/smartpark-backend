package com.proyect.park.models.dto.mapper;

import com.proyect.park.models.dto.TipoVehiculoDto;
import com.proyect.park.models.dto.UsuarioDto;
import com.proyect.park.models.entities.TipoVehiculo;
import com.proyect.park.models.entities.Usuario;

public class DtoMapperTipoVehiculo {
    private TipoVehiculo tipoVehiculo;

    public DtoMapperTipoVehiculo() {
    }

    public static DtoMapperTipoVehiculo builder(){
        return new DtoMapperTipoVehiculo();
    }

    public DtoMapperTipoVehiculo setTipoVehiculo(TipoVehiculo tipoVehiculo){
        this.tipoVehiculo = tipoVehiculo;
        return this;
    }

    public TipoVehiculoDto build(){
        if(tipoVehiculo == null){
            throw new RuntimeException("DEBE PASAR EL ENTITY TIPO DE VEHICULO!");
        }

       // Long vehiculoId = tipoVehiculo.getVehiculo() != null ? tipoVehiculo.getVehiculo().getId():null;

        return new TipoVehiculoDto(
                this.tipoVehiculo.getId(),
                tipoVehiculo.getTipo()
        );
    }
}
