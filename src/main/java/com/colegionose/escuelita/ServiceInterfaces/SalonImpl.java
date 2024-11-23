package com.colegionose.escuelita.ServiceInterfaces;

import com.colegionose.escuelita.entity.Salon;

import java.util.List;

public interface SalonImpl {

    List<Salon> listaSalon();

    Salon GuardarSalon(Salon salon);

    public void eliminarSalon(Long id);

}
