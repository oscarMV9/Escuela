package com.colegionose.escuelita.controller;

import com.colegionose.escuelita.entity.Estudiante;
import com.colegionose.escuelita.repository.CursoRepository;
import com.colegionose.escuelita.services.EstudianteServicie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class EstudiantesController {

    @Autowired
    private EstudianteServicie servicioEstudiante;

    @Autowired
    private CursoRepository repositorioCurso;

    @GetMapping("/estudiantes")
    public String MostrarEstudiantes(Model model) {
        List<Estudiante> estudiante = servicioEstudiante.listarEstudiantes();
        model.addAttribute("estudiante", estudiante);
        return "Estudiantes/ListaEstudiantes";
    }

    @GetMapping("/nuevo")
    public String FormularioEstudiantes(Model model) {
        model.addAttribute("estudiante", new Estudiante());
        model.addAttribute("curso", repositorioCurso.findAll());
        return "Estudiantes/estudiante_formulario";
    }

    @PostMapping("/guardarEstudiante")
    public String crearEstudiante(@ModelAttribute Estudiante estudiante, @RequestParam Long idCurso, Model model) {
        try {
            servicioEstudiante.saveGuardar(estudiante, idCurso);
            return "redirect:/estudiantes";
        } catch (RuntimeException mensaje) {
            model.addAttribute("error",mensaje.getMessage());
            model.addAttribute("curso", repositorioCurso.findAll());
            return "Estudiantes/estudiante_formulario";
        }

    }

    @GetMapping("/editarEstudiante/{id}")
    public String EditarEstudiante(@PathVariable Long id, Model model) {
        Estudiante estudiante = servicioEstudiante.EstudianteById(id).orElseThrow(()-> new IllegalArgumentException("estudiante no encontrado con el id: " + id));
        model.addAttribute("estudiante", estudiante);
        model.addAttribute("curso", repositorioCurso.findAll());
        return "Estudiantes/edicion_estudiante";
    }

    @PostMapping("/actualizarEstudiante/{id}")
    public String actualizarEstudiante(@ModelAttribute Estudiante estudiante, @RequestParam Long idCurso, Model model) {
        try {
            servicioEstudiante.saveGuardar(estudiante, idCurso);
            return "redirect:/estudiantes";
        }catch (RuntimeException error) {
            model.addAttribute("error", "curso no encontrado con ID: " + idCurso);
            model.addAttribute("curso", repositorioCurso.findAll());
            return "Estudiantes/edicion_estudiante";
        }
    }

    @GetMapping("/eliminarEstudiante/{id}")
    public String EliminarEstudiante(@PathVariable("id") Long id) {
        servicioEstudiante.EliminarEstudiante(id);
        return "redirect:/estudiantes";
    }

}
