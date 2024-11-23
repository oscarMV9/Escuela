package com.colegionose.escuelita.repository;

import com.colegionose.escuelita.entity.Curso;
import com.colegionose.escuelita.entity.Salon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CursoRepository extends JpaRepository<Curso, Long> {
    Optional<Curso> findBySalon(Salon salon);
}
