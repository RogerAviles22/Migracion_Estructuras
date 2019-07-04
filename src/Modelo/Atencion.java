/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import Controlador.VentanaEmergente;
import java.util.HashMap;
import java.util.Iterator;
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
    public static PriorityQueue<Turno> enEspera = new PriorityQueue<>((Turno t1, Turno t2)-> t2.getTipo()-t1.getTipo());
    public static LinkedList<Integer> puestos = new LinkedList<>();
    public static int turnoNormal=0;
    public static int turnoDiscapacidad=0;
    public static int turno3erEdad=0;
    
    /**
     * Rellena el PriorityQueue con los turnos que no
     * estan siendo Atendidos. 
     * Si no hay puestos creados, no agrega turnos a la lista o estan los puestos ocupados
     * Esta funcion deberia estar en el paneTurnos
     * @param t Turno
     * @return True si hay puestos creados y los agrega a enEspera
     */
    public boolean cargarEnEspera(Turno t){
        if(!puestos.isEmpty() || cargarEnAtencion()){
            enEspera.offer(t);
            return true; 
        }else{
            VentanaEmergente.noHayPuestos();
            return false;
        }            
    }
    
    /**
     * Crea un Puesto y lo anuncia en el PaneMenuPrincipal
     * @return True si agrega hasta el puesto 6
     */
    public static boolean cargarPuesto(){
        if(puestos.size()<6){
            Turno escogido =enEspera.poll();
            if(puestos.isEmpty()){ //Caso en el que no haya puestos Creados 
                int i=1;
                enAtencion.put(i, null);
                puestos.add(i);
                return true;
            }            
            else if(!puestos.isEmpty() && escogido!=null){ //Caso en el que ya exista puesto creados                  
                puestos.add(puestos.getLast()+1);
                enAtencion.put(puestos.getLast()+1, null);
                for (Map.Entry<Integer, Turno> entry : enAtencion.entrySet()) {
                    if (entry.getValue() == null) { //Recibe el valor del Turno, es null cuando Puesto no tiene Turnos
                        entry.setValue(escogido); //Agrega la primera cola en Espera
                        System.out.println("Mapas en cargar : "+ enAtencion);
                        return true;
                    }
                }
                enEspera.offer(escogido); //agrega en caso de que no exista
            }
            else{
            enAtencion.put(puestos.getLast()+1, null);
            puestos.add(puestos.getLast()+1);     
            return true;
            }            
            
        }
        return false;
    }
    
    /**
     * Carga en el Map enAtencion cuando un puesto no tiene Turnos
     * Esta funcion debe estar en el PaneTurnos
     * @return False cuando no hay Turnos o los puestos estan llenos
     */
    public static boolean cargarEnAtencion(){
        Turno escogido =enEspera.poll(); //Escoge la primera cola de la lista
        if(escogido==null)
            return false; //Retorna false si no existe Turno
        else {
            if (!enAtencion.isEmpty()) {
                for (Map.Entry<Integer, Turno> entry : enAtencion.entrySet()) {
                    if (entry.getValue() == null) { //Recibe el valor del Turno, es null cuando Puesto no tiene Turnos
                        entry.setValue(escogido); //Agrega la primera cola en Espera
                        System.out.println("Mapas en cargar : "+ enAtencion);  
                        return true;
                    }
                }
            }
        }
        enEspera.offer(escogido); //Si no lo agrega, retorna a la lista        
        
        return false; //Si no se agrega un turno al puesto.
    }
    
    public static LinkedList<Integer> puestosVacios(){
        LinkedList<Integer> vacio = new LinkedList<>();
        if(!enAtencion.isEmpty()){
            for (Map.Entry<Integer, Turno> entry : enAtencion.entrySet()) {
            if(entry.getValue()==null)
                vacio.add(entry.getKey());
        }
        }
        return vacio;
    }
    
    /**
     * Elimina el puesto que el Admin escriba, si hay un puesto con Turno, este no
     * puede ser eliminado
     * @param n Numero del puesto a borrar
     * @return 
     */
    public static void eliminarPuesto(Integer n) {
        Iterator<Map.Entry<Integer, Turno>> itr = enAtencion.entrySet().iterator();
        while (itr.hasNext()) {
            Map.Entry<Integer, Turno> entry = itr.next();
            if (entry.getKey().equals(n) && entry.getValue() == null) {
                itr.remove();
            }
            // break;
        }
        ListIterator<Integer> borrado = puestos.listIterator();
        while (borrado.hasNext()) {
            Integer b = borrado.next();
            if (b.equals(n)) {
                borrado.remove();
            }
            //break;
        }
        System.out.println("Mapas: " + enAtencion);
        System.out.println("Hola Elimino Turno");

    }

    public PriorityQueue<Turno> getEnEspera() {
        return enEspera;
    }
    
    
}
