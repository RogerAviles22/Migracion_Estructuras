/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package migracionproyecto;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

/**
 *
 * @author Rogencio
 */
public class SistemaTurnosVista {
    private BorderPane root;
    
    public SistemaTurnosVista() {  
        root= new BorderPane();
        encabezado();
        izquierda();
        derecha();
        conclusion();
        BackgroundFill fondo = new BackgroundFill(Color.LIGHTSKYBLUE, new CornerRadii(1),
                new Insets(0.0, 0.0, 0.0, 0.0));
        root.setBackground(new Background(fondo));
         
    }
    
    /**
     * Muestra el encabezado del panel Turnos del Sistema
     */
    private void encabezado(){
        HBox vb= new HBox();
        Label welcome= new Label("BIENVENIDOS AL SISTEMA DE MIGRACIÓN");
        setearFuente(welcome);
        vb.getChildren().add(welcome);
        vb.setAlignment(Pos.CENTER);
        root.setTop(vb);
    }
    
    /**
     * Carga las imagenes de publicidad con dimension obligatoria de 400*400
     */
    private void izquierda(){
        VBox vb= new VBox();
        Image image = new Image(getClass().getResourceAsStream("/Recursos/Publicidad2.jpg"));
        Label myLabel = new Label();
        myLabel.setGraphic(new ImageView(image));
        vb.getChildren().add(myLabel);
        vb.setPadding(new Insets(10, 0, 0, 20));
        vb.setAlignment(Pos.CENTER);
        root.setLeft(vb);
    }
    
    /**
     * Muestra una tabla con los turnos de los pacientes
     */
    private void derecha(){
        HBox hb= new HBox();
        Label t= new Label("Turnos");
        Label p= new Label("Puestos");
        setearFuente(t);
        setearFuente(p);
        hb.getChildren().addAll(t,p);
        hb.setSpacing(25);
        hb.setPadding(new Insets(10, 20, 0, 20));
        hb.setAlignment(Pos.TOP_CENTER);
        root.setRight(hb);
    }
    
    /**
     * Muestra el horario de atención del sistema de Migración
     */
    private void conclusion(){
        HBox vb= new HBox();
        Label horario= new Label("Horario de Atención Lunes/Viernes 8:00-22:00");
        setearFuente(horario);
        vb.getChildren().add(horario);
        vb.setAlignment(Pos.CENTER);
        root.setBottom(vb);
    }
    
    private void setearFuente(Label l){
        l.setFont(Font.font("Georgia",FontWeight.BOLD,20));
    }
    
    
    public BorderPane getRoot() {
        return root;
    }
}
