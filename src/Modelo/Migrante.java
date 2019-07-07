/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.util.LinkedList;
import java.util.Objects;
import java.util.Set;

/**
 *
 * @author Rogencio
 */
public class Migrante extends Persona{
    private Nacionalidad nacionalidad;
    private String fechaNacimiento;
    private String tipo;

    

    public Migrante(String cedula, String nombre, String apellido, String sexo, Nacionalidad nacionalidad, String fechaNacimiento, String tipo) {
        super(cedula, nombre, apellido, sexo,tipo);
        this.nacionalidad = nacionalidad;
        this.fechaNacimiento = fechaNacimiento;
        this.tipo=tipo;
    }

    public Nacionalidad getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(Nacionalidad nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    @Override
    public String toString() {
        return "Migrante: " +getNombre()+" "+getApellido()+" "  +nacionalidad + ", fechaNacimiento=" + fechaNacimiento ;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    
    /*public static void main(String[] args) {
        Nacionalidad n = new Nacionalidad("Ecuador", "America del Sur", "Guayas", "Guayaquil");
        Migrante m = new Migrante("054", "Roger", "Aviles", "F", n, "20/15/2804");
        System.out.println(m);
    }*/

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.nacionalidad);
        hash = 97 * hash + Objects.hashCode(this.fechaNacimiento);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        Migrante migrante=(Migrante)obj;
        if(migrante.getNombre().equals(this.getCedula()) && migrante.getApellido().equals(this.getNombre())){
            return true;
        }else{
            return false;
        }
    }
    public boolean comprobarExistencia(Migrante migrante){
        if(migrante==null) return false;
        if(this.getCedula().equals(migrante.getCedula())) return true;
        return false;
    }
}
