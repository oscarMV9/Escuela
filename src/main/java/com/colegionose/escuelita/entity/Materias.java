package com.colegionose.escuelita.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
//@AllArgsConstructor
//@NoArgsConstructor
@Table(name = "materias")
public class Materias {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_materia;

    private String nombre_materia;

    @OneToMany(mappedBy = "materia" , cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Profesor> profesor;

    @OneToMany(mappedBy = "materia", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Notas> notas;

    public Materias(Long id_materia, String nombre_materia, List<Profesor> profesor) {
        this.id_materia = id_materia;
        this.nombre_materia = nombre_materia;
        this.profesor = profesor;
    }

    public Materias() {
    }

    public Long getId_materia() {
        return id_materia;
    }

    public void setId_materia(Long id_materia) {
        this.id_materia = id_materia;
    }

    public String getNombre_materia() {
        return nombre_materia;
    }

    public void setNombre_materia(String nombre_materia) {
        this.nombre_materia = nombre_materia;
    }

    public List<Profesor> getProfesor() {
        return profesor;
    }

    public void setProfesor(List<Profesor> profesor) {
        this.profesor = profesor;
    }
}
