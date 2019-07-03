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
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;

/**
 *
 * @author Rogencio
 */
public class vistaAdministrarPuesto {
    private BorderPane root;    
    private Button crearPuesto;
    private Button eliminarPuesto;

    public vistaAdministrarPuesto() {
        root = new BorderPane();        
        BackgroundFill fondo = new BackgroundFill(Color.ORANGE, new CornerRadii(1),
                new Insets(0.0, 0.0, 0.0, 0.0));
        root.setBackground(new Background(fondo));
        crearPuestos();
        eliminarPuestos();
        HBox hb = new HBox();
        hb.getChildren().addAll(crearPuesto, eliminarPuesto);
        hb.setPadding(new Insets(10, 10, 10, 10));
        hb.setSpacing(50);
        hb.setAlignment(Pos.CENTER);
        root.setCenter(hb);
        root.setBottom(back());
        
    }
    
    private void crearPuestos(){
        Image image = new Image(getClass().getResourceAsStream("/Recursos/crearPuesto.png"));
        ImageView view = new ImageView(image);
        crearPuesto= new Button("CREAR\nPUESTO",view);
        crearPuesto.setTextAlignment(TextAlignment.CENTER);
        crearPuesto.setContentDisplay(ContentDisplay.TOP);  
        crearPuesto.setOnAction(e->{
            if(Atencion.cargarPuesto()){
                System.out.println(Atencion.modulo);
                VentanaEmergente.puestoCreado();
            }
            else{
                VentanaEmergente.sobrepasarLimitePuesto();
            }
        });
    }
    
    private void eliminarPuestos(){
        Image image = new Image(getClass().getResourceAsStream("/Recursos/eliminarPuesto.png"));
        ImageView view = new ImageView(image);
        eliminarPuesto= new Button("ELIMINAR\nPUESTO",view);
        eliminarPuesto.setTextAlignment(TextAlignment.CENTER);
        eliminarPuesto.setContentDisplay(ContentDisplay.TOP);     
        eliminarPuesto.setOnAction(e->{
            VentanaEmergente.preguntaEliminaPuesto(0,Atencion.eliminarPuesto(0));
        });
    }
    
    
    
    /**
     * Regresa al menu principal
     * @return 
     */
    private HBox back(){
        HBox hb = new HBox();
        Image image = new Image(getClass().getResourceAsStream("/Recursos/back.png"));
        ImageView view = new ImageView(image);
        Button back = new Button();
        back.setBackground(Background.EMPTY);
        back.setContentDisplay(ContentDisplay.TOP);
        back.setGraphic(view);
        back.setOnAction(e->{
            SistemaMenuPrincipal p = new SistemaMenuPrincipal();
            MigracionProyecto.scene.setRoot(p.getRoot());
        });
        hb.getChildren().add(back);
        return hb;
    }
    
    public BorderPane getRoot() {
        return root;
    }
}