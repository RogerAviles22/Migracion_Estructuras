/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package migracionproyecto;

import Controlador.VentanaEmergente;
import Modelo.Atencion;
import static Modelo.Atencion.enAtencion;
import Modelo.Turno;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Map;
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
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

/**
 *
 * @author Rogencio
 */
public class MiniPaneSeleccionP {
    private BorderPane root;
    private Button aceptar;    
    private Button cancelar;
    private Label titulo;
    private Label msg;
    private Stage stageForm;
    private ComboBox puestosCreados;
    
    public MiniPaneSeleccionP(){
        root= new BorderPane();
        puestosCreados = new ComboBox();      
        aceptar= new Button("Aceptar");       
        cancelar= new Button("Cancelar");  
        titulo = new Label("SELECCIONE EL PUESTO \n\tQUE ATENDERÁ EL REGISTRO"); 
        titulo.setFont(Font.font("Verdana", FontWeight.BOLD, 15));
        msg= new Label("ADVERTENCIA: Si no aparecen puestos:\n"
                + "\t1) No hay puestos creados.\n"
                + "\t2) No hay puestos con turnos."); 
        msg.setAlignment(Pos.CENTER);
        msg.setFont(Font.font("Verdana", FontWeight.BOLD, 11));
        VBox vb = new VBox();
        vb.setSpacing(25);
        vb.setAlignment(Pos.CENTER);
        vb.setPadding(new Insets(10, 20, 40, 20));
        HBox hb = new HBox();
        hb.getChildren().addAll(aceptar,cancelar);
        hb.setSpacing(25);
        vb.getChildren().addAll(titulo,msg,puestosCreados,hb);
        root.setCenter(vb);
        BackgroundFill fondo = new BackgroundFill(Color.SKYBLUE, new CornerRadii(1),
                new Insets(0.0, 0.0, 0.0, 0.0));
        root.setBackground(new Background(fondo)); 
        rellenarComboBox();
        aceptarActionPuesto();
        cerrarActionVentana();
    }
    
    
    
    private void cerrarActionVentana(){
        cancelar.setOnMouseClicked(e->{
            stageForm.close();
        });
    }
    
    private void aceptarActionPuesto(){
        aceptar.setOnMouseClicked(e->{
            if(puestoSeleccion()){                
                SistemaRegistroVista stv = new SistemaRegistroVista();
                MigracionProyecto.scene.setRoot(stv.getRoot());
                stageForm.close();
            }
            
        });
    }
    
    private void rellenarComboBox(){
        LinkedList<Integer> puestos= new LinkedList<>();
        for (Map.Entry<Integer, Turno> entry : enAtencion.entrySet()) {
            if(entry.getValue()!=null)                
                puestos.add(entry.getKey());
        }
        puestosCreados.getItems().addAll(puestos);
    }
    
    /**
     * Seleccionamos el puesto, borramos el que tenia y despues lo llenamos con el sgte puesto 
     * @return True si se ha escogido un turno con puesto
     */
    private boolean puestoSeleccion(){
        Integer puestoSeleccionado = (Integer) puestosCreados.getValue();
        if(puestoSeleccionado==null){
            VentanaEmergente.turnoSinSeleccion();
            return false;
        }
        else{
            for (Map.Entry<Integer, Turno> entry : enAtencion.entrySet()) {
                //Comparamos que la clave sea igual a la seleccionada para setear Turno                    
                if(entry.getKey().equals(puestoSeleccionado)){
                    entry.setValue(null);
                    Turno siguienteCola=Atencion.enEspera.poll();
                    if(siguienteCola!=null)
                        entry.setValue(siguienteCola);
                    break;                    
                }  
            }
            return true;
        }
       // return false;
    }
    
    private BorderPane getRoot() {
        return root;
    }
    
    public void mostrarVentana(){
        stageForm = new Stage();
        Scene scene = new Scene(getRoot(), 350, 350);
        stageForm.setTitle("Escoger puesto");
        stageForm.setScene(scene);
        stageForm.show();
        Image image= new Image("/Recursos/puesto.png");
        stageForm.getIcons().add(image);
        stageForm.show();        
    }
    
}
