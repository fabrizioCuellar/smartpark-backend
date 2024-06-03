package com.proyect.park.repositories;

import com.proyect.park.models.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository  extends JpaRepository<Usuario,Long> {
}
