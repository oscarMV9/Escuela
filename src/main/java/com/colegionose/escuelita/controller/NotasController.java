package com.colegionose.escuelita.controller;

import com.colegionose.escuelita.entity.Estudiante;
import com.colegionose.escuelita.entity.Notas;
import com.colegionose.escuelita.repository.EstudianteRepository;
import com.colegionose.escuelita.repository.MateriasRepository;
import com.colegionose.escuelita.services.NotasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/notas")
public class NotasController {

    @Autowired
    private NotasService servicioNotas;

    @Autowired
    private EstudianteRepository repositorioEstudiante;

    @Autowired
    private MateriasRepository repositorioMaterias;


    @GetMapping("/estudiante/{id}")
    public String ListarNotasXEstudiante(@PathVariable Long id, Model model) {
        Estudiante estudiante = repositorioEstudiante.findById(id).orElse(null);

        if (estudiante == null) {
            model.addAttribute("error", "el estudiante no existe");
            return "error";
        }

        List<Notas> notas = servicioNotas.notasXEstudiantes(id);
        model.addAttribute("estudiante", estudiante);
        model.addAttribute("notas", notas);

        return "notas/notasEstudiante";
    }

    @GetMapping("/crearNotas")
    public String FormularioNota(Model model) {
        model.addAttribute("notas", new Notas());
        model.addAttribute("estudiantes", repositorioEstudiante.findAll());
        model.addAttribute("materias", repositorioMaterias.findAll());
        return "notas/formularioNotas";
    }

    @PostMapping("/guardarNota")
    public String guardarNota(@RequestParam("idEstudiante") Long idEstudiante,
                              @RequestParam("idMateria") Long idMateria,
                              @RequestParam("nota") double nota,
                              @RequestParam("comentario") String comentario,
                              RedirectAttributes redirectAttributes) {
        try {
            servicioNotas.GuardarNotas(idEstudiante, idMateria, nota, comentario);
            redirectAttributes.addFlashAttribute("success", "Nota creada");
        } catch (IllegalStateException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "error al guardar");
        }
        return "redirect:/notas/crearNotas";
    }

    @GetMapping("/editarNota/{id}")
    public String mostrarFormularioEdicion(@PathVariable("id") Long id, Model model) {
        Notas nota = servicioNotas.buscarPorId(id);
        if (nota == null) {
            return "";
        }
        model.addAttribute("notas", nota);
        model.addAttribute("estudiantes", repositorioEstudiante.findAll());
        model.addAttribute("materias", repositorioMaterias.findAll());
        return "notas/formularioNotasedit";
    }

    @PostMapping("/editarnota/{id}")
    public String actualizarNota(@PathVariable("id") Long id, @ModelAttribute Notas notas) {
        Notas notaExistente = servicioNotas.buscarPorId(id);
        if (notaExistente == null) {
            return "redirect:";
        }

        notaExistente.setEstudiante(notas.getEstudiante());
        notaExistente.setMateria(notas.getMateria());
        notaExistente.setNota(notas.getNota());
        notaExistente.setComentario(notas.getComentario());

        servicioNotas.GuardarNotas(notaExistente);
        return "redirect:/notas/estudiante/{id}";
    }
}
