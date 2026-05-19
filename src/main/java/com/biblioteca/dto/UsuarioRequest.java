package com.biblioteca.dto;

import lombok.Data;

@Data
public class UsuarioRequest {
    private String nombre;
    private String correo;
    private String tipoUsuario;

    private String codigoEstudiante;
    private String programa;

    private String codigoProfesor;
    private String facultad;

    private String codigoEmpleado;
    private String turno;
}
