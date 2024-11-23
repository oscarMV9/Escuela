package com.colegionose.escuelita.controller;

import com.colegionose.escuelita.entity.Materias;
import com.colegionose.escuelita.entity.Profesor;
import com.colegionose.escuelita.services.MateriasService;
import com.colegionose.escuelita.services.PorfesorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class ProfesorController {

    @Autowired
    private PorfesorService servicioProfesor;
    @Autowired
    private MateriasService materiasService;

    @GetMapping("/profesores")
    public String listaProfes(Model model) {
        model.addAttribute("profesores", servicioProfesor.listaProfesor());
        return "Profesores/listaProfesores";
    }

    @GetMapping("/crearprofe")
    public String FormularioProfe(Model model) {
        model.addAttribute("profesor", new Profesor());
        model.addAttribute("materias", materiasService.listaMaterias());
        return "Profesores/FormularioProfe";
    }

    @PostMapping("/guardarProfe")
    public String guardarProfesor(@ModelAttribute Profesor profesor) {
        servicioProfesor.guardarProfesor(profesor);
        return "redirect:/profesores";
    }

    @GetMapping("/editarProfesor/{id}")
    public String formularioEdicion(@PathVariable Long id, Model model) {
        Optional<Profesor> profesorOpt = servicioProfesor.ObtenerProfeXId(id);
        if (profesorOpt.isPresent()) {
            model.addAttribute("profesor", profesorOpt.get());
            model.addAttribute("materias", materiasService.listaMaterias());
            return "Profesores/edicion_profesor";
        } else {
            return "redirect:/profesores";
        }
    }

    @GetMapping("/eliminarprofesor/{id}")
    public String EliminarProfesor(@PathVariable("id") Long id) {
        servicioProfesor.eliminarProfe(id);
        return "redirect:/profesores";
    }

}
