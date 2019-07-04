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
    private int cifra; //Numero del turno
    private int tipo; //Prioridad del turno 3-2-1

    public Turno(int cifra, int tipo) {
        this.cifra = cifra;
        this.tipo = tipo;
        
    }

    public int getCifra() {
        return cifra;
    }

    public void setCifra(int cifra) {
        this.cifra = cifra;
    }

    /**
     * Devuelve la prioridad
     * @return Prioridad
     */
    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

   @Override
    public String toString() {
        return "El Turno es "  + cifra + "y prioridad " + tipo + "\n" ;
    }
    
    
}
