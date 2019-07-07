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
import java.util.Iterator;
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
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

/**
 *
 * @author Rogencio
 */
public class MiniPaneModificarT {
    private BorderPane root;
    private Button aceptar;    
    private Button cancelar;
    private Label titulo;
    private Label msg;
    private Stage stageForm;
    private ComboBox puestosCreados;
    private ComboBox puestosModificacion;
    private LinkedList<Integer> sustitucion;
    
    public MiniPaneModificarT(){
        root= new BorderPane();
        puestosCreados = new ComboBox();
        puestosModificacion = new ComboBox();        
        aceptar= new Button("Aceptar");       
        cancelar= new Button("Cancelar");  
        titulo = new Label("SELECCIONE EL PUESTO \n\tA MODIFICAR"); titulo.setFont(Font.font("Verdana", FontWeight.BOLD, 15));
        msg= new Label("ADVERTENCIA: Si no aparecen puestos:\n"
                + "\t1) No hay puestos creados.\n"
                + "\t2) ErrOr En eL SisTEMa."); 
        msg.setAlignment(Pos.CENTER);
        msg.setFont(Font.font("Verdana", FontWeight.BOLD, 11));
        VBox vb = new VBox();
        vb.setSpacing(25);
        vb.setAlignment(Pos.CENTER);
        vb.setPadding(new Insets(10, 20, 40, 20));
        vb.getChildren().addAll(titulo,msg,cargaComboBox());
        root.setCenter(vb);
        BackgroundFill fondo = new BackgroundFill(Color.SKYBLUE, new CornerRadii(1),
                new Insets(0.0, 0.0, 0.0, 0.0));
        root.setBackground(new Background(fondo)); 
        cerrarActionVentana();
        modificarActionPuesto();
    }
    
    private GridPane cargaComboBox(){
        GridPane gp =  new GridPane();
        Label lb = new Label("Escoja el puesto a modificar: ");
        lb.setFont(Font.font("Consola", FontWeight.MEDIUM, 13));
        gp.add(lb, 0, 0);
        puestosCreados.getItems().addAll(Atencion.puestos);        
        gp.add(puestosCreados, 1, 0);
        Label lb1 = new Label("Escoja número para modificación: ");
        lb1.setFont(Font.font("Consola", FontWeight.MEDIUM, 13));
        gp.add(lb1, 0, 1);
        puestosSustitutos();
        puestosModificacion.getItems().addAll(sustitucion);
        gp.add(puestosModificacion, 1, 1);
        gp.add(aceptar, 0, 2); 
        gp.add(cancelar, 1, 2);
        gp.setPadding(new Insets(10, 10, 10, 10));
        gp.setVgap(15);
        gp.setHgap(15);
        return gp;
    }
    
    private void puestosSustitutos(){
        sustitucion= new LinkedList<>();
        sustitucion.add(100);
        sustitucion.add(150);
        sustitucion.add(200);
        sustitucion.add(250);        
        sustitucion.add(300);
        sustitucion.add(350);
        sustitucion.add(400);        
        sustitucion.add(450);
    }
    
    
    
    private void cerrarActionVentana(){
        cancelar.setOnMouseClicked(e->{
            stageForm.close();
        });
    }
    
    private void modificarActionPuesto(){
        aceptar.setOnMouseClicked(e->{
            if(cambiarPuesto());
                stageForm.close();
        });
    }
    
    private boolean cambiarPuesto(){
        Integer n = (Integer) puestosCreados.getValue();
        Integer n1 = (Integer) puestosModificacion.getValue();
        if(n==null || n1== null){
            VentanaEmergente.campoVacio();
            return false;
        }
        else if(n.equals(n1)){
            VentanaEmergente.puestoIgual();
            return false;
        }
        else {
            Integer key = null;
            Turno turno = null;      
            Iterator<Map.Entry<Integer, Turno>> itr = enAtencion.entrySet().iterator();        
            while (itr.hasNext()) {
                Map.Entry<Integer, Turno> entry = itr.next();
                if (entry.getKey().equals(n)) {
                    key = entry.getKey();
                    turno = entry.getValue();
                    itr.remove();                    
                }
            }
            enAtencion.put(n1, turno);   
            
            ListIterator<Integer> itrPuesto = Atencion.puestos.listIterator();
            while (itrPuesto.hasNext()) {
                Integer b = itrPuesto.next();
                if (b.equals(n)) 
                    itrPuesto.remove();                
            }
            Atencion.puestos.add(n1);
            
            ListIterator<Integer> itrSust = sustitucion.listIterator(); 
            while(itrSust.hasNext()){
                Integer s = itrSust.next();
                if (s.equals(n1)) 
                    itrSust.remove();                
            }
            sustitucion.add(key);
            
            System.out.println("Key "+key);
            System.out.println("Ssutitutos "+sustitucion);
            VentanaEmergente.puestoModificado(n, n1);
            return true;
        }                
    }
    
    
    public void mostrarVentana(){
        stageForm = new Stage();
        Scene scene = new Scene(getRoot(), 350, 350);
        stageForm.setTitle("Modificar puesto");
        stageForm.setScene(scene);
        stageForm.show();
        Image image= new Image("/Recursos/modificar.png");
        stageForm.getIcons().add(image);
        stageForm.show();        
    }
    
    private BorderPane getRoot() {
        return root;
    }
}
