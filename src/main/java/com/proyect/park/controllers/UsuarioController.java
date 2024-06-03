package com.proyect.park.controllers;


import com.proyect.park.models.dto.UsuarioDto;
import com.proyect.park.models.entities.Usuario;
import com.proyect.park.models.request.UsuarioRequest;
import com.proyect.park.services.UsuarioService;
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
@RequestMapping("/api/v1/usuario")
@CrossOrigin(origins = "*")
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/todos")
    public List<UsuarioDto> obtener(){
        return  usuarioService.obtenerUsuarios();
    }


    @GetMapping("/{id}")
    public ResponseEntity<?> show(@PathVariable Long id){
        Optional<UsuarioDto> userDtoOptional = usuarioService.encontrarUsuario(id);

        if(userDtoOptional.isPresent()){
            return ResponseEntity.ok(userDtoOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }
    @PostMapping("/guardar")
    public ResponseEntity<?> guardar(@Valid @RequestBody Usuario user, BindingResult result) {
        if (result.hasErrors()) {
            return validation(result);
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.guardarUsuario(user));

    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<?> update(@Valid @RequestBody UsuarioRequest user, BindingResult result, @PathVariable Long id) {
        if(result.hasErrors()){
            return validation(result);
        }
        Optional<UsuarioDto> o = usuarioService.actualizarUsuario(user, id);

        if (o.isPresent()) {
            return ResponseEntity.status(HttpStatus.CREATED).body(o.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> remove(@PathVariable Long id) {
        Optional<UsuarioDto> o = usuarioService.encontrarUsuario(id);

        if(o.isPresent()) {
            usuarioService.eliminarUsuario(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }



    private ResponseEntity<?> validation(BindingResult result){
        Map<String, String> errors = new HashMap<>();
        result.getFieldErrors().forEach(err-> {
            errors.put(err.getField(), "El campo " + err.getField() + " " + err.getDefaultMessage());
        });
        return ResponseEntity.badRequest().body(errors);
    }

}
