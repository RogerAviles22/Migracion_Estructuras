/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package migracionproyecto;

import Modelo.Atencion;
import static Modelo.Atencion.enAtencion;
import Modelo.Turno;
import java.util.Map;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
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
    private ComboBox prioridad;
    private int priority; //Número del tipo de Persona escogido
    private Atencion atencion;
    private Turno turno;
    private Map<Integer,Turno> atenciones =  Atencion.enAtencion;
    private GridPane visualizacion;
    
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
        VBox vb= new VBox();
        Label welcome= new Label("Registro de Turno");
        HBox hb = new HBox();
        Label choose= new Label("Escoja un turno por \nsu tipo de Persona:");
        hb.getChildren().addAll(choose,seleccionarPrioridad());
        hb.setPadding(new Insets(10, 10, 10, 10));
        hb.setSpacing(50);
        setearFuente(welcome);
        vb.getChildren().addAll(welcome,hb);
        vb.setPadding(new Insets(10, 10, 10, 10));
        vb.setAlignment(Pos.CENTER);
        root.setTop(vb);
    }
    
    /**
     * Crea el Turno y lo dirige a un Puesto dependiendo de la prioridad
     * @param cifra 
     */
    private void crearAtencion(int cifra){
        atencion= new Atencion();
        turno = new Turno(cifra, priority);
        atencion.cargarEnEspera(turno);
        atencion.cargarEnAtencion();
        
    }
    
    /**
     * Crea un ComboBox con la prioridad de la persona a escoger turno.
     * Cambia el valor de la prioridad según lo seleccionado.
     * @return ComboBox con las prioridades.
     */
    private ComboBox seleccionarPrioridad(){        
        prioridad = new ComboBox();
        prioridad.getItems().addAll("Discapacidad","3era Edad","Normal");
        prioridad.setPromptText("Normal");
        prioridad.setOnAction(e->{
            if(prioridad.getValue().equals("Discapacidad")){
                priority =3;
                //++Atencion.turnoDiscapacidad;
                crearAtencion(priority);
                
            }
                
            else if(prioridad.getValue().equals("3era Edad")){
                priority =2;
                //++Atencion.turno3erEdad;
                crearAtencion(priority);
                
            }
                
            else if(prioridad.getValue().equals("Normal")){
                priority =1;
                //++Atencion.turnoNormal;
                crearAtencion(priority);
                
            }
        }                
        );
        return prioridad;        
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
        VBox vb= new VBox();
        Label t= new Label("Turnos");  
        Label p= new Label("Puestos");   
        setearFuente(t);   setearFuente(p);  
        visualizacion= new GridPane();
        panelVisualizacioTurnos(t, p);
        vb.getChildren().add( visualizacion);
        vb.setSpacing(25);
        vb.setPadding(new Insets(10, 20, 0, 20));
        vb.setAlignment(Pos.TOP_CENTER);
        root.setRight(vb);
    }   
    
    private void panelVisualizacioTurnos(Label t, Label p){
        visualizacion.add(t, 0, 0);
        visualizacion.add(p, 1, 0);
        visualizacion.add(new Label("hola"), 0, 1);
        visualizacion.add(new Label("que"), 1, 1);
        visualizacion.add(new Label("hace"), 0, 2);
        visualizacion.add(new Label("AQUI"), 1, 2);
        visualizacion.add(new Label("deben"), 0, 3);
        visualizacion.add(new Label("ir"), 1, 3);
        visualizacion.add(new Label("text"), 0, 4);
        visualizacion.add(new Label("con"), 1, 4);
        visualizacion.add(new Label("datos"), 0, 5);
        visualizacion.add(new Label("turno"), 1, 5);
        visualizacion.add(new Label("y"), 0, 6);
        visualizacion.add(new Label("puesto"), 1, 6);
        visualizacion.setPadding(new Insets(10, 15, 10, 10));
        visualizacion.setAlignment(Pos.CENTER);
        visualizacion.setHgap(15);
        visualizacion.setVgap(15);
    }
    
        
    
    /**
     * Muestra el horario de atención del sistema de Migración
     */
    private void conclusion(){
        HBox vb= new HBox();
        Label horario= new Label("Horario de Atención Lunes/Viernes 8:00-22:00");
        setearFuente(horario);
        vb.getChildren().addAll(horario,back());
        vb.setSpacing(70);
        vb.setAlignment(Pos.CENTER);
        root.setBottom(vb);
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
    
    private void setearFuente(Label l){
        l.setFont(Font.font("Georgia",FontWeight.BOLD,20));
    }
    
    
    public BorderPane getRoot() {
        return root;
    }

    public ComboBox getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(ComboBox prioridad) {
        this.prioridad = prioridad;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }
    
    
}
