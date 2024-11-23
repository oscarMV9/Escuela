package com.colegionose.escuelita.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
//@AllArgsConstructor
//@NoArgsConstructor
@Table(name = "profesor")
public class Profesor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_profesor;

    private String nombre;

    private String apellido;

    @ManyToOne
    @JoinColumn(name = "especialidad", nullable = false)
    private Materias materia;

    public Profesor(Long id_profesor, String nombre, String apellido, Materias materia) {
        this.id_profesor = id_profesor;
        this.nombre = nombre;
        this.apellido = apellido;
        this.materia = materia;
    }

    public Profesor() {
    }

    public Long getId_profesor() {
        return id_profesor;
    }

    public void setId_profesor(Long id_profesor) {
        this.id_profesor = id_profesor;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public Materias getMateria() {
        return materia;
    }

    public void setMateria(Materias materia) {
        this.materia = materia;
    }
}
