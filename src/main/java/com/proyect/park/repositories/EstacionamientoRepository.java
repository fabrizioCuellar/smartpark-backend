package com.proyect.park.repositories;

import com.proyect.park.models.entities.Estacionamiento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EstacionamientoRepository extends JpaRepository<Estacionamiento,Long> {
}
