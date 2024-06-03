package com.proyect.park.services.Impl;

import com.proyect.park.models.dto.EstacionamientoDto;
import com.proyect.park.models.dto.mapper.DtoMapperEstacionamiento;
import com.proyect.park.models.entities.Estacionamiento;
import com.proyect.park.models.entities.ZonaAparcamiento;
import com.proyect.park.models.request.EstacionamientoRequest;
import com.proyect.park.repositories.EstacionamientoRepository;
import com.proyect.park.repositories.ZonaAparcamientoRepository;
import com.proyect.park.services.EstacionamientoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EstacionamientoServiceImpl implements EstacionamientoService {

    @Autowired
    private EstacionamientoRepository estacionamientoRepository;

    @Autowired
    private ZonaAparcamientoRepository zonaAparcamientoRepository;


    @Override
    public List<EstacionamientoDto> obtener() {
        List<Estacionamiento> estacionamientos = (List<Estacionamiento>) estacionamientoRepository.findAll();
        return estacionamientos
                .stream()
                .map(e -> DtoMapperEstacionamiento
                        .builder()
                        .setEstacionamiento(e)
                        .build()
                ).collect(Collectors.toList());
    }

    @Override
    public Optional<EstacionamientoDto> encontrar(Long id) {
        return estacionamientoRepository.findById(id)
                .map(e -> DtoMapperEstacionamiento
                        .builder()
                        .setEstacionamiento(e)
                        .build());
    }

    @Override
    public EstacionamientoDto guardar(EstacionamientoDto estacionamiento) {
        Optional<ZonaAparcamiento> zonaAparcamientoOptional = zonaAparcamientoRepository.findById(estacionamiento.getZonaAparcamiento());
        ZonaAparcamiento zonaAparcamiento  = zonaAparcamientoOptional.orElseThrow(() -> new IllegalArgumentException("zona de aparcamiento no encontrado"));

        Estacionamiento estacionamientoAux = new Estacionamiento();

        estacionamientoAux.setEstado(estacionamiento.getEstado());
        estacionamientoAux.setDescripcion(estacionamiento.getDescripcion());
        estacionamientoAux.setPrecio(estacionamiento.getPrecio());
        estacionamientoAux.setLargo(estacionamiento.getLargo());
        estacionamientoAux.setAncho(estacionamiento.getAncho());
        estacionamientoAux.setTechado(estacionamiento.getTechado());
        estacionamientoAux.setCalificacion(estacionamiento.getCalificacion());
        estacionamientoAux.setZonaAparcamiento(zonaAparcamiento);



        return DtoMapperEstacionamiento.builder().setEstacionamiento(estacionamientoRepository.save(estacionamientoAux)).build();
    }

    @Override
    public Optional<EstacionamientoDto> actualizar(EstacionamientoRequest estacionamientoRequest, Long id) {
        // Busca el estacionamiento por ID
        Optional<Estacionamiento> estacionamientoOptional = estacionamientoRepository.findById(id);

        // Verifica si el estacionamiento existe
        if (!estacionamientoOptional.isPresent()) {
            throw new NoSuchElementException("No se encontró el estacionamiento con ID: " + id);
        }

        // Busca la zona de aparcamiento por ID
        Optional<ZonaAparcamiento> zonaAparcamientoOptional = zonaAparcamientoRepository.findById(estacionamientoRequest.getZonaAparcamiento());

        // Verifica si la zona de aparcamiento existe
        if (!zonaAparcamientoOptional.isPresent()) {
            throw new NoSuchElementException("No se encontró la zona de aparcamiento con ID: " + estacionamientoRequest.getZonaAparcamiento());
        }

        // Obtén los objetos de los Optional
        Estacionamiento estacionamientoDB = estacionamientoOptional.get();
        ZonaAparcamiento zonaAparcamientoDB = zonaAparcamientoOptional.get();

        // Actualiza los campos del estacionamiento
        estacionamientoDB.setEstado(estacionamientoRequest.getEstado());
        estacionamientoDB.setDescripcion(estacionamientoRequest.getDescripcion());
        estacionamientoDB.setPrecio(estacionamientoRequest.getPrecio());
        estacionamientoDB.setLargo(estacionamientoRequest.getLargo());
        estacionamientoDB.setAncho(estacionamientoRequest.getAncho());
        estacionamientoDB.setTechado(estacionamientoRequest.getTechado());
        estacionamientoDB.setCalificacion(estacionamientoRequest.getCalificacion());
        estacionamientoDB.setZonaAparcamiento(zonaAparcamientoDB);

        // Guarda el estacionamiento actualizado
        Estacionamiento estacionamientoAux = estacionamientoRepository.save(estacionamientoDB);

        // Devuelve el DTO correspondiente
        return Optional.ofNullable(DtoMapperEstacionamiento.builder().setEstacionamiento(estacionamientoAux).build());
    }

    @Override
    public void eliminar(Long id) {
        estacionamientoRepository.deleteById(id);
    }
}
