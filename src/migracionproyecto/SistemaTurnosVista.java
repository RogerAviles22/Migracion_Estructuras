/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package migracionproyecto;

import Controlador.VentanaEmergente;
import Modelo.Atencion;
import Modelo.Turno;
import TDA.CircularLinkedList;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Map;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
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
    private GridPane visualizacion;
    private int priority; //Número del tipo de Persona escogido
    private Atencion atencion;
    private CircularLinkedList<ImageView> publicidad = new CircularLinkedList<>();
    private Turno turno;
    private HiloPublicidad hPublicidad; //Hilo con las publicidades
    //private Map<Integer,Turno> atenciones =  Atencion.enAtencion;
    
    public SistemaTurnosVista() {  
        root= new BorderPane();
        encabezado();
        cargarPublicidad();
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
     * Actualiza el GridPane cada vez que agrega un nuevo turno
     * @param cifra 
     */
    private void crearAtencion(int cifra){
        atencion= new Atencion();
        turno = new Turno(cifra, priority); //priority es el tipo de prioridad
        if(atencion.cargarEnEspera(turno)){
            atencion.cargarEnAtencion();
            VentanaEmergente.turnoCreado();
            if(priority==3)
                Atencion.turno3erEdad++;
            else if(priority==2)
                Atencion.turnoDiscapacidad++;
            else
                Atencion.turnoNormal++;
            visualizacion.getChildren().clear(); //Borra el contenido y actualiza con datos nuevos
            panelVisualizacioTurnosPuestos(Atencion.puestos, Atencion.enAtencion);
        }
        
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
                priority=3;
                crearAtencion(Atencion.turno3erEdad);
            }                
            else if(prioridad.getValue().equals("3era Edad")){
                priority =2;
                crearAtencion(Atencion.turnoDiscapacidad);
            }                
            else if(prioridad.getValue().equals("Normal")){                
                priority =1;
                crearAtencion(Atencion.turnoNormal);              
            }            
        }                
        );
        return prioridad;        
    }
    
    
    
    /**
     * Actualiza las imagenes de publicidad con dimension obligatoria de 400*400
     */
    private void izquierda(){
        VBox vb= new VBox();
        Image image = new Image(getClass().getResourceAsStream("/Recursos/Publicidad2.jpg"));
        Label myLabel = new Label();
        myLabel.setGraphic(new ImageView(image));
        vb.getChildren().add(myLabel);
        vb.setPadding(new Insets(10, 0, 0, 20));
        vb.setAlignment(Pos.CENTER);
        hPublicidad = new HiloPublicidad(vb, publicidad);
        Thread publi = new Thread(hPublicidad);
        publi.start();
        root.setLeft(vb);
    }
    
    /**
     * Carga las imagenes del .txt y las almacena en el CircularLinkedList
     */
    private void cargarPublicidad() {
        String archivo="src/archivos/publicidades.txt";        
        try(Scanner sc = new Scanner(new File(archivo))){
            while(sc.hasNextLine()){
                String linea= sc.nextLine();
                Image image = new Image(getClass().getResourceAsStream("/Recursos/"+linea));
                ImageView contenedor = new ImageView(image);
                publicidad.addLast(contenedor);
            }            
        }catch (FileNotFoundException ex) {
            System.out.println("File Not Found");
        }
    }
    
    /**
     * Muestra una tabla con los turnos de los pacientes
     */
    private void derecha(){
        VBox vb= new VBox();
        HBox hb = new HBox();
        Label t= new Label("TURNOS");  
        t.setId("label");
        t.getStylesheets().add("Controlador/estiloTurno.css");
        Label p= new Label("PUESTOS");  
        p.setId("label");
        p.getStylesheets().add("Controlador/estiloTurno.css");
        hb.setSpacing(25);
        hb.getChildren().addAll(t,p);
        visualizacion= new GridPane();
        panelVisualizacioTurnosPuestos(Atencion.puestos, Atencion.enAtencion); //Recorre el LinkedList de puestos creados
        vb.getChildren().addAll(hb, visualizacion); 
        vb.setSpacing(25);
        vb.setPadding(new Insets(10, 20, 0, 20));
        vb.setAlignment(Pos.TOP_CENTER);
        root.setRight(vb);
    }   
    
    private void panelVisualizacioTurnosPuestos(LinkedList<Integer> puestos, Map<Integer,Turno> enAtencion){
        if(!puestos.isEmpty()){
            ListIterator<Integer> liPuesto = puestos.listIterator();
            int i=0;
            while(liPuesto.hasNext()){
                Label puesto= new Label(String.valueOf(liPuesto.next())); 
                puesto.setId("puesto");
                puesto.getStylesheets().add("Controlador/estiloTurno.css");
                visualizacion.add(puesto, 1, i);
                i++;
            }
        }        
        if(!enAtencion.isEmpty()){
            int i =0;
            for (Map.Entry<Integer, Turno> entry : enAtencion.entrySet()) {
                if(entry.getValue()!=null){
                    Turno tu = entry.getValue();
                    String pr = null;
                    if(tu.getTipo()==3)
                        pr = "D - ";
                    else if(tu.getTipo()==2)
                        pr = "E -  ";
                    else if(tu.getTipo()==1)
                        pr = "N - ";
                    Label turn = new Label(pr+String.valueOf(tu.getCifra()));
                    turn.setId("puesto");
                    turn.getStylesheets().add("Controlador/estiloTurno.css");
                    visualizacion.add(turn , 0, i);
                    i++;
                }
            }
        }        
        visualizacion.setPadding(new Insets(10, 15, 10, 10));
        visualizacion.setAlignment(Pos.CENTER);
        visualizacion.setHgap(50);
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
            hPublicidad.setDetener();
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
    
}

/**
 * Hilo que ayudará en cargar las imagenes al CircularLinkedList, recibiendo como
 * parametro en VBox donde estarán las imágenes y el circularLL. 
 * @author Rogencio
 */
class HiloPublicidad implements Runnable{    
    private VBox izquierda;
    private CircularLinkedList<ImageView> publicidades;
    private boolean detener = true;
    
    public HiloPublicidad(VBox publicidad, CircularLinkedList<ImageView> image){
        izquierda = publicidad;
        this.publicidades = image;
    }
    
    @Override
    public void run() {
        Iterator<ImageView> it = publicidades.iterator();
        while(detener){
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    izquierda.getChildren().clear(); //Borra el contenido del VBox
                    izquierda.getChildren().add(it.next()); //Agrega la sgte imagen publicitaria
                }
            });
                    
            try {
                Thread.sleep(1000); //Cambia la imagen cada 5 seg
            } catch (InterruptedException ex) {
                Logger.getLogger(SistemaTurnosVista.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public void setDetener(){
        detener = false;
    }
}
