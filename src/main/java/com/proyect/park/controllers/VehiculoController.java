package com.proyect.park.controllers;


import com.proyect.park.models.dto.VehiculoDto;
import com.proyect.park.models.request.VehiculoRequest;
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
@RequestMapping("/api/v1/vehiculo")
@CrossOrigin(origins = "*")
public class VehiculoController {

    @Autowired
    private VehiculoService vehiculoService;

    @GetMapping("/todos")
    public List<VehiculoDto> obtener() {
        return vehiculoService.obtener();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> show(@PathVariable Long id) {
        Optional<VehiculoDto> vehiculoDtoOptional = vehiculoService.encontrar(id);

        if (vehiculoDtoOptional.isPresent()) {
            return ResponseEntity.ok(vehiculoDtoOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/guardar")
    public ResponseEntity<?> guardar(@Valid @RequestBody VehiculoDto vehiculo, BindingResult result) {
        if (result.hasErrors()) {
            return validation(result);
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(vehiculoService.guardar(vehiculo));
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<?> update(@Valid @RequestBody VehiculoRequest vehiculoRequest, BindingResult result, @PathVariable Long id) {
        if (result.hasErrors()) {
            return validation(result);
        }
        Optional<VehiculoDto> o = vehiculoService.actualizar(vehiculoRequest, id);

        if (o.isPresent()) {
            return ResponseEntity.status(HttpStatus.CREATED).body(o.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> remove(@PathVariable Long id) {
        Optional<VehiculoDto> o = vehiculoService.encontrar(id);

        if (o.isPresent()) {
            vehiculoService.eliminar(id);
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
