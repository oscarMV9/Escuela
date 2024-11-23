package com.colegionose.escuelita.controller;

import com.colegionose.escuelita.entity.Materias;
import com.colegionose.escuelita.services.MateriasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class MateriasController {

    @Autowired
    private MateriasService servicioMaterias;

    @GetMapping("/materias")
    public String ListarMaterias(Model model) {
        List<Materias> materias = servicioMaterias.listaMaterias();
        model.addAttribute("materias", materias);
        return "Materias/listaMaterias";
    }

    @GetMapping("/crearmateria")
    public String FormularioMateria(Model model) {
        model.addAttribute("materias", new Materias());
        return "Materias/formularioMaterias";
    }

    @PostMapping("/guardarmateria")
    public String CrearMateria(@ModelAttribute Materias materia) {
        servicioMaterias.guardarMaterias(materia);
        return "redirect:/materias";
    }

    @PostMapping("/eliminarMateria/{id}")
    public String EliminarMateria(@PathVariable("id") Long id) {
        servicioMaterias.EliminarMaterias(id);
        return "redirect:/materias";
    }

    @GetMapping("/materias/{idMaterias}")
    public String ObtenerProfeMaterias(@PathVariable Long idMaterias, Model model) {
        Materias materia = servicioMaterias.profesorXMaterias(idMaterias)
                .orElse(null);
        if (materia != null) {
            model.addAttribute("materias", materia);
            return "Materias/MateriasProfes";
        } else {
            return "";
        }
    }

}
