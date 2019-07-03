/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

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
    
    
}
