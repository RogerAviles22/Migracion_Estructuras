/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package migracionproyecto;

import Controlador.VentanaEmergente;
import Modelo.Atencion;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

/**
 *
 * @author Rogencio
 */
public class miniPaneEliminar {
    private BorderPane root;
    private Button aceptar;
    private Label titulo;
    private Label msg;
    private Stage stageForm;
    private ComboBox puestosVacios;
    
    public miniPaneEliminar(){
        root= new BorderPane();
        puestosVacios = new ComboBox();
        rellenarComboBox();
        aceptar= new Button("Aceptar");        
        titulo = new Label("SELECCIONE EL PUESTO A BORRAR\n"); titulo.setFont(Font.font("Verdana", FontWeight.BOLD, 15));
        msg= new Label("ADVERTENCIA: Si no aparecen puestos:\n"
                + "\t1) No hay puestos creados.\n"
                + "\t2) Los puestos están atendiendo turnos."); msg.setFont(Font.font("Verdana", FontWeight.BOLD, 11));
        VBox vb = new VBox();
        vb.getChildren().addAll(titulo,msg, puestosVacios,aceptar);
        vb.setSpacing(25);
        vb.setAlignment(Pos.CENTER);
        vb.setPadding(new Insets(10, 20, 40, 20));
        root.setCenter(vb);
        BackgroundFill fondo = new BackgroundFill(Color.AZURE, new CornerRadii(1),
                new Insets(0.0, 0.0, 0.0, 0.0));
        root.setBackground(new Background(fondo));         
        cerrarVentana();
    }
    
    private void rellenarComboBox(){        
            puestosVacios.getItems().addAll(Atencion.puestosVacios());
            puestosVacios.setOnAction(e->{
                Integer n = (Integer) puestosVacios.getValue();
                Atencion.eliminarPuesto(n);
                VentanaEmergente.puestoBorrado();
            });        
    }
    
    private void cerrarVentana(){
        aceptar.setOnMouseClicked(e->{
            stageForm.close();
        });
    }
    
    public void mostrarVentana(){
        stageForm = new Stage();
        Scene scene = new Scene(getRoot(), 335, 240);
        stageForm.setTitle("Borrar puesto");
        stageForm.setScene(scene);
        stageForm.show();
        Image image= new Image("/Recursos/eliminarPuesto.png");
        stageForm.getIcons().add(image);
        stageForm.show();        
    }
    
    private BorderPane getRoot() {
        return root;
    }
}
