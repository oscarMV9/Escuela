package com.colegionose.escuelita.repository;

import com.colegionose.escuelita.entity.Salon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SalonRepository extends JpaRepository<Salon, Long> {
    Optional<Salon> findByNumeroSalon(String numeroSalon);
}
