package com.proyect.park.services.Impl;

import com.proyect.park.models.dto.ZonaAparcamientoDto;
import com.proyect.park.models.dto.mapper.DtoMapperVehiculo;
import com.proyect.park.models.dto.mapper.DtoMapperZonaAparcamiento;
import com.proyect.park.models.entities.Usuario;
import com.proyect.park.models.entities.Vehiculo;
import com.proyect.park.models.entities.ZonaAparcamiento;
import com.proyect.park.models.request.ZonaAparcamientoRequest;
import com.proyect.park.repositories.UsuarioRepository;
import com.proyect.park.repositories.ZonaAparcamientoRepository;
import com.proyect.park.services.ZonaAparcamientoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ZonaAparcamientoServiceImpl implements ZonaAparcamientoService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ZonaAparcamientoRepository zonaAparcamientoRepository;

    @Override
    public List<ZonaAparcamientoDto> obtener() {
        List<ZonaAparcamiento> zonaAparcamientos = (List<ZonaAparcamiento>) zonaAparcamientoRepository.findAll();
        return zonaAparcamientos
                .stream()
                .map(u-> DtoMapperZonaAparcamiento
                        .builder()
                        .setZonaAparcamiento(u)
                        .build()
                ).collect(Collectors.toList());
    }

    @Override
    public Optional<ZonaAparcamientoDto> encontrar(Long id) {
        return zonaAparcamientoRepository.findById(id)
                .map(u-> DtoMapperZonaAparcamiento
                        .builder()
                        .setZonaAparcamiento(u)
                        .build());
    }

    @Override
    public ZonaAparcamientoDto guardar(ZonaAparcamientoDto zonaAparcamientoDto) {

        Optional<Usuario> user = usuarioRepository.findById(zonaAparcamientoDto.getUsuario());
        Usuario usuario = user.orElseThrow(() -> new IllegalArgumentException("Usuario no encontrada"));

        ZonaAparcamiento zonaAparcamientoAux = new ZonaAparcamiento();

        zonaAparcamientoAux.setNombre(zonaAparcamientoDto.getNombre());
        zonaAparcamientoAux.setDescripcion(zonaAparcamientoDto.getDescripcion());
        zonaAparcamientoAux.setDireccion(zonaAparcamientoDto.getDireccion());
        zonaAparcamientoAux.setNumeroEstacionamiento(zonaAparcamientoDto.getNumeroEstacionamiento());
        zonaAparcamientoAux.setPais(zonaAparcamientoDto.getPais());
        zonaAparcamientoAux.setLocalizacion(zonaAparcamientoDto.getLocalizacion());
        zonaAparcamientoAux.setImagen(zonaAparcamientoDto.getImagen());
        zonaAparcamientoAux.setUsuario(usuario);

        return DtoMapperZonaAparcamiento.builder().setZonaAparcamiento(zonaAparcamientoRepository.save(zonaAparcamientoAux)).build();
    }

    @Override
    public Optional<ZonaAparcamientoDto> actualizar(ZonaAparcamientoRequest zonaAparcamientoRequest, Long id) {
        Optional<ZonaAparcamiento> zonaAparcamientoOptional = zonaAparcamientoRepository.findById(id);

        // agregar si falta más atributos en Long en vez de la entidad: Ejemplo Usuario user por Long user, solo paso el Id

        ZonaAparcamiento zonaAparcamientoAux = null;
        if (zonaAparcamientoOptional.isPresent()){

            ZonaAparcamiento zonaAparcamientoDB = zonaAparcamientoOptional.orElseThrow();

            zonaAparcamientoDB.setNombre(zonaAparcamientoRequest.getNombre());
            zonaAparcamientoDB.setDescripcion(zonaAparcamientoRequest.getDescripcion());
            zonaAparcamientoDB.setDireccion(zonaAparcamientoRequest.getDireccion());
            zonaAparcamientoDB.setNumeroEstacionamiento(zonaAparcamientoRequest.getNumeroEstacionamiento());
            zonaAparcamientoDB.setPais(zonaAparcamientoRequest.getPais());
            zonaAparcamientoDB.setLocalizacion(zonaAparcamientoRequest.getLocalizacion());
            zonaAparcamientoDB.setImagen(zonaAparcamientoRequest.getImagen());

            zonaAparcamientoAux = zonaAparcamientoRepository.save(zonaAparcamientoDB);

        }
        return Optional.ofNullable(DtoMapperZonaAparcamiento.builder().setZonaAparcamiento(zonaAparcamientoAux).build());
    }

    @Override
    public void eliminar(Long id) {
        zonaAparcamientoRepository.deleteById(id);
    }
}
