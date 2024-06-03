package com.proyect.park.controllers;

import com.proyect.park.models.dto.VehiculoDto;
import com.proyect.park.models.dto.ZonaAparcamientoDto;
import com.proyect.park.models.request.VehiculoRequest;
import com.proyect.park.models.request.ZonaAparcamientoRequest;
import com.proyect.park.services.ZonaAparcamientoService;
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
@RequestMapping("/api/v1/zona_aparcamiento")
@CrossOrigin(origins = "*")
public class ZonaAparcamientoController {

    @Autowired
    private ZonaAparcamientoService zonaAparcamientoService;

    @GetMapping("/todos")
    public List<ZonaAparcamientoDto> obtener() {
        return zonaAparcamientoService.obtener();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> show(@PathVariable Long id) {
        Optional<ZonaAparcamientoDto> vehiculoDtoOptional = zonaAparcamientoService.encontrar(id);

        if (vehiculoDtoOptional.isPresent()) {
            return ResponseEntity.ok(vehiculoDtoOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/guardar")
    public ResponseEntity<?> guardar(@Valid @RequestBody ZonaAparcamientoDto vehiculo, BindingResult result) {
        if (result.hasErrors()) {
            return validation(result);
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(zonaAparcamientoService.guardar(vehiculo));
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<?> update(@Valid @RequestBody ZonaAparcamientoRequest zonaAparcamientoRequest, BindingResult result, @PathVariable Long id) {
        if (result.hasErrors()) {
            return validation(result);
        }
        Optional<ZonaAparcamientoDto> zonaAparcamientoDtoOptional = zonaAparcamientoService.actualizar(zonaAparcamientoRequest, id);

        if (zonaAparcamientoDtoOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.CREATED).body(zonaAparcamientoDtoOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> remove(@PathVariable Long id) {
        Optional<ZonaAparcamientoDto> o = zonaAparcamientoService.encontrar(id);

        if (o.isPresent()) {
            zonaAparcamientoService.eliminar(id);
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
