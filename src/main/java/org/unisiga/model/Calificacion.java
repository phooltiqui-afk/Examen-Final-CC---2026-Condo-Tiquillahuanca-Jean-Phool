package org.unisiga.model;

/**
 * Estructura de Calificación de Tres Vías. Unifica al Alumno (Inscripción) con el Examen (Evaluación).
 */
public class Calificacion {
    private float nota;
    private Matricula inscripcion; // Vía 1 (Quién rinde en qué sección)
    private Evaluacion evaluacion;   // Vía 2 (Qué examen unificado de cátedra es)

    public Calificacion(float nota, Matricula inscripcion, Evaluacion evaluacion) {
        this.nota = nota;
        this.inscripcion = inscripcion;
        this.evaluacion = evaluacion;
    }

    public float getNota() { return nota; }
    public void setNota(float nota) { this.nota = nota; }
    public Matricula getInscripcion() { return inscripcion; }
    public Evaluacion getEvaluacion() { return evaluacion; }
}
