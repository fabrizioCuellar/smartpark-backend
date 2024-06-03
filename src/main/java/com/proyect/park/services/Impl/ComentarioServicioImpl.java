package com.proyect.park.services.Impl;

import com.proyect.park.models.dto.ComentarioDto;
import com.proyect.park.models.dto.mapper.DtoMapperComentario;
import com.proyect.park.models.entities.Comentario;
import com.proyect.park.models.entities.Estacionamiento;
import com.proyect.park.models.entities.Usuario;
import com.proyect.park.models.request.ComentarioRequest;
import com.proyect.park.repositories.ComentarioRepository;
import com.proyect.park.repositories.EstacionamientoRepository;
import com.proyect.park.repositories.UsuarioRepository;
import com.proyect.park.services.ComentarioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ComentarioServicioImpl implements ComentarioServicio {

    @Autowired
    private ComentarioRepository comentarioRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private EstacionamientoRepository estacionamientoRepository;

    @Override
    public List<ComentarioDto> obtener() {
        List<Comentario> comentarios = (List<Comentario>) comentarioRepository.findAll();
        return comentarios
                .stream()
                .map(c -> DtoMapperComentario
                        .builder()
                        .setComentario(c)
                        .build()
                ).collect(Collectors.toList());
    }

    @Override
    public Optional<ComentarioDto> encontrar(Long id) {
        return comentarioRepository.findById(id)
                .map(c -> DtoMapperComentario
                        .builder()
                        .setComentario(c)
                        .build());
    }

    @Override
    public ComentarioDto guardar(ComentarioDto comentario) {
        Optional<Usuario> user = usuarioRepository.findById(comentario.getUsuario());
        Usuario usuario = user.orElseThrow(() -> new IllegalArgumentException("Usuario no encontrada"));

        Optional<Estacionamiento> estacionamientoOptional = estacionamientoRepository.findById(comentario.getUsuario());
        Estacionamiento estacionamiento = estacionamientoOptional.orElseThrow(() -> new IllegalArgumentException("Estacionamiento no encontrada"));

        Comentario comentarioAux = new Comentario();

        comentarioAux.setDescripcion(comentario.getDescripcion());
        comentarioAux.setPunto(comentario.getPunto());
        comentarioAux.setUsuario(usuario);
        comentarioAux.setEstacionamiento(estacionamiento);

        return DtoMapperComentario.builder().setComentario(comentarioRepository.save(comentarioAux)).build();
    }

    @Override
    public Optional<ComentarioDto> actualizar(ComentarioRequest comentarioRequest, Long id) {
        Optional<Comentario> comentarioOptional = comentarioRepository.findById(id);

        Comentario comentarioAux = null;

        if(comentarioOptional.isPresent()){
            Comentario comentarioDB = comentarioOptional.orElseThrow();
            comentarioDB.setDescripcion(comentarioRequest.getDescripcion());
            comentarioDB.setPunto(comentarioRequest.getPunto());

            comentarioAux = comentarioRepository.save(comentarioDB);
        }
        return Optional.ofNullable(DtoMapperComentario.builder().setComentario(comentarioAux).build());
    }

    @Override
    public void eliminar(Long id) {
        comentarioRepository.deleteById(id);
    }
}
