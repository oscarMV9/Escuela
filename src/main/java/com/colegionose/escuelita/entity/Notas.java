package com.colegionose.escuelita.entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "notas")
public class Notas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_nota;

    @ManyToOne
    @JoinColumn(name = "id_estudiante", nullable = false)
    private Estudiante estudiante;

    @ManyToOne
    @JoinColumn(name = "id_materia", nullable = false)
    private Materias materia;

    private double nota;
    private String comentario;

    public Notas(Long id_nota, Estudiante estudiante, Materias materia, double nota, String comentario) {
        this.id_nota = id_nota;
        this.estudiante = estudiante;
        this.materia = materia;
        this.nota = nota;
        this.comentario = comentario;
    }

    public Notas() {
    }

    public Long getId_nota() {
        return id_nota;
    }

    public void setId_nota(Long id_nota) {
        this.id_nota = id_nota;
    }

    public Estudiante getEstudiante() {
        return estudiante;
    }

    public void setEstudiante(Estudiante estudiante) {
        this.estudiante = estudiante;
    }

    public Materias getMateria() {
        return materia;
    }

    public void setMateria(Materias materia) {
        this.materia = materia;
    }

    public double getNota() {
        return nota;
    }

    public void setNota(double nota) {
        this.nota = nota;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }
}
