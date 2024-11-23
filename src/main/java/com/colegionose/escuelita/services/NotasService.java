package com.colegionose.escuelita.services;

import com.colegionose.escuelita.ServiceInterfaces.NotasImpl;
import com.colegionose.escuelita.entity.Estudiante;
import com.colegionose.escuelita.entity.Materias;
import com.colegionose.escuelita.entity.Notas;
import com.colegionose.escuelita.repository.EstudianteRepository;
import com.colegionose.escuelita.repository.MateriasRepository;
import com.colegionose.escuelita.repository.NotasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NotasService implements NotasImpl {

    @Autowired
    private NotasRepository repositorioNotas;

    @Autowired
    private EstudianteRepository repositorioEstudiante;

    @Autowired
    private MateriasRepository repositorioMaterias;

    public List<Notas> notasXEstudiantes(Long idEstudiante) {
        return repositorioNotas.findByEstudiante_IdEstudiante(idEstudiante);
    }

    @Override
    public Notas GuardarNotas(Long idEstudiante, Long idMateria, double nota, String comentario) {
        Estudiante estudiante = repositorioEstudiante.findById(idEstudiante)
                .orElseThrow(()-> new IllegalArgumentException("el estudiante con ID" + idEstudiante +"no existe"));

        Materias materias = repositorioMaterias.findById(idMateria)
                .orElseThrow(()-> new IllegalArgumentException("la materia con ID" + idMateria +"no existe"));

        Optional<Notas> notaExistente = repositorioNotas.findByEstudianteAndMateria(estudiante, materias);
        if (notaExistente.isPresent()) {
            throw new IllegalStateException("El estudiante ya tiene una nota registrada para esta materia.");
        }

        Notas nuevaNota = new Notas();
        nuevaNota.setEstudiante(estudiante);
        nuevaNota.setMateria(materias);
        nuevaNota.setNota(nota);
        nuevaNota.setComentario(comentario);

        return repositorioNotas.save(nuevaNota);
    }
    
    public Notas buscarPorId(Long id) {
        return repositorioNotas.findById(id).orElse(null);
    }

    public void GuardarNotas(Notas notaExistente) {
    }
}
