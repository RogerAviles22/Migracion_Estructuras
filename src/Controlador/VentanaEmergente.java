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
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Turno Creado");
        alert.setHeaderText("Turno creado exitosamente!");
        alert.showAndWait();
    }
}
