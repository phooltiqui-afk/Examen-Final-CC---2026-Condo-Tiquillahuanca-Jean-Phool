package org.unisiga.controller;

import org.unisiga.model.*;

public class ControlMatricula {
    private InscripcionController datosGlobales;

    public ControlMatricula(InscripcionController datosGlobales) {
        this.datosGlobales = datosGlobales;
    }

    /**
     * Resuelve el TODO del método inscribirSeccion() respetando validaciones.
     */
    public String procesarInscripcion(Estudiante estudiante, Grupo seccion) {
        if (estudiante == null || seccion == null) {
            return "Error: Datos inválidos para procesar matrícula.";
        }

        // 1. Regla de negocio: Validar cupo máximo
        if (seccion.getInscripciones().size() >= seccion.getCupoMaximo()) {
            return "Error: La sección no cuenta con cupos disponibles.";
        }

        // 2. Regla de negocio: Validar que no esté inscrito previamente
        for (Matricula m : estudiante.getInscripciones()) {
            if (m.getSeccion().getIdGrupo() == seccion.getIdGrupo() && 
                m.getSeccion().getAsignatura().equals(seccion.getAsignatura())) {
                return "Error: El estudiante ya está inscrito en esta asignatura.";
            }
        }

        // 3. Crear la clase de asociación Matricula
        Matricula nuevaMatricula = new Matricula(estudiante, seccion);

        // 4. Asegurar bidireccionalidad exacta
        estudiante.getInscripciones().add(nuevaMatricula);
        seccion.getInscripciones().add(nuevaMatricula);

        return "Éxito: Inscripción realizada correctamente en el Grupo " + seccion.getIdGrupo();
    }
}