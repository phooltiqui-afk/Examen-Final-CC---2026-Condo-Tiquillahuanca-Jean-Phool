package org.unisiga.main;

import org.unisiga.controller.InscripcionController;
import org.unisiga.controller.ControlMatricula;
import org.unisiga.controller.ControlNotas;
import org.unisiga.model.*;
import org.unisiga.view.ConsoleView;

public class Main {
    public static void main(String[] args) {
        ConsoleView vista = new ConsoleView();
        InscripcionController controller = new InscripcionController();
        ControlMatricula ctrlMatricula = new ControlMatricula(controller);
        ControlNotas ctrlNotas = new ControlNotas(controller);

        vista.desplegarMenu();

        // 1. Sembrar catálogo
        Asignatura progBasica = new Asignatura("INF-11", "Programación Básica", 4);
        Asignatura poo = new Asignatura("INF-12", "Programación Orientada a Objetos", 5);
        controller.getAsignaturas().add(progBasica);
        controller.getAsignaturas().add(poo);

        // 2. Reglas recursivas
        poo.agregarPrerrequisito(progBasica);

        // 3. Composición Fuerte (La asignatura crea el grupo)
        Grupo grupoPOO_A = poo.crearGrupo('A', 25, "Lunes 08:30");
        Evaluacion certamen1 = poo.agregarEvaluacion("Certamen 1", 0.40f);

        // 4. Alumnos de prueba
        controller.registrarEstudiante("11111111-K", "Juan OK", "juan@unisiga.edu", "20260001", 2026, 6.0f);
        Estudiante juan = controller.getEstudiantes().get(0);

        // 5. Probar flujos
        System.out.println("\n--- PROBANDO INSCRIPCIÓN ---");
        String resMatricula = ctrlMatricula.procesarInscripcion(juan, grupoPOO_A);
        System.out.println("Resultado: " + resMatricula);

        System.out.println("\n--- PROBANDO NOTAS (3 VÍAS) ---");
        if (!juan.getInscripciones().isEmpty()) {
            Matricula inscripcionJuan = juan.getInscripciones().get(0);
            String resNota = ctrlNotas.procesarRegistroNota(inscripcionJuan, certamen1, 6.5f);
            System.out.println("Resultado Nota: " + resNota);
        }
    }
}