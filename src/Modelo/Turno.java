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
public class Turno {
    private String modulo;
    private int turno;
    private int puesto;

    public Turno(String modulo, int turno, int puesto) {
        this.modulo = modulo;
        this.turno = turno;
        this.puesto=puesto;
    }

    

    

    @Override
    public String toString() {
        return "Modulo: "+modulo+" turno: "+turno+" puesto: "+puesto+ "\n";
    }

    public String getModulo() {
        return modulo;
    }

    public void setModulo(String modulo) {
        this.modulo = modulo;
    }

    public int getTurno() {
        return turno;
    }

    public void setTurno(int turno) {
        this.turno = turno;
    }

    public int getPuesto() {
        return puesto;
    }

    public void setPuesto(int puesto) {
        this.puesto = puesto;
    }

    
    

    

}
