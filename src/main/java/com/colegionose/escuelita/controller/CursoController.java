package com.colegionose.escuelita.controller;

import com.colegionose.escuelita.entity.Curso;
import com.colegionose.escuelita.repository.SalonRepository;
import com.colegionose.escuelita.services.CursoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


@Controller
public class CursoController {

    @Autowired
    private CursoService servicioCurso;

    @Autowired
    private SalonRepository repositorioSalon;

    @GetMapping("/cursos")
    public String MostrarCursos(Model model) {
        List<Curso> cursos =servicioCurso.listaCursos();
        model.addAttribute("curso", cursos);
        return "Curso/listaCursos";
    }

    @GetMapping("/cursos/{idCurso}")
    public String obtenerEstudiantesPorCurso(@PathVariable Long idCurso, Model model) {
        Curso curso = servicioCurso.ObtenerCursoXEstudiantes(idCurso)
                .orElse(null);

        if (curso != null) {
            model.addAttribute("curso", curso);
            return "Curso/cursoEstudiantes";
        } else {
            return "";
        }
    }

    @GetMapping("/nuevoCurso")
    public String crearCursoForm(Model model) {
        model.addAttribute("curso", new Curso());
        model.addAttribute("salones", repositorioSalon.findAll());
        return "Curso/formulario_curso";
    }

    @PostMapping("/GuardarCurso")
    public String GuardarCurso(Curso curso, @RequestParam Long IdSalon, Model model) {
        try {
            servicioCurso.GuardarCurso(curso, IdSalon);
            return "redirect:/cursos";
        } catch (RuntimeException error) {
            model.addAttribute("error", error.getMessage());
            model.addAttribute("salones", repositorioSalon.findAll());
            return "Curso/formulario_curso";
        }
    }

}
