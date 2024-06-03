package com.proyect.park.controllers;

import com.proyect.park.models.dto.TipoVehiculoDto;
import com.proyect.park.models.dto.VehiculoDto;
import com.proyect.park.models.entities.TipoVehiculo;
import com.proyect.park.services.TipoVehiculoService;
import com.proyect.park.services.VehiculoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/tipo_vehiculo")
@CrossOrigin(origins = "*")
public class TipoVehiculoController {

    @Autowired
    private TipoVehiculoService tipoVehiculoService;

    @GetMapping("/todos")
    public List<TipoVehiculoDto> obtener() {
        return tipoVehiculoService.obtener();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> show(@PathVariable Long id) {
        Optional<TipoVehiculoDto> vehiculoDtoOptional = tipoVehiculoService.encontrar(id);

        if (vehiculoDtoOptional.isPresent()) {
            return ResponseEntity.ok(vehiculoDtoOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/guardar")
    public ResponseEntity<?> guardar(@Valid @RequestBody TipoVehiculo vehiculo, BindingResult result) {
        if (result.hasErrors()) {
            return validation(result);
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(tipoVehiculoService.guardar(vehiculo));
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<?> update(@Valid @RequestBody TipoVehiculo vehiculoRequest, BindingResult result, @PathVariable Long id) {
        if (result.hasErrors()) {
            return validation(result);
        }
        Optional<TipoVehiculoDto> tipo = tipoVehiculoService.actualizar(vehiculoRequest, id);

        if (tipo.isPresent()) {
            return ResponseEntity.status(HttpStatus.CREATED).body(tipo);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> remove(@PathVariable Long id) {
        Optional<TipoVehiculoDto> o = tipoVehiculoService.encontrar(id);

        if (o.isPresent()) {
            tipoVehiculoService.eliminar(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    private ResponseEntity<?> validation(BindingResult result) {
        Map<String, String> errors = new HashMap<>();
        result.getFieldErrors().forEach(err -> {
            errors.put(err.getField(), "El campo " + err.getField() + " " + err.getDefaultMessage());
        });
        return ResponseEntity.badRequest().body(errors);
    }
}
