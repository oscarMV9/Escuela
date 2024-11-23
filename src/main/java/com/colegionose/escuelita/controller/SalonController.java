package com.colegionose.escuelita.controller;

import com.colegionose.escuelita.entity.Salon;
import com.colegionose.escuelita.services.SalonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class SalonController {

    @Autowired
    private SalonService servicioSalon;

    @GetMapping("/salon")
    public String listarSalones(Model model) {
        List<Salon> salon = servicioSalon.listaSalon();
        model.addAttribute("salones", salon);
        return "Salon/listar_salones";
    }

    @GetMapping("/nuevo_salon")
    public String Formulario_nuevoSalon(Model model) {
        model.addAttribute("salon", new Salon());
        return "Salon/FormularioNuevo";
    }

    @PostMapping("/guardarSalon")
    public String crearSalon(@ModelAttribute Salon salon, Model model) {
        servicioSalon.GuardarSalon(salon);
        return "redirect:/salon";
    }

    @GetMapping("/eliminarSalon/{id}")
    public String EliminarSalon(@PathVariable("id")Long id) {
        servicioSalon.eliminarSalon(id);
        return "redirect:/salon";
    }

}
