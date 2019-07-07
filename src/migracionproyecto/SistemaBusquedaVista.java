/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package migracionproyecto;

import Modelo.Migrante;
import Modelo.Registrador;
import Modelo.RegistroMigratorio;
import java.time.LocalDate;
import java.util.HashMap;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.HBox;
;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

/**
 *
 * @author Rogencio
 */
public class SistemaBusquedaVista {
    private TableView<Migrante> tabla;
    private VBox root;
    private ComboBox selector;
    private DatePicker dp;
    TextField texto;
    
    
    public SistemaBusquedaVista(){
        root=new VBox();
        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(50));
        body();
        
    }
    
    public void body(){
        crearEncabezado();
        crearFiltros();
    }
    
   
    
    public void crearEncabezado(){
        HBox head= new HBox();
        Text t = new Text ("BIENVENIDO AL SISTEMA DE BUSQUEDA MIGRATORIO");
        t.setFont(Font.font ("Verdana", 20));
        t.setFill(Color.BROWN);
        t.setUnderline(true);
        head.getChildren().add(t);
        root.getChildren().add(t);
        head.setAlignment(Pos.CENTER);
        head.setFillHeight(true);
        
    }
    
    HBox opciones;
    public void crearFiltros(){
        VBox controlador= new VBox();
        HBox crear= new HBox();
        opciones= new HBox();
        selector=new ComboBox();
        selector.getItems().addAll("Fecha","Provincia de Origen","Canton de Origen","Destinos");
        selector.setPromptText("Fecha");
        Label label= new Label("POR FAVOR, ELIJA SU METODO DE BUSQUEDA:  ");
        crear.getChildren().addAll(label,selector);
        crear.setAlignment(Pos.CENTER);
        crear.setPadding(new Insets(50));
        opciones.setAlignment(Pos.CENTER);
        opciones.setPadding(new Insets(20));
        selector.setOnAction((e)->{
            opciones.getChildren().clear();
            
        if(selector.getValue().equals("Fecha")){
            opciones.getChildren().add(crearCalender());
        }
        else{crearTextos();
        }
        botonCheck();
        });
        controlador.getChildren().addAll(crear,opciones);
        root.getChildren().add(controlador);
        
        
    }
    
    private void crearTextos(){
            texto= new TextField();
            texto.setAlignment(Pos.CENTER);
            texto.setMaxWidth(250);
            opciones.getChildren().add(texto);
        }
    
    private void botonCheck(){
        Image image = new Image(getClass().getResourceAsStream("/Recursos/check.png"));
        ImageView view = new ImageView(image);
        Button check = new Button();
        view.setFitHeight(50);
        view.setFitWidth(50);
        check.setBackground(Background.EMPTY);
        check.setContentDisplay(ContentDisplay.TOP);
        check.setGraphic(view);
        check.setOnAction(e->{
            crearTablaOrigenes(texto.getText());
        });
        opciones.getChildren().add(check);
    }
    
    
    public ObservableList<Migrante> filtrosOrigenes(String filtro){
        ObservableList<Migrante> data= FXCollections.observableArrayList();
        HashMap<Migrante,HashMap<String,RegistroMigratorio>> run=Registrador.leerArchivo();
        run.forEach((k,v)->{
        if (k.getNacionalidad().getCiudad().equals(filtro)){
            data.add(k);
        }});
        return data;
    }
    private void crearTablaOrigenes(String filtro){
        
        TableColumn<Migrante,String> cedulaColumns= new TableColumn<>("Cedula");
        cedulaColumns.setMaxWidth(200);
        cedulaColumns.setCellValueFactory(new PropertyValueFactory<>("cedula"));
        
        TableColumn<Migrante,String> nombreColumns= new TableColumn<>("Nombre");
        nombreColumns.setMaxWidth(200);
        nombreColumns.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        
        TableColumn<Migrante,String> apellidoColumns= new TableColumn<>("Apellido");
        apellidoColumns.setMaxWidth(200);
        apellidoColumns.setCellValueFactory(new PropertyValueFactory<>("apellido"));
        
        TableColumn<Migrante,String> sexoColumns= new TableColumn<>("Sexo");
        sexoColumns.setMaxWidth(100);
        sexoColumns.setCellValueFactory(new PropertyValueFactory<>("sexo"));
        
        TableColumn<Migrante,String> nacionalidadColumns= new TableColumn<>("Nacionalidad");
        nacionalidadColumns.setMaxWidth(200);
        nacionalidadColumns.setCellValueFactory(new PropertyValueFactory<>("nacionalidad"));
        
        TableColumn<Migrante,String> fechaNacimientoColumns= new TableColumn<>("FechaNacimiento");
        fechaNacimientoColumns.setMaxWidth(100);
        fechaNacimientoColumns.setCellValueFactory(new PropertyValueFactory<>("fechaNacimiento"));
        
        TableColumn<Migrante,String> tipoColumns= new TableColumn<>("Tipo");
        tipoColumns.setMaxWidth(100);
        tipoColumns.setCellValueFactory(new PropertyValueFactory<>("tipo"));
        
        tabla=new TableView<>();
        tabla.setItems(filtrosOrigenes(filtro));
        tabla.getColumns().addAll(cedulaColumns,nombreColumns,apellidoColumns,sexoColumns,nacionalidadColumns,fechaNacimientoColumns,tipoColumns);
        root.getChildren().add(tabla);
    }
    public void filtroFecha(){
            
    }
    
    public void filtroCiudadOrigen(){
        
    }
    
    public void filtroCantonOrigen(){
        
    }
    
    public void filtroDestinos(){
        
    }
    
    private DatePicker crearCalender(){
        dp= new DatePicker();
        dp.setOnAction((e)->{
            LocalDate localdate=dp.getValue();
        });
        dp.setValue(LocalDate.now());
        return dp;
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
            SistemaMenuPrincipal p = new SistemaMenuPrincipal();
            MigracionProyecto.scene.setRoot(p.getRoot());
        });
        hb.getChildren().add(back);
        return hb;
    }

    public VBox getRoot() {
        return root;
    }

    public void setRoot(VBox root) {
        this.root = root;
    }
    
}

