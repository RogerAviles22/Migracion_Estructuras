/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Turno;
import javafx.scene.control.Alert;

/**
 *
 * @author Rogencio
 */
public class VentanaEmergente {
    
    public static void sobrepasarLimitePuesto(){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Límite de Puesto");
        alert.setHeaderText("Sobrepasa el número total de Puestos!");
        alert.setContentText("Puesto no creado");
        alert.showAndWait();
    }
    
    public static void puestoCreado(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Puesto Creado");
        alert.setHeaderText("Puesto creado exitosamente!");
        alert.showAndWait();
    }
    
    public static void turnoCreado(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Turno Creado");
        alert.setHeaderText("Turno creado exitosamente!");
        alert.showAndWait();
    }
    
    public static void turnoEnEspera(Turno t){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Turno en Espera");
        alert.setHeaderText("Turno "+t.getCifra()+t.getTipo()+" en espera.\n"
                + "Espere a que termine un turno o creen más puestos.");
        alert.showAndWait();
    }
    
    public static void noHayPuestos(){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Sin Puestos");
        alert.setHeaderText("No hay puestos creados!");
        alert.setContentText("El admin ya creará futuros puestos");
        alert.showAndWait();
    }
    
    public static void campoVacio(){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Campo vacio");
        alert.setHeaderText("Falta escoger un campo!");
        alert.setContentText("Seleccione un puesto para modificación.");
        alert.showAndWait();
    }
    
    public static void puestoModificado(int n, int n1){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Puesto Modificado");
        alert.setHeaderText("El puesto "+n+" ha sido modificado por "+n1);
        alert.setContentText("Modificación exitosa");
        alert.showAndWait();
    }
    
    public static void puestoIgual(){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Mismo Puesto");
        alert.setHeaderText("El puesto que escogio es el mismo");
        alert.setContentText("Modificación rechazada");
        alert.showAndWait();
    }
    
    public static void puestoBorrado(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Puesto Borrado");
        alert.setHeaderText("Puesto Borrado con éxito!");
        alert.showAndWait();
    }
    
    public static void crearMigrante(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Migrante Registrado");
        alert.setHeaderText("Migrante Creado con exito!");
        alert.showAndWait();
    }
    
}
