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
@Table(name = "curso")

public class Curso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_curso;

    private String nombre_curso;
    private String año_Escolar;

    @OneToMany(mappedBy = "curso", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Estudiante> estudiantes;

    @ManyToOne
    @JoinColumn(name = "id_salon", nullable = false)
    private Salon salon;

    public Curso(Long id_curso, String nombre_curso, String año_Escolar, Salon salon) {
        this.id_curso = id_curso;
        this.nombre_curso = nombre_curso;
        this.año_Escolar = año_Escolar;
        this.salon = salon;
    }

    public Curso() {
    }

    public String getAño_Escolar() {
        return año_Escolar;
    }

    public void setAño_Escolar(String año_Escolar) {
        this.año_Escolar = año_Escolar;
    }

    public Long getId_curso() {
        return id_curso;
    }

    public void setId_curso(Long id_curso) {
        this.id_curso = id_curso;
    }

    public String getNombre_curso() {
        return nombre_curso;
    }

    public void setNombre_curso(String nombre_curso) {
        this.nombre_curso = nombre_curso;
    }

    public Salon getSalon() {
        return salon;
    }

    public void setSalon(Salon salon) {
        this.salon = salon;
    }
}
