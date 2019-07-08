/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package migracionproyecto;

import Modelo.Atencion;
import Modelo.Migrante;
import java.util.LinkedList;
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
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

/**
 *
 * @author Francisco
 */
public class miniPaneBusqueda {
    private BorderPane root;
    private Button aceptar;    
    private Button cancelar;
    private Label titulo;
    private Label msg;
    private Stage stageForm;
    private ComboBox rut;
    private Migrante migrante;
    
    public miniPaneBusqueda(Migrante migrante){
        this.migrante=migrante;
        root= new BorderPane();
        rut = new ComboBox();       
        aceptar= new Button("Aceptar");       
        cancelar= new Button("Cancelar");  
        titulo = new Label("SELECCIONE EL TIPO DE REGISTRO MIGRATORIO \n\tA BUSCAR"); 
        titulo.setFont(Font.font("Verdana", FontWeight.BOLD, 15));
        msg= new Label("ADVERTENCIA: Si no se encuentra en la BD:\n"
                + "\t1) Por favor, verifique su registro migratorio.\n"
                + "\t2) Por favor, registre un ingreso o salida migratoria"); 
        msg.setAlignment(Pos.CENTER);
        msg.setFont(Font.font("Verdana", FontWeight.BOLD, 11));
        VBox vb = new VBox();
        vb.setSpacing(25);
        vb.setAlignment(Pos.CENTER);
        vb.setPadding(new Insets(10, 20, 40, 20));
        vb.getChildren().addAll(titulo,msg,cargaComboBox());
        root.setCenter(vb);
        BackgroundFill fondo = new BackgroundFill(Color.BLANCHEDALMOND, new CornerRadii(1),
                new Insets(0.0, 0.0, 0.0, 0.0));
        root.setBackground(new Background(fondo)); 
        cerrarActionVentana();
        aceptarAction();
    }
    
    private GridPane cargaComboBox(){
        GridPane gp =  new GridPane();
        rut.getItems().addAll("Entrada","Salida");
        rut.setValue("Entrada");
        Label lb = new Label("Escoja su estado migratorio ");
        lb.setFont(Font.font("Consola", FontWeight.MEDIUM, 13));
        gp.add(lb, 0, 0);
        gp.add(rut, 0, 2);
        gp.add(aceptar, 3, 2); 
        gp.add(cancelar, 4, 2);
        gp.setPadding(new Insets(10, 10, 10, 10));
        gp.setVgap(15);
        gp.setHgap(15);
        return gp;
    }
     public void mostrarVentana(){
        stageForm = new Stage();
        Scene scene = new Scene(getRoot(), 500, 350);
        stageForm.setTitle("Datos Migratorios");
        stageForm.setScene(scene);
        stageForm.show();
        Image image= new Image("/Recursos/modificar.png");
        stageForm.getIcons().add(image);
        stageForm.show();        
    }
     private void cerrarActionVentana(){
        cancelar.setOnMouseClicked(e->{
            stageForm.close();
        });
    }
    private void aceptarAction(){
        aceptar.setOnMouseClicked(e->{
            System.out.println(rut.getValue());
            DatosDeBusqueda datos= new DatosDeBusqueda(rut.getValue().toString(),migrante);
            MigracionProyecto.scene.setRoot(datos.getRoot());
            stageForm.close();
        });
    }

    public BorderPane getRoot() {
        return root;
    }

    public void setRoot(BorderPane root) {
        this.root = root;
    }
    
     
    
}
