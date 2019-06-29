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
public class Puesto {
    public int numero;

    public Puesto() {
        this.numero = 0;
    }

    public int getNumero() {
        return numero;
    }
    
    /**
     * Método que servirá para aumentar los puestos para agg turnos
     * @return True si no agregamos más de 5 puestos
     */
    public boolean aumentarPuestos(){
        if(numero<5){
            ++numero;
            return true;
        }
        return false;            
    }
    
    
    /*public boolean eliminarPuestos(){
        if(numero>0){
            --numero;;
            return true;
        }
        return false;         
    }
    */
    
    
    
    
}
