package com.colegionose.escuelita.services;

import com.colegionose.escuelita.ServiceInterfaces.MateriasImpl;
import com.colegionose.escuelita.entity.Materias;
import com.colegionose.escuelita.repository.MateriasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MateriasService implements MateriasImpl {

    @Autowired
    private MateriasRepository repositorioMaterias;

    @Override
    public List<Materias> listaMaterias(){
        return repositorioMaterias.findAll();
    }

    public Optional<Materias> profesorXMaterias(Long idMaterias) {
        return repositorioMaterias.findById(idMaterias);
    }

    @Override
    public Materias guardarMaterias(Materias materias) {
        return repositorioMaterias.save(materias);
    }

    @Override
    public void EliminarMaterias(Long id){
        repositorioMaterias.deleteById(id);
    }

}
