package com.proyect.park.services.Impl;

import com.proyect.park.models.dto.VehiculoDto;
import com.proyect.park.models.dto.mapper.DtoMapperVehiculo;
import com.proyect.park.models.entities.TipoVehiculo;
import com.proyect.park.models.entities.Usuario;
import com.proyect.park.models.entities.Vehiculo;
import com.proyect.park.models.request.VehiculoRequest;
import com.proyect.park.repositories.TipoVehiculoRepository;
import com.proyect.park.repositories.UsuarioRepository;
import com.proyect.park.repositories.VehiculoRepository;
import com.proyect.park.services.VehiculoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class VehiculoServiceImpl implements VehiculoService {

    @Autowired
    private VehiculoRepository vehiculoRepository;

    @Autowired
    private TipoVehiculoRepository tipoVehiculoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public List<VehiculoDto> obtener() {
        List<Vehiculo> vehiculos = (List<Vehiculo>) vehiculoRepository.findAll();
        return vehiculos
                .stream()
                .map(u-> DtoMapperVehiculo
                        .builder()
                        .setVehiculo(u)
                        .build()
                ).collect(Collectors.toList());
    }

    @Override
    public Optional<VehiculoDto> encontrar(Long id) {
        return vehiculoRepository.findById(id)
                .map(u-> DtoMapperVehiculo
                        .builder()
                        .setVehiculo(u)
                        .build());
    }

    @Override
    public VehiculoDto guardar(VehiculoDto vehiculo) {

        // se va a sacar él, id de usuario y tipo Vehículo
        Optional<Usuario> user = usuarioRepository.findById(vehiculo.getUsuario());
        Usuario usuario = user.orElseThrow(() -> new IllegalArgumentException("Usuario no encontrada"));

        Optional<TipoVehiculo> tipoVehiculo = tipoVehiculoRepository.findById(vehiculo.getTipoVehiculo());
        TipoVehiculo tipoVehiculo1 = tipoVehiculo.orElseThrow(() -> new IllegalArgumentException("Usuario no encontrada"));

        Vehiculo vehiculoAux = new Vehiculo();

        vehiculoAux.setMarca(vehiculo.getMarca());
        vehiculoAux.setPlaca(vehiculo.getPlaca());
        vehiculoAux.setUsuario(usuario);
        vehiculoAux.setTipoVehiculo(tipoVehiculo1);

        return DtoMapperVehiculo.builder().setVehiculo(vehiculoRepository.save(vehiculoAux)).build();
    }

    @Override
    public Optional<VehiculoDto> actualizar(VehiculoRequest vehiculo, Long id) {

        Optional<Vehiculo> o = vehiculoRepository.findById(id);
        Optional<TipoVehiculo>tipoVehiculoOptional = tipoVehiculoRepository.findById(vehiculo.getTipoVehiculo());

        Vehiculo vehiculoAux = null;
        if(o.isPresent()){
            Vehiculo vehiculoDB = o.orElseThrow();
            vehiculoDB.setMarca(vehiculo.getMarca());
            vehiculoDB.setPlaca(vehiculo.getPlaca());
            vehiculoDB.setTipoVehiculo(tipoVehiculoOptional.orElseThrow());
            vehiculoAux = vehiculoRepository.save(vehiculoDB);
        }
        return Optional.ofNullable(DtoMapperVehiculo.builder().setVehiculo(vehiculoAux).build());

    }

    @Override
    public void eliminar(Long id) {
        vehiculoRepository.deleteById(id);
    }
}
