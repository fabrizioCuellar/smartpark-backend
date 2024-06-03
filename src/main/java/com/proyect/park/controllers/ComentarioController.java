package com.proyect.park.controllers;

import com.proyect.park.models.dto.ComentarioDto;
import com.proyect.park.models.request.ComentarioRequest;
import com.proyect.park.services.ComentarioServicio;
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
@RequestMapping("/api/v1/comentario")
@CrossOrigin(origins = "*")
public class ComentarioController {

    @Autowired
    private ComentarioServicio comentarioService;

    @GetMapping("/todos")
    public List<ComentarioDto> obtener() {
        return comentarioService.obtener();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> show(@PathVariable Long id) {
        Optional<ComentarioDto> comentarioDtoOptional = comentarioService.encontrar(id);

        if (comentarioDtoOptional.isPresent()) {
            return ResponseEntity.ok(comentarioDtoOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/guardar")
    public ResponseEntity<?> guardar(@Valid @RequestBody ComentarioDto comentario, BindingResult result) {
        if (result.hasErrors()) {
            return validation(result);
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(comentarioService.guardar(comentario));
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<?> update(@Valid @RequestBody ComentarioRequest comentarioRequest, BindingResult result, @PathVariable Long id) {
        if (result.hasErrors()) {
            return validation(result);
        }
        Optional<ComentarioDto> o = comentarioService.actualizar(comentarioRequest, id);

        if (o.isPresent()) {
            return ResponseEntity.status(HttpStatus.CREATED).body(o.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> remove(@PathVariable Long id) {
        Optional<ComentarioDto> o = comentarioService.encontrar(id);

        if (o.isPresent()) {
            comentarioService.eliminar(id);
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
