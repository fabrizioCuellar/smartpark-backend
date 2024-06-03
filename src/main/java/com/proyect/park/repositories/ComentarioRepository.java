package com.proyect.park.repositories;

import com.proyect.park.models.entities.Comentario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ComentarioRepository extends JpaRepository<Comentario,Long> {
}
