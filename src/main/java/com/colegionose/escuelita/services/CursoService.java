package com.colegionose.escuelita.services;

import com.colegionose.escuelita.ServiceInterfaces.CursoImpl;
import com.colegionose.escuelita.entity.Curso;
import com.colegionose.escuelita.entity.Salon;
import com.colegionose.escuelita.repository.CursoRepository;
import com.colegionose.escuelita.repository.SalonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CursoService implements CursoImpl {

    @Autowired
    private CursoRepository repositorioCurso;

    @Autowired
    private SalonRepository repositorioSalon;

    @Override
    public List<Curso> listaCursos() {
        return repositorioCurso.findAll();
    }

    public Optional<Curso> ObtenerCursoXEstudiantes(Long idCurso) {
        return repositorioCurso.findById(idCurso);
    }

    @Override
    public Curso GuardarCurso(Curso curso, Long id) {
        Optional<Curso> cursoExistente = repositorioCurso.findBySalon(curso.getSalon());
        if (cursoExistente.isPresent()) {
            throw new RuntimeException("Ese salón ya está ocupado");
        }

        Optional<Salon> salonOptional = repositorioSalon.findById(id);
        if (salonOptional.isPresent()) {
            curso.setSalon(salonOptional.get());
        } else {
            throw new RuntimeException("Salón no encontrado con id: " + id);
        }
        return repositorioCurso.save(curso);
    }


}
