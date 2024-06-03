package com.proyect.park.controllers;

import com.proyect.park.models.dto.EstacionamientoDto;
import com.proyect.park.models.request.EstacionamientoRequest;
import com.proyect.park.services.EstacionamientoService;
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
@RequestMapping("/api/v1/estacionamiento")
@CrossOrigin(origins = "*")
public class EstacionamientoController {

    @Autowired
    private EstacionamientoService estacionamientoService;

    @GetMapping("/todos")
    public List<EstacionamientoDto> obtener() {
        return estacionamientoService.obtener();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> show(@PathVariable Long id) {
        Optional<EstacionamientoDto> estacionamientoDtoOptional = estacionamientoService.encontrar(id);

        if (estacionamientoDtoOptional.isPresent()) {
            return ResponseEntity.ok(estacionamientoDtoOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/guardar")
    public ResponseEntity<?> guardar(@Valid @RequestBody EstacionamientoDto reserva, BindingResult result) {
        if (result.hasErrors()) {
            return validation(result);
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(estacionamientoService.guardar(reserva));
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<?> update(@Valid @RequestBody EstacionamientoRequest estacionamientoRequest, BindingResult result, @PathVariable Long id) {
        if (result.hasErrors()) {
            return validation(result);
        }
        Optional<EstacionamientoDto> o = estacionamientoService.actualizar(estacionamientoRequest, id);

        if (o.isPresent()) {
            return ResponseEntity.status(HttpStatus.CREATED).body(o.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> remove(@PathVariable Long id) {
        Optional<EstacionamientoDto> o = estacionamientoService.encontrar(id);

        if (o.isPresent()) {
            estacionamientoService.eliminar(id);
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
