package org.unisiga.controller;

import org.unisiga.model.Calificacion;
import org.unisiga.model.Evaluacion;
import org.unisiga.model.Matricula;

public class ControlNotas {
    private InscripcionController datosGlobales;

    // Con esto solucionas el error de la línea 5 del Main (el constructor)
    public ControlNotas(InscripcionController datosGlobales) {
        this.datosGlobales = datosGlobales;
    }

    // Con esto solucionas el error de la línea 36 del Main (el método)
    public String procesarRegistroNota(Matricula inscripcion, Evaluacion evaluacion, float valorNota) {
        if (inscripcion == null || evaluacion == null) {
            return "Error: Inscripción o Evaluación no especificadas.";
        }

        // Rango de notas exigido por la pauta [1.0, 7.0]
        if (valorNota < 1.0f || valorNota > 7.0f) {
            return "Error: El rango de la calificación debe ser estrictamente entre 1.0 y 7.0.";
        }

        // Validar correspondencia con la asignatura
        if (!evaluacion.getAsignatura().equals(inscripcion.getSeccion().getAsignatura())) {
            return "Error: La evaluación seleccionada no pertenece a la asignatura del grupo.";
        }

        // Estructura de tres vías: buscar si ya existe para actualizar o crear una nueva
        for (Calificacion c : inscripcion.getCalificaciones()) {
            if (c.getEvaluacion().getId() == evaluacion.getId()) {
                c.setNota(valorNota);
                return "Éxito: Nota actualizada correctamente.";
            }
        }

        // Si no existía, se enlaza en las vías correspondientes
        Calificacion nuevaCalificacion = new Calificacion(valorNota, inscripcion, evaluacion);
        inscripcion.getCalificaciones().add(nuevaCalificacion);
        evaluacion.getCalificaciones().add(nuevaCalificacion);

        return "Éxito: Nota registrada con éxito.";
    }
}
