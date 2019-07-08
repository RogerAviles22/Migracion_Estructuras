/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package migracionproyecto;

import Modelo.Entrada;
import Modelo.Migrante;
import Modelo.Registrador;
import Modelo.RegistroMigratorio;
import java.util.HashMap;
import java.util.LinkedList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

/**
 *
 * @author Francisco
 */
public class DatosDeBusqueda {
    TableView<RegistroMigratorio> tabla;
    VBox root;
    private Migrante migrante;
    private String filtro;
    
    public DatosDeBusqueda(String filtro,Migrante migrante){
        this.migrante=migrante;
        this.filtro=filtro;
        root=new VBox();
        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(140));
        root.setSpacing(50);
        Image image = new Image ("/Recursos/mapaEcuador.png");
        root.setBackground(new Background(new BackgroundImage(image,BackgroundRepeat.REPEAT,
                                                                  BackgroundRepeat.REPEAT,
                                                                  BackgroundPosition.CENTER,
                                                                  BackgroundSize.DEFAULT)));
        body();
        crearTabla(filtro,migrante.getCedula());
        
    }
    public void crearEncabezado(){
        HBox head= new HBox();
        Text t = new Text ("ESTOS SON SUS DATOS MIGRATORIOS");
        t.setFont(Font.font ("Verdana", 20));
        t.setFill(Color.BLACK);
        head.getChildren().add(t);
        root.getChildren().add(t);
        head.setAlignment(Pos.CENTER);
        head.setFillHeight(true);
        
    }
    private void crearTabla(String filtro,String cedula){
        
        TableColumn<RegistroMigratorio,String> cedulaColumns= new TableColumn<>("Medio de Transportacion");
        cedulaColumns.setMaxWidth(200);
        cedulaColumns.setCellValueFactory(new PropertyValueFactory<>("medioTrans"));
        
        TableColumn<RegistroMigratorio,String> nombreColumns= new TableColumn<>("Fecha");
        nombreColumns.setMaxWidth(200);
        nombreColumns.setCellValueFactory(new PropertyValueFactory<>("fecha"));
        
        TableColumn<Entrada,String> apellidoColumns= new TableColumn<>("Ciudad");
        apellidoColumns.setMaxWidth(200);
        apellidoColumns.setCellValueFactory(new PropertyValueFactory<>("ciudad"));
        
        TableColumn<Entrada,String> sexoColumns= new TableColumn<>("Pais");
        sexoColumns.setMaxWidth(100);
        sexoColumns.setCellValueFactory(new PropertyValueFactory<>("pais"));
        
        TableColumn<Entrada,String> nacionalidadColumns= new TableColumn<>("Continente");
        nacionalidadColumns.setMaxWidth(200);
        nacionalidadColumns.setCellValueFactory(new PropertyValueFactory<>("continente"));
        
        tabla=new TableView<>();
        tabla.setItems(filtrosData(filtro,cedula));
        tabla.getColumns().addAll(cedulaColumns,nombreColumns);
        //tabla.getColumns(apellidoColumns,sexoColumns,nacionalidadColumns);
        
        tabla.setMaxSize(350, 350);
        seleccionDato();
        root.getChildren().add(tabla);
        root.getChildren().add(back());
    }
    
    public ObservableList<RegistroMigratorio> filtrosData(String filtro,String cedula){
        ObservableList<RegistroMigratorio> data= FXCollections.observableArrayList();
        HashMap<Migrante,HashMap<String,LinkedList<RegistroMigratorio>>> run=Registrador.leerArchivo();
        run.forEach((k,v)->{
            if(k.getCedula().equals(cedula)){
                HashMap<String,LinkedList<RegistroMigratorio>> p=v;
                if(p.keySet().contains(filtro)){
                p.get(filtro).forEach((e)->{
                    data.add(e);
                });
            }
        } 
    });
            
        return data;
    }
    private HBox back(){
        HBox hb = new HBox();
        Image image = new Image(getClass().getResourceAsStream("/Recursos/back.png"));
        ImageView view = new ImageView(image);
        Button back = new Button();
        back.setBackground(Background.EMPTY);
        back.setContentDisplay(ContentDisplay.TOP);
        back.setGraphic(view);
        back.setOnAction(e->{
            SistemaBusquedaVista p = new SistemaBusquedaVista();
            MigracionProyecto.scene.setRoot(p.getRoot());
        });
        hb.getChildren().add(back);
        return hb;
    }

    private void seleccionDato(){
        tabla.setRowFactory(eventoP->{
        TableRow<RegistroMigratorio> row= new TableRow<>();
        row.setOnMouseClicked((evento)->{
        if (!row.isEmpty() && evento.getButton()==MouseButton.PRIMARY 
             && evento.getClickCount() == 2) {
             RegistroMigratorio registro=row.getItem();
             if(registro!=null && migrante!=null && filtro!=null){
                 Datos dato= new Datos(registro,migrante,filtro);
                 MigracionProyecto.scene.setRoot(dato.getRoot());
                
             }
             
        }
    });
        return row;
        });
        
}
    public VBox getRoot() {
        return root;
    }

    public void setRoot(VBox root) {
        this.root = root;
    }
    
    
    public void body(){
        crearEncabezado();
    }
}
