/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import Controlador.VentanaEmergente;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Map;
import java.util.PriorityQueue;

/**
 *
 * @author Rogencio
 */
public class Atencion {
    public static  Map<Integer,Turno> enAtencion= new HashMap<>(); //Debe ser prioridad 3(Discapacidad) 2(3erEdad) 1(Normal) y asi ordenarlos
    public static PriorityQueue<Turno> enEspera;
    public static LinkedList<Integer> puestos = new LinkedList<>();
    public static int modulo=1;
    public static int turnoNormal=0;
    public static int turnoDiscapacidad=0;
    public static int turno3erEdad=0;

    public Atencion() {
        enEspera = new PriorityQueue<>((Turno t1, Turno t2)-> t2.getTipo()-t1.getTipo());
        System.out.println(enEspera);
    }
    
    
    /**
     * Rellena el PriorityQueue con los turnos que no
     * estan siendo Atendidos. 
     * Si no hay puestos creados, no agrega turnos a la lista
     * Esta funcion deberia estar en el paneTurnos
     * @param t Turno
     * @return True si hay puestos creados y los agrega a enEspera
     */
    public boolean cargarEnEspera(Turno t){
        if(!puestos.isEmpty()){
            enEspera.add(t);
            return true;
        }else{
            VentanaEmergente.noHayPuestos();
            return false;
        }            
    }
    
    /**
     * Crea un Puesto y lo anuncia en el PaneMenuPrincipal
     * @return True si el modulo agregado es menor a 5
     */
    public static boolean cargarPuesto(){
        if(modulo<8){ 
            enAtencion.put(modulo, null);
            puestos.add(modulo);
            modulo++;
            return true;
        }
        return false;
    }
    
    /**
     * Carga en el Map enAtencion cuando un puesto no tiene Turnos
     * Esta funcion debe estar en el PaneTurnos
     * @return False cuando no hay Turnos
     */
    public boolean cargarEnAtencion(){
        Turno escogido =enEspera.poll(); //Escoge la primera cola de la lista
        if(escogido==null)
            return false;
        else {
            if (!enAtencion.isEmpty()) {
                for (Map.Entry<Integer, Turno> entry : enAtencion.entrySet()) {
                    if (entry.getValue() == null) { //Recibe el valor del Turno, es null cuando Puesto no tiene Turnos
                        entry.setValue(escogido);
                        return true;
                    }
                }
            }
        }
        return false;
    }
    
    /**
     * Elimina el puesto que el Admin escriba, si hay un puesto con Turno, este no
     * puede ser eliminaod
     * @param n Numero del puesto a borrar
     * @return 
     */
    public static boolean eliminarPuesto(int n){
        if(n>0){
            for (Map.Entry<Integer, Turno> entry : enAtencion.entrySet()){
                if (entry.getKey() == n && entry.getValue() == null){
                    enAtencion.remove(entry.getKey());
                }
            }
            ListIterator<Integer> borrado = puestos.listIterator();
            while(borrado.hasNext()){
                if(borrado.next().equals(n))
                    puestos.remove(n);
            }                
            return true;
        }
        return false;
    }

    public PriorityQueue<Turno> getEnEspera() {
        return enEspera;
    }
    
    
}
