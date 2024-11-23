package com.colegionose.escuelita.services;

import com.colegionose.escuelita.ServiceInterfaces.SalonImpl;
import com.colegionose.escuelita.entity.Salon;
import com.colegionose.escuelita.repository.SalonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SalonService implements SalonImpl {

    @Autowired
    private SalonRepository repositorioSalon;

    @Override
    public List<Salon> listaSalon() {
        return repositorioSalon.findAll();
    }

    @Override
    public Salon GuardarSalon(Salon salon) {
        return repositorioSalon.save(salon);
    }

    @Override
    public void eliminarSalon(Long id) {
        repositorioSalon.deleteById(id);
    }

}
