package com.proyect.park.services.Impl;

import com.proyect.park.models.dto.ReservaDto;
import com.proyect.park.models.dto.mapper.DtoMapperReserva;
import com.proyect.park.models.entities.Reserva;
import com.proyect.park.models.entities.Vehiculo;
import com.proyect.park.models.request.ReservaRequest;
import com.proyect.park.repositories.ReservaRepository;
import com.proyect.park.repositories.VehiculoRepository;
import com.proyect.park.services.ReservaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ReservaServiceImpl implements ReservaService {

    @Autowired
    private ReservaRepository reservaRepository;


    @Autowired
    private VehiculoRepository vehiculoRepository;


    @Override
    public List<ReservaDto> obtener() {
        List<Reserva> reservas = (List<Reserva>) reservaRepository.findAll();
        return reservas
                .stream()
                .map(r -> DtoMapperReserva
                        .builder()
                        .setReserva(r)
                        .build()
                ).collect(Collectors.toList());
    }

    @Override
    public Optional<ReservaDto> encontrar(Long id) {
        return reservaRepository.findById(id)
                .map(r -> DtoMapperReserva
                        .builder()
                        .setReserva(r)
                        .build());
    }

    @Override
    public ReservaDto guardar(ReservaDto reserva) {

        Optional<Vehiculo> auto = vehiculoRepository.findById(reserva.getVehiculo());
        Vehiculo vehiculo = auto.orElseThrow(() -> new IllegalArgumentException("Vehiculo no encontrado"));

        Reserva reservaAux = new Reserva();

        reservaAux.setInicioReserva(reserva.getInicioReserva());
        reservaAux.setHoras(reserva.getHoras());
        reservaAux.setVehiculo(vehiculo);

        return DtoMapperReserva.builder().setReserva(reservaRepository.save(reservaAux)).build();
    }

    @Override
    public Optional<ReservaDto> actualizar(ReservaRequest reservaRequest, Long id) {
        Optional<Reserva> reservaOptional = reservaRepository.findById(id);



        Reserva reservaAux = null;
        if (reservaOptional.isPresent()) {

            Optional<Vehiculo> vehiculoOptional = vehiculoRepository.findById(reservaRequest.getVehiculo());
          //  Optional<Espacio> espacioOptional = espacioRepository.findById(reservaRequest.getEspacio());
            if(vehiculoOptional.isPresent() ){
                Reserva reservaDB = reservaOptional.orElseThrow();
                // la hora de inicio no se actualiza, se tiene que eliminar y hacer otra vez
                reservaDB.setHoras(reservaRequest.getHoras());
                reservaDB.setVehiculo(vehiculoOptional.orElseThrow());
               // reservaDB.setEspacio(espacioOptional.orElseThrow());
                reservaAux = reservaRepository.save(reservaDB);
            }

        }

        return Optional.ofNullable(DtoMapperReserva.builder().setReserva(reservaAux).build());
    }

    @Override
    public void eliminar(Long id) {
        reservaRepository.deleteById(id);
    }
}
