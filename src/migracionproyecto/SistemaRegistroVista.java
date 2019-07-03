/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package migracionproyecto;

import Modelo.Migrante;
import Modelo.Nacionalidad;
import Modelo.Registrador;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.time.LocalDate;
import java.util.Scanner;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

/**
 *
 * @author Francisco
 */
public class SistemaRegistroVista {
    private GridPane root;
    private Button confirmarRegistro;
    private Button retrocederRegistro;
    private Button vaciarRegistro;
    private static Migrante migrante;
    private static Registrador agente;
    
    public SistemaRegistroVista() {
        root = new GridPane();
        crearPanel();
        root.setHgap(5);
        root.setVgap(5);
        root.paddingProperty();
       root.setPadding(new Insets(20));
       BackgroundFill fondo = new BackgroundFill(Color.DARKTURQUOISE, new CornerRadii(1),
                new Insets(0.0, 0.0, 0.0, 0.0));
        root.setBackground(new Background(fondo));
        //root.setHgap(25);
        //root.setVgap(15);
    }
    
    private void crearPanel(){
        crearLabel();
        crearLabels();
        crearTextFields();
        sesionButones();
    }
    
    private void crearLabel(){
        Label encabezado= new Label();
        encabezado.setText("BIENVENIDO AL SISTEMA DE REGISTRO DE UN MIGRANTE, "
                + "\nPOR FAVOR ESCRIBIR LOS DATOS CORRECTAMENTE ANTES DE GUARDAR\n");
        
        root.add(encabezado, 0, 0);
    }
    private void crearLabels(){
        
        Label cedula= new Label("Cedula:");
        Label nombre= new Label("Nombre:");
        Label apellido= new Label("Apellido:");
        Label sexo= new Label("Sexo:");
        Label fechaNacimiento= new Label("Fecha Nacimiento:");
        Label paisOrigen= new Label("Pais Origen:");
        Label ciudadOrigen= new Label("Ciudad Origen:");
        Label cantonOrigen= new Label("Canton Origen: ");
        Label continenteOrigen= new Label("Continente Origen");
        Label tipo= new Label("Tipo: ");
        Label categoria= new Label("ENTRADA/SALIDA: ");
        Label fechaActual= new Label("Fecha Migratoria");
        
        root.add(cedula, 0, 1, 1, 1);
        root.add(nombre, 0, 3,1, 1);
        root.add(apellido, 0, 5,1,1);
        root.add(sexo, 0, 7, 1, 1);
        root.add(fechaNacimiento, 0,9, 1, 1);
        root.add(paisOrigen, 0, 11, 1, 1);
        root.add(ciudadOrigen, 0, 13, 1, 1);
        root.add(cantonOrigen, 0, 15, 1, 1);
        root.add(continenteOrigen, 0, 17, 1, 1);
        root.add(tipo, 0, 19, 1, 1);
        root.add(categoria, 0, 21,1,1);
        root.add(fechaActual, 0, 23, 1, 1);
        root.setHgap(5);
        root.setVgap(5);
        
        
        
    }
    private void crearTextFields(){
        TextField cedulaT= new TextField();
        TextField nombreT= new TextField();
        TextField apellidoT= new TextField();
        TextField sexoT= new TextField();
        TextField fechaNacimientoT= new TextField();
        TextField paisOrigenT= new TextField();
        TextField ciudadOrigenT= new TextField();
        TextField cantonOrigenT= new TextField();
        TextField continenteOrigenT= new TextField();
        ComboBox tipoT= new ComboBox();
        tipoT.getItems().addAll("Discapacidad","3era Edad","Normal");
        tipoT.setPromptText("Normal");
        ComboBox categoriaT= new ComboBox();
        categoriaT.getItems().addAll("Entrada","Salida");
        categoriaT.setPromptText("Entrada");
        categoriaT.setOnAction(e->{
            if(categoriaT.getValue().equals("Entrada")){
                
                SesionEntrada();
            }else{
                SesionSalida();
            }
        });
        TextField fechaActualT=new TextField();
        fechaActualT.setText(LocalDate.now().toString());
        
        root.add(cedulaT, 1, 1, 1, 1);
        root.add(nombreT, 1, 3, 1, 1);
        root.add(apellidoT, 1, 5, 1, 1);
        root.add(sexoT, 1, 7, 1, 1);
        root.add(fechaNacimientoT, 1, 9, 1, 1);
        root.add(paisOrigenT, 1, 11, 1, 1);
        root.add(ciudadOrigenT, 1, 13, 1, 1);
        root.add(cantonOrigenT, 1, 15, 1, 1);
        root.add(continenteOrigenT, 1, 17, 1, 1);
        root.add(tipoT, 1, 19, 1, 1);
        root.add(categoriaT, 1, 21,1,1);
        root.add(fechaActualT, 1, 23, 1, 1);
        root.setHgap(5);
        root.setVgap(5);
   
    
    }
    
    private void SesionEntrada(){
        
        Label medioTrans= new Label("Medio de Transportacion: ");
        Label paisEntrada= new Label("Pais al que Ingresa: ");
        Label ciudadEntrada= new Label("Ciudad al que Ingresa: ");
        Label continenteEntrada= new Label("Continente al que Ingresa ");
        TextField medioTransT= new TextField();
        TextField paisEntradaT= new TextField();
        TextField ciudadEntradaT= new TextField();
        TextField continenteEntradaT= new TextField();
        
        root.add(medioTrans, 0, 25, 1, 1);
        root.add(paisEntrada, 0, 27,1,1);
        root.add(ciudadEntrada, 0, 29,1,1);
        root.add(continenteEntrada, 0, 31,1,1);
        root.add(medioTransT, 1, 25, 1, 1);
        root.add(paisEntradaT, 1, 27,1,1);
        root.add(ciudadEntradaT, 1, 29,1,1);
        root.add(continenteEntradaT, 1, 31,1,1);
        
        
    }
    private void SesionSalida(){
        
        Label medioTrans= new Label("Medio de Transportacion: ");
        Label paisSalida= new Label("Pais al que se dirige ");
        Label ciudadSalida= new Label("Ciudad a la que se dirige: ");
        Label continenteSalida= new Label("Continente al que se dirige ");
        TextField medioTransT= new TextField();
        TextField paisSalidaT= new TextField();
        TextField ciudadSalidaT= new TextField();
        TextField continenteSalidaT= new TextField();
        
        root.add(medioTrans, 0, 25, 1, 1);
        root.add(paisSalida, 0, 27,1,1);
        root.add(ciudadSalida, 0, 29,1,1);
        root.add(continenteSalida, 0, 31,1,1);
        root.add(medioTransT, 1, 25, 1, 1);
        root.add(paisSalidaT, 1, 27,1,1);
        root.add(ciudadSalidaT, 1, 29,1,1);
        root.add(continenteSalidaT, 1, 31,1,1);
    }
    
    public void sesionButones(){
        HBox botones= new HBox();
        confirmarRegistro=new Button("CONFIRMAR");
        confirmarRegistro.setOnAction(e->{
            
        
        
        });
        vaciarRegistro= new Button("VACIAR DATA");
        Image image = new Image(getClass().getResourceAsStream("/Recursos/back.png"));
        ImageView view = new ImageView(image);
        retrocederRegistro= new Button();
        retrocederRegistro.setBackground(Background.EMPTY);
        retrocederRegistro.setContentDisplay(ContentDisplay.TOP);
        retrocederRegistro.setGraphic(view);
        retrocederRegistro.setOnAction(e->{
            SistemaMenuPrincipal p = new SistemaMenuPrincipal();
            MigracionProyecto.scene.setRoot(p.getRoot());
        });
        botones.getChildren().addAll(retrocederRegistro,confirmarRegistro,vaciarRegistro);
        botones.setAlignment(Pos.CENTER);
        botones.setPadding(Insets.EMPTY);
        botones.setSpacing(10);
        root.addRow(40, botones);
    }

    public GridPane getRoot() {
        return root;
    }

    public void setRoot(GridPane root) {
        this.root = root;
    }

    public Button getConfirmarRegistro() {
        return confirmarRegistro;
    }

    public void setConfirmarRegistro(Button confirmarRegistro) {
        this.confirmarRegistro = confirmarRegistro;
    }

    public Button getRetrocederRegistro() {
        return retrocederRegistro;
    }

    public void setRetrocederRegistro(Button retrocederRegistro) {
        this.retrocederRegistro = retrocederRegistro;
    }

    public Button getVaciarRegistro() {
        return vaciarRegistro;
    }

    public void setVaciarRegistro(Button vaciarRegistro) {
        this.vaciarRegistro = vaciarRegistro;
    }
}
    