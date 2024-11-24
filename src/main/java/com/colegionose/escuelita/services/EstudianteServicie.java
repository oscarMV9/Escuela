package com.colegionose.escuelita.services;

import com.colegionose.escuelita.ServiceInterfaces.EstudiantesImpl;
import com.colegionose.escuelita.entity.Curso;
import com.colegionose.escuelita.entity.Estudiante;
import com.colegionose.escuelita.repository.CursoRepository;
import com.colegionose.escuelita.repository.EstudianteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Optional;

@Service
public class EstudianteServicie implements EstudiantesImpl {

    @Autowired
    private EstudianteRepository repositorioEstudiantes;

    @Autowired
    private CursoRepository repositorioCurso;

    @Override
    public List<Estudiante> listarEstudiantes() {
        return repositorioEstudiantes.findAll();
    }

    @Override
    public Optional<Estudiante> EstudianteById(Long id) {
        return repositorioEstudiantes.findById(id);
    }

    @Override
    public Estudiante saveGuardar(Estudiante e, Long id) {
        Optional<Estudiante> estudianteExistente = repositorioEstudiantes.findByIdentificacion(e.getIdentificacion());

        if (estudianteExistente.isPresent()) {
            Estudiante estudianteActual = estudianteExistente.get();
            if (!estudianteActual.getId_estudiante().equals(e.getId_estudiante())) {
                throw new RuntimeException("El número de identificación ya está en uso");
            }

            estudianteActual.setNombre(e.getNombre());
            estudianteActual.setApellido(e.getApellido());
            estudianteActual.setCorreo(e.getCorreo());
            estudianteActual.setCurso(obtenerCursoId(id));
            return repositorioEstudiantes.save(estudianteActual);
        }

        e.setCurso(obtenerCursoId(id));
        return repositorioEstudiantes.save(e);
    }

    private Curso obtenerCursoId(Long id) {
        return repositorioCurso.findById(id)
                .orElseThrow(() -> new RuntimeException("Curso no encontrado con el ID " + id));
    }



    @Override
    public void EliminarEstudiante(Long id) {
        repositorioEstudiantes.deleteById(id);
    }


}
