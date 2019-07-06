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
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;

/**
 *
 * @author Rogencio
 */
public class vistaAdministrarPuesto {
    private BorderPane root;    
    private Button crearPuesto;
    private Button eliminarPuesto;
    private Button modificarPuestos;

    public vistaAdministrarPuesto() {
        root = new BorderPane();        
        BackgroundFill fondo = new BackgroundFill(Color.ORANGE, new CornerRadii(1),
                new Insets(0.0, 0.0, 0.0, 0.0));
        root.setBackground(new Background(fondo));
        crearPuestos();
        modificarPuestos();
        eliminarPuestos();
        HBox hb = new HBox();
        hb.getChildren().addAll(crearPuesto, modificarPuestos,eliminarPuesto);
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
        crearPuesto.setFont(Font.font("Georgia", FontWeight.BOLD, 11));
        crearPuesto.setTextAlignment(TextAlignment.CENTER);
        crearPuesto.setContentDisplay(ContentDisplay.TOP);  
        crearPuesto.setOnAction(e->{
            if(Atencion.cargarPuesto()){ //Crea el puesto
                VentanaEmergente.puestoCreado();
            }
            else{         
                System.out.println("Mapas en cargar : "+ Atencion.enAtencion); 
                System.out.println("Tamaño del puesto: "+Atencion.puestos.size());
                System.out.println("En espera: "+Atencion.enEspera);
                VentanaEmergente.sobrepasarLimitePuesto();
            }
        });
    }
    
    private void modificarPuestos(){
        Image image = new Image(getClass().getResourceAsStream("/Recursos/modificar.png"));
        ImageView view = new ImageView(image);
        modificarPuestos= new Button("MODIFICAR\nPUESTO",view);
        modificarPuestos.setFont(Font.font("Georgia", FontWeight.BOLD, 11));
        modificarPuestos.setTextAlignment(TextAlignment.CENTER);
        modificarPuestos.setContentDisplay(ContentDisplay.TOP);  
        modificarPuestos.setOnAction(e->{
            MiniPaneModificarT mpmt = new MiniPaneModificarT();
            mpmt.mostrarVentana();
        });
    }
    
    private void eliminarPuestos(){
        Image image = new Image(getClass().getResourceAsStream("/Recursos/eliminarPuesto.png"));
        ImageView view = new ImageView(image);
        eliminarPuesto= new Button("ELIMINAR\nPUESTO",view);
        eliminarPuesto.setFont(Font.font("Georgia", FontWeight.BOLD, 11));
        eliminarPuesto.setTextAlignment(TextAlignment.CENTER);
        eliminarPuesto.setContentDisplay(ContentDisplay.TOP);     
        eliminarPuesto.setOnAction(e -> {
            MiniPaneEliminarT mPE = new MiniPaneEliminarT();
            mPE.mostrarVentana();
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
