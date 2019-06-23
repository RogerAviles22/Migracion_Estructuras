/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.util.ArrayList;

/**
 *
 * @author Rogencio
 */
public class Atencion {
    private int prioridad; //Debe ser prioridad 3(Discapacidad) 2(3erEdad) 1(Normal) y asi ordenarlos
    private ArrayList<Turno> turno;
    
    public Atencion() {
        generarTurnos();
    }

    
    /**
     * Crea los turnos desde el 0-100 en un puesto A
     */
    private void generarTurnos() {
        turno = new ArrayList<>();
        int puesto=1;
        for (int contador = 1; contador < 101; contador++){
            if(puesto<=10){
                Turno t = new Turno("A", contador, puesto);   
                turno.add(t);                
            }else{    
                puesto=1;
                Turno t = new Turno("A", contador, puesto);
                turno.add(t);
            }
            ++puesto;
        } 
                      
    }
    
    
    
    public static void main(String[] args) {
        Atencion a = new Atencion();
        System.out.println(a.getTurno());
    }

    public int getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(int prioridad) {
        this.prioridad = prioridad;
    }

    public ArrayList<Turno> getTurno() {
        return turno;
    }

    public void setTurno(ArrayList<Turno> turno) {
        this.turno = turno;
    }

    

    @Override
    public String toString() {
        return "Atencion{" + "tipoPersona=" + prioridad + ", turno=" + turno + '}';
    }

    
    
    
    
    
}
