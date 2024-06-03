package com.proyect.park.models.dto.mapper;

import com.proyect.park.models.dto.ZonaAparcamientoDto;
import com.proyect.park.models.entities.ZonaAparcamiento;

public class DtoMapperZonaAparcamiento {

    private ZonaAparcamiento zonaAparcamiento;

    public DtoMapperZonaAparcamiento(){

    }
    public  static DtoMapperZonaAparcamiento builder(){
        return new DtoMapperZonaAparcamiento();
    }

    public DtoMapperZonaAparcamiento setZonaAparcamiento(ZonaAparcamiento zonaAparcamiento){
        this.zonaAparcamiento = zonaAparcamiento;
        return  this;
    }

    public ZonaAparcamientoDto build(){
        if(zonaAparcamiento == null){
            throw new RuntimeException("DEBE PASAR EL ENTITY ZONA DE APARCAMIENTO");
        }

        Long UsuarioId = zonaAparcamiento.getUsuario() != null ? zonaAparcamiento.getUsuario().getId():null;
        return  new ZonaAparcamientoDto(
                this.zonaAparcamiento.getId()
                ,zonaAparcamiento.getNombre()
                ,zonaAparcamiento.getDireccion()
                ,zonaAparcamiento.getNumeroEstacionamiento()
                ,zonaAparcamiento.getDescripcion()
                ,zonaAparcamiento.getPais()
                ,zonaAparcamiento.getLocalizacion()
                ,zonaAparcamiento.getImagen()
                ,UsuarioId
        );
    }
}
