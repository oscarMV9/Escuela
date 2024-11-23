package com.colegionose.escuelita.ServiceInterfaces;

import com.colegionose.escuelita.entity.Materias;

import java.util.List;

public interface MateriasImpl {

    List<Materias> listaMaterias();

    Materias guardarMaterias(Materias materias);

    public void EliminarMaterias(Long id);

}
