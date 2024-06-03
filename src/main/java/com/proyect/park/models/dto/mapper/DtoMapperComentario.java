package com.proyect.park.models.dto.mapper;

import com.proyect.park.models.dto.ComentarioDto;
import com.proyect.park.models.entities.Comentario;

public class DtoMapperComentario {
    private Comentario comentario;

    public DtoMapperComentario() {
    }

    public static DtoMapperComentario builder() {
        return new DtoMapperComentario();
    }

    public DtoMapperComentario setComentario(Comentario comentario) {
        this.comentario = comentario;
        return this;
    }

    public ComentarioDto build() {
        if (comentario == null) {
            throw new RuntimeException("DEBE PASAR EL ENTITY COMENTARIO");
        }
        Long estacionamientoId = comentario.getEstacionamiento() != null ? comentario.getEstacionamiento().getId():null;
        Long usuarioId = comentario.getUsuario() != null ? comentario.getUsuario().getId():null;

        return new ComentarioDto(
                this.comentario.getId(),
                this.comentario.getDescripcion(),
                this.comentario.getPunto(),
                estacionamientoId,
                usuarioId

        );
    }
}
