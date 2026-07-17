package org.unisiga.main;

import org.unisiga.controller.*;
import org.unisiga.model.*;
import org.unisiga.view.ConsoleView;

public class Main {
    public static void main(String[] args) {
        ConsoleView vista = new ConsoleView();
        
        // Inicializamos nuestro motor de datos e inyectamos los controladores correspondientes
        InscripcionController controller = new InscripcionController();
        ControlMatricula ctrlMatricula = new ControlMatricula(controller);
        ControlNotas ctrlNotas = new ControlNotas(controller);

        vista.desplegarMenu();

        // -------------------------------------------------------------------------
        // 1. TODO: Sembrar catálogo de asignaturas en el sistema
        // -------------------------------------------------------------------------
        Asignatura progBasica = new Asignatura("INF-11", "Programación Básica", 4);
        Asignatura poo = new Asignatura("INF-12", "Programación Orientada a Objetos", 5);
        Asignatura algebra = new Asignatura("MAT-01", "Álgebra Lineal", 4);
        
        controller.getAsignaturas().add(progBasica);
        controller.getAsignaturas().add(poo);
        controller.getAsignaturas().add(algebra);

        // -------------------------------------------------------------------------
        // 2. TODO: Definir reglas recursivas (POO requiere Programación Básica)
        // -------------------------------------------------------------------------
        poo.agregarPrerrequisito(progBasica);

        // -------------------------------------------------------------------------
        // 3. TODO: Instanciar secciones y evaluaciones a través de Composición Fuerte
        // -------------------------------------------------------------------------
        // La asignatura fabrica el grupo de forma interna
        Grupo grupoPOO_A = poo.crearGrupo('A', 25, "Lunes y Miércoles 08:30 - 10:00");
        
        // Asociar un docente de forma segura (recuperado de los demos del controlador)
        if (!controller.getAcademicos().isEmpty()) {
            Academico tutor = controller.getAcademicos().get(0);
            grupoPOO_A.asignarDocente(tutor); // Si tienes implementada la lógica bidireccional
        }
        
        // La asignatura fabrica sus hitos evaluativos internos
        Evaluacion certamen1 = poo.agregarEvaluacion("Certamen 1", 0.40f);
        Evaluacion proyectoFinal = poo.agregarEvaluacion("Proyecto Final", 0.60f);

        // -------------------------------------------------------------------------
        // 4. TODO: Crear estudiantes de prueba
        // -------------------------------------------------------------------------
        // Juan: cumple con requisitos (en un sistema real validarías su historial)
        controller.registrarEstudiante("11111111-K", "Juan Requisito OK", "juan@unisiga.edu", "20260001", 2026, 6.0f);
        // María: No cumple (la usaremos para forzar o validar flujos)
        controller.registrarEstudiante("22222222-K", "María Sin Requisito", "maria@unisiga.edu", "20260002", 2026, 4.2f);

        Estudiante juan = controller.getEstudiantes().get(controller.getEstudiantes().size() - 2);
        Estudiante maria = controller.getEstudiantes().get(controller.getEstudiantes().size() - 1);

        // -------------------------------------------------------------------------
        // 5. TODO: Ejecutar casos de uso en el controlador y validar salidas
        // -------------------------------------------------------------------------
        System.out.println("\n--- [EJECUTANDO CASO DE USO: INSCRIPCIÓN] ---");
        String resMatriculaJuan = ctrlMatricula.procesarInscripcion(juan, grupoPOO_A);
        System.out.println("Resultado Juan: " + resMatriculaJuan);

        System.out.println("\n--- [EJECUTANDO CASO DE USO: CALIFICACIONES (TRES VÍAS)] ---");
        if (!juan.getInscripciones().isEmpty()) {
            Matricula inscripcionJuan = juan.getInscripciones().get(0);
            
            // Registrar una nota válida (Rango 1.0 - 7.0)
            String resNota1 = ctrlNotas.procesarRegistroNota(inscripcionJuan, certamen1, 6.5f);
            System.out.println("Registro Certamen 1: " + resNota1);
            
            // Probar validación de error con nota fuera de rango
            String resNotaErr = ctrlNotas.procesarRegistroNota(inscripcionJuan, proyectoFinal, 8.5f);
            System.out.println("Registro Inválido (Nota 8.5): " + resNotaErr);
        }

        System.out.println("\n[SISTEMA] El esqueleto está compilado y la lógica de negocio ha sido sembrada con éxito.");
    }
}
