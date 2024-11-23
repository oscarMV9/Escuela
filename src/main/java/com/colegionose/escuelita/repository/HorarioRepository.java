package com.colegionose.escuelita.repository;

import com.colegionose.escuelita.entity.Horarios;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HorarioRepository extends JpaRepository<Horarios, Long> {
}
