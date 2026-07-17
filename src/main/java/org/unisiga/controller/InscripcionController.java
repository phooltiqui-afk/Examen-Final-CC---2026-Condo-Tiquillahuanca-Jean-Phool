package org.unisiga.controller;

import java.util.ArrayList;
import java.util.List;
import org.unisiga.model.*;

public class InscripcionController {
    private List<Estudiante> estudiantes = new ArrayList<>();
    private List<Academico> academicos = new ArrayList<>();
    private List<Departamento> departamentos = new ArrayList<>();
    private List<Asignatura> asignaturas = new ArrayList<>();

    public InscripcionController() {
        inicializarDatosDemo();
    }

    private void inicializarDatosDemo() {
        // 1. Crear Departamentos
        Departamento deptoCS = new Departamento("DEP-CS", "Computación");
        departamentos.add(deptoCS);

        // 2. Crear Académicos usando tu constructor exacto (rut, nombre, correo, idEmpleado, tipoContrato)
        Academico prof = new Academico("11111111-1", "Dr. Alan Turing", "aturing@unisiga.edu", "EM001", "Principal");
        // Bidireccionalidad segura solicitada por el TODO de Departamento
        prof.setDepartamento(deptoCS);
        deptoCS.getAcademicos().add(prof);
        academicos.add(prof);

        // 3. Crear Estudiantes usando tu constructor exacto (rut, nombre, correo, matricula, anioIngreso, promedioPpa)
        estudiantes.add(new Estudiante("22222222-2", "Jean Phool", "jphool@unisiga.edu", "20261001", 2026, 6.2f));
        estudiantes.add(new Estudiante("33333333-3", "Ana Gomez", "agomez@unisiga.edu", "20261002", 2025, 5.8f));

        // Note: Asignatura fábrica de grupos y evaluaciones (Composición) se gestionará dinámicamente o simulada aquí.
    }

    public void registrarEstudiante(String rut, String nombre, String correo, String matricula, int anio, float ppa) {
        estudiantes.add(new Estudiante(rut, nombre, correo, matricula, anio, ppa));
    }

    public void registrarAcademico(String rut, String nombre, String correo, String idEmp, String contrato, Departamento depto) {
        Academico ac = new Academico(rut, nombre, correo, idEmp, contrato);
        ac.setDepartamento(depto);
        depto.getAcademicos().add(ac);
        academicos.add(ac);
    }

    public List<Estudiante> getEstudiantes() { return estudiantes; }
    public List<Academico> getAcademicos() { return academicos; }
    public List<Departamento> getDepartamentos() { return departamentos; }
    public List<Asignatura> getAsignaturas() { return asignaturas; }
}