package com.colegionose.escuelita.repository;

import com.colegionose.escuelita.entity.Estudiante;
import com.colegionose.escuelita.entity.Materias;
import com.colegionose.escuelita.entity.Notas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MateriasRepository extends JpaRepository<Materias, Long> {
}
