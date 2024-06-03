package com.proyect.park.services.Impl;

import com.proyect.park.models.dto.TipoVehiculoDto;
import com.proyect.park.models.dto.mapper.DtoMapperTipoVehiculo;
import com.proyect.park.models.entities.TipoVehiculo;
import com.proyect.park.repositories.TipoVehiculoRepository;
import com.proyect.park.services.TipoVehiculoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TipoVehiculoServiceImpl implements TipoVehiculoService {


    @Autowired
    private TipoVehiculoRepository tipoVehiculoRepository;
    @Override
    public List<TipoVehiculoDto> obtener() {
        List<TipoVehiculo> vehiculos = (List<TipoVehiculo>) tipoVehiculoRepository.findAll();
        return vehiculos
                .stream()
                .map(u-> DtoMapperTipoVehiculo
                        .builder()
                        .setTipoVehiculo(u)
                        .build()
                ).collect(Collectors.toList());
    }

    @Override
    public Optional<TipoVehiculoDto> encontrar(Long id) {
        return tipoVehiculoRepository.findById(id)
                .map(u-> DtoMapperTipoVehiculo
                        .builder()
                        .setTipoVehiculo(u)
                        .build());
    }

    @Override
    public TipoVehiculoDto guardar(TipoVehiculo tipoVehiculo) {
        return DtoMapperTipoVehiculo.builder().setTipoVehiculo(tipoVehiculoRepository.save(tipoVehiculo)).build();
    }

    @Override
    public    Optional<TipoVehiculoDto>  actualizar(TipoVehiculo tipoVehiculo, Long id) {

        Optional<TipoVehiculo> tipoVehiculoOptional = tipoVehiculoRepository.findById(id);

        TipoVehiculo tipoVehiculoAux = null;

        if(tipoVehiculoOptional.isPresent()){
            TipoVehiculo tipoVehiculoDB = tipoVehiculoOptional.orElseThrow();
            tipoVehiculoDB.setTipo(tipoVehiculo.getTipo());

            tipoVehiculoAux = tipoVehiculoRepository.save(tipoVehiculoDB);
        }

        return Optional.ofNullable(DtoMapperTipoVehiculo.builder().setTipoVehiculo(tipoVehiculoAux).build());
    }

    @Override
    public void eliminar(Long id) {
        tipoVehiculoRepository.deleteById(id);
    }
}
