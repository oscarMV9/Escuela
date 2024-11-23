package com.colegionose.escuelita.ServiceInterfaces;

import com.colegionose.escuelita.entity.Profesor;

import java.util.List;
import java.util.Optional;

public interface ProfesorImpl {

    List<Profesor> listaProfesor();

    Optional<Profesor> ObtenerProfeXId(Long id);

    Profesor guardarProfesor(Profesor profesor);

    void eliminarProfe(Long id);

}
