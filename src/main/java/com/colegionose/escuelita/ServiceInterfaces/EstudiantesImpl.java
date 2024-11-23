package com.colegionose.escuelita.ServiceInterfaces;


import com.colegionose.escuelita.entity.Estudiante;

import java.util.List;
import java.util.Optional;

public interface EstudiantesImpl {

    List<Estudiante> listarEstudiantes();

    public Optional<Estudiante> EstudianteById(Long id);

    Estudiante saveGuardar(Estudiante e, Long id);

    public void EliminarEstudiante(Long id);

}
