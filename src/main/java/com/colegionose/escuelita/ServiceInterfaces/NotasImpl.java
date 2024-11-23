package com.colegionose.escuelita.ServiceInterfaces;

import com.colegionose.escuelita.entity.Notas;

public interface NotasImpl {

    Notas GuardarNotas(Long idEstudiante, Long idMateria, double nota, String comentario);

}
