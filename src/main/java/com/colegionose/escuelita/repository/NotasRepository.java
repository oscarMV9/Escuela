package com.colegionose.escuelita.repository;

import com.colegionose.escuelita.entity.Estudiante;
import com.colegionose.escuelita.entity.Materias;
import com.colegionose.escuelita.entity.Notas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface NotasRepository extends JpaRepository<Notas, Long> {
    List<Notas> findByEstudiante_IdEstudiante(Long idEstudiante);
    Optional<Notas> findByEstudianteAndMateria(Estudiante estudiante, Materias materias);
}
