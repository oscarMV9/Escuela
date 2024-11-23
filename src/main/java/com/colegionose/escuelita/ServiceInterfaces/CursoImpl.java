package com.colegionose.escuelita.ServiceInterfaces;

import com.colegionose.escuelita.entity.Curso;

import java.util.List;

public interface CursoImpl {

    public List<Curso> listaCursos();

    Curso GuardarCurso(Curso curso, Long id);

}
