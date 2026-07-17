package org.unisiga.model;

import java.util.ArrayList;
import java.util.List;

public class Asignatura {
    private String codigoAsig;
    private String nombre;
    private int creditos;
    private List<Asignatura> prerrequisitos; // Relación recursiva 
    private List<Grupo> grupos;              // Composición Fuerte
    private List<Evaluacion> evaluaciones;    // Composición Fuerte
    private int contadorEvaluaciones = 1;

    public Asignatura(String codigoAsig, String nombre, int creditos) {
        this.codigoAsig = codigoAsig;
        this.nombre = nombre;
        this.creditos = creditos;
        this.prerrequisitos = new ArrayList<>();
        this.grupos = new ArrayList<>();
        this.evaluaciones = new ArrayList<>();
    }

    // Método para cumplir con el requisito de Prerrequisitos N:M Recursivo
    public void agregarPrerrequisito(Asignatura pre) {
        if (pre != null && !this.prerrequisitos.contains(pre)) {
            this.prerrequisitos.add(pre);
        }
    }

    // COMPOSICIÓN FUERTE: Asignatura fabrica sus propios grupos
    public Grupo crearGrupo(char idGrupo, int cupoMaximo, String horario) {
        Grupo nuevoGrupo = new Grupo(idGrupo, cupoMaximo, horario, this);
        this.grupos.add(nuevoGrupo);
        return nuevoGrupo;
    }

    // COMPOSICIÓN FUERTE: Asignatura fabrica sus propias evaluaciones
    public Evaluacion agregarEvaluacion(String titulo, float ponderacion) {
        Evaluacion nuevaEval = new Evaluacion(contadorEvaluaciones++, titulo, ponderacion, this);
        this.evaluaciones.add(nuevaEval);
        return nuevaEval;
    }

    // Getters y Setters
    public String getCodigoAsig() { return codigoAsig; }
    public String getNombre() { return nombre; }
    public List<Asignatura> getPrerrequisitos() { return prerrequisitos; }
    public List<Grupo> getGrupos() { return grupos; }
    public List<Evaluacion> getEvaluaciones() { return evaluaciones; }
}
