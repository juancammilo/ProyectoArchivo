/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.archivos;

import java.util.Map;
import java.util.HashMap;

/**
 *
 * @author Jkammilo
 */
public class Persona {
    
    private int cedula;
    
    private String nombre;
    
    private String apellido;
    
    private String correo;
    
    Map<Integer, Reporte> reportes;

    public Persona(int cedula, String nombre, String apellido, String correo) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.apellido = apellido;
        this.correo = correo;
        reportes = new HashMap<>();
    }

    public int getCedula() {
        return cedula;
    }

    public void setCedula(int cedula) {
        this.cedula = cedula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public Map<Integer, Reporte> getReportes() {
        return reportes;
    }

    public void setReportes(Map<Integer, Reporte> reportes) {
        this.reportes = reportes;
    }
       
}
