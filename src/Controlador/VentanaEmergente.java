/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

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
    
    public static void noHayPuestos(){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Sin Puestos");
        alert.setHeaderText("No hay puestos creados!");
        alert.setContentText("El admin ya creará futuros puestos");
        alert.showAndWait();
    }
    
    public static void puestoConTurnos(){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Puesto con Atención");
        alert.setHeaderText("El puesto que desea eliminar está\n atendiendo un turno!");
        alert.setContentText("Espere a que finalice su registro.");
        alert.showAndWait();
    }
    
    public static void puestoInexistente(){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Puesto Fantasma");
        alert.setHeaderText("El puesto que desea eliminar que desea eliminar\n no existe!");
        alert.setContentText("Digite un puesto existente.");
        alert.showAndWait();
    }
    
    public static void puestoBorrado(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Puesto Borrado");
        alert.setHeaderText("Puesto Borrado con éxito!");
        alert.showAndWait();
    }
    
}
