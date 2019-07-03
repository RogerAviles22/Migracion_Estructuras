/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package migracionproyecto;

import Controlador.VentanaEmergente;
import Modelo.Atencion;
import Modelo.Puesto;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
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
public class SistemaMenuPrincipal {
    private BorderPane root;
    private Button crearPuesto;
    private Button menuTurno;
    private Button menuRegistro;
    private Button menuBusqueda;
    
    public SistemaMenuPrincipal(){
        root = new BorderPane();   
        BackgroundFill fondo = new BackgroundFill(Color.ORANGE, new CornerRadii(1),
                new Insets(0.0, 0.0, 0.0, 0.0));
        root.setBackground(new Background(fondo));
        inicializarContenido();
        HBox hb = new HBox();
        hb.getChildren().addAll(crearPuesto,menuTurno,menuRegistro, menuBusqueda );
        hb.setPadding(new Insets(10, 10, 10, 10));
        hb.setSpacing(30);
        hb.setAlignment(Pos.CENTER);
        root.setCenter(hb);
    }
    
    private void inicializarContenido(){
        encabezado();
        primerBoton();
        segundoBoton();
        tercerBoton();
        cuartoBoton(); 
    }
    
    /**
     * Muestra el titulo del Menú Principal
     */
    private void encabezado(){
        HBox hb= new HBox();
        Image image = new Image(getClass().getResourceAsStream("/Recursos/menu.png"));
        ImageView view = new ImageView(image);
        Label titulo= new Label("BIENVENIDO AL \nREGISTRO MIGRATORIO");        
        titulo.setFont(Font.font("Georgia",FontWeight.BOLD,30));
        hb.getChildren().addAll(view,titulo);
        hb.setAlignment(Pos.CENTER);
        hb.setSpacing(10);
        hb.setPadding(new Insets(30, 0, 0, 0));
        root.setTop(hb);
    }

    /**
     * Boton que crea un puesto en el menú Turno
     * *No funcional
     */
    private void primerBoton(){   
        Image image = new Image(getClass().getResourceAsStream("/Recursos/puesto.png"));
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
    
    /**
     * Boton que dirige al menúTurno
     */
    private void segundoBoton(){        
        Image image = new Image(getClass().getResourceAsStream("/Recursos/turno.png"));
        ImageView view = new ImageView(image);
        menuTurno= new Button("OBSERVAR\nTURNOS",view);
        menuTurno.setTextAlignment(TextAlignment.CENTER);
        menuTurno.setContentDisplay(ContentDisplay.TOP);
        menuTurno.setOnAction(e->{
            SistemaTurnosVista stv = new SistemaTurnosVista();
            MigracionProyecto.scene.setRoot(stv.getRoot());
        });
    }
    
    /**
     * Botón que dirige al menú Registrar
     */
    private void tercerBoton(){        
        Image image = new Image(getClass().getResourceAsStream("/Recursos/registro.png"));
        ImageView view = new ImageView(image);
        menuRegistro= new Button("REGISTRAR\nMIGRANTE",view);
        menuRegistro.setTextAlignment(TextAlignment.CENTER);
        menuRegistro.setContentDisplay(ContentDisplay.TOP);
        menuRegistro.setOnAction(e->{
            SistemaRegistroVista stv = new SistemaRegistroVista();
            MigracionProyecto.scene.setRoot(stv.getRoot());
        });
    }
    
    /**
     * Botón que dirige al menú Búsqueda
     */
    private void cuartoBoton(){        
        Image image = new Image(getClass().getResourceAsStream("/Recursos/buscar.png"));
        ImageView view = new ImageView(image);
        menuBusqueda= new Button("BUSCAR\nMIGRANTE",view);
        menuBusqueda.setTextAlignment(TextAlignment.CENTER);
        menuBusqueda.setContentDisplay(ContentDisplay.TOP);
    }
    
    public BorderPane getRoot() {
        return root;
    }
    
    
    
}
