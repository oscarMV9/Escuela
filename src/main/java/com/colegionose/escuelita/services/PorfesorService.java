package com.colegionose.escuelita.services;

import com.colegionose.escuelita.ServiceInterfaces.ProfesorImpl;
import com.colegionose.escuelita.entity.Materias;
import com.colegionose.escuelita.entity.Profesor;
import com.colegionose.escuelita.repository.MateriasRepository;
import com.colegionose.escuelita.repository.ProfesorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PorfesorService implements ProfesorImpl {

    @Autowired
    private ProfesorRepository repositorioProfesor;

    @Autowired
    private MateriasRepository repositorioMaterias;
    @Autowired
    private ProfesorRepository profesorRepository;

    @Override
    public List<Profesor>listaProfesor() {
        return profesorRepository.findAll();
    }

    @Override
    public Optional<Profesor> ObtenerProfeXId(Long id) {
        return repositorioProfesor.findById(id);
    }

    @Override
    public Profesor guardarProfesor(Profesor profesor) {
        Materias materia = repositorioMaterias.findById(profesor.getMateria().getId_materia())
                .orElseThrow(() -> new RuntimeException("Materia no encontrada"));
        profesor.setMateria(materia);
        return repositorioProfesor.save(profesor);
    }

    @Override
    public void eliminarProfe(Long id) {
        repositorioProfesor.deleteById(id);
    }


}
