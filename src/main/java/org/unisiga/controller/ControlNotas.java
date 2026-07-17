package org.unisiga.controller;

import org.unisiga.model.*;

public class ControlNotas {
    private InscripcionController datosGlobales;

    public ControlNotas(InscripcionController datosGlobales) {
        this.datosGlobales = datosGlobales;
    }

    /**
     * Resuelve el TODO del método registrarNota() validando rangos [1.0, 7.0]
     */
    public String procesarRegistroNota(Matricula inscripcion, Evaluacion evaluacion, float valorNota) {
        if (inscripcion == null || evaluacion == null) {
            return "Error: Inscripción o Evaluación no especificadas.";
        }

        // 1. Regla de negocio: Rango de notas exigido [1.0, 7.0]
        if (valorNota < 1.0f || valorNota > 7.0f) {
            return "Error: El rango de la calificación debe ser estrictamente entre 1.0 y 7.0.";
        }

        // 2. Regla de negocio: Validar correspondencia de la evaluación con la asignatura
        if (!evaluacion.getAsignatura().equals(inscripcion.getSeccion().getAsignatura())) {
            return "Error: La evaluación seleccionada no pertenece a la asignatura del grupo.";
        }

        // 3. Estructura de tres vías: buscar si ya existe para actualizar o crear una nueva
        for (Calificacion c : inscripcion.getCalificaciones()) {
            if (c.getEvaluacion().getId() == evaluacion.getId()) {
                c.setNota(valorNota);
                return "Éxito: Nota actualizada correctamente.";
            }
        }

        // Si no existía, se crea la estructura enlazando las vías
        Calificacion nuevaCalificacion = new Calificacion(valorNota, inscripcion, evaluacion);
        inscripcion.getCalificaciones().add(nuevaCalificacion);
        evaluacion.getCalificaciones().add(nuevaCalificacion);

        return "Éxito: Nota registrada con éxito.";
    }
}
