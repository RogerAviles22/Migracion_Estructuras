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
import Modelo.Salida;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Set;
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
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;import javafx.scene.control.TextField;
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
 * @author Rogencio
 */
public class SistemaBusquedaVista {
    private TableView<Migrante> tabla;
    private VBox root;
    private ComboBox selector;
    private DatePicker dp= new DatePicker();
    TextField texto= new TextField();
    TextField textoDestino= new TextField();
    TextField textoCanton=new TextField();
    
    
    public SistemaBusquedaVista(){
        root=new VBox();
        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(50));
        body();
        Image image = new Image ("/Recursos/mapa2.png");
        root.setBackground(new Background(new BackgroundImage(image,BackgroundRepeat.REPEAT,
                                                                  BackgroundRepeat.REPEAT,
                                                                  BackgroundPosition.DEFAULT,
                                                                  BackgroundSize.DEFAULT)));
        
        root.getChildren().add(back());
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
        else if(selector.getValue().equals("Destinos")){
            TextoDestino();
        }
        else if(selector.getValue().equals("Canton de Origen")){
            TextoCanton();
        }else{
            crearTextos();
        }
        botonCheck();
        });
        controlador.getChildren().addAll(crear,opciones);
        root.getChildren().add(controlador);
        
        
    }
    private void TextoDestino(){
            textoDestino.setAlignment(Pos.CENTER);
            textoDestino.setMaxWidth(250);
            opciones.getChildren().add(textoDestino);
            
            
    }
    private void TextoCanton(){
            textoCanton.setAlignment(Pos.CENTER);
            textoCanton.setMaxWidth(250);
            opciones.getChildren().add(textoCanton);
            
            
    }
    
    private void crearTextos(){
            
            texto.setText(null);
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
            root.getChildren().remove(tabla);
            if(dp.getValue()!=null){
                crearTablaOrigenes(dp.getValue().toString());
                dp.setValue(null);
            }
            else if(textoDestino.getText()!=null){
                crearTablaOrigenes(textoDestino.getText());
                textoDestino.setText(null);
                 //OJASO
            }
            else if(textoCanton.getText()!=null){
                crearTablaOrigenes(textoCanton.getText());
                 textoCanton.setText(null);//OJASO
            }
            else if(texto.getText()!=null){
                crearTablaOrigenes(texto.getText());
                texto.setText(null);//OJASO
                 }
           
                 
        
        });
        opciones.getChildren().add(check);
    }
    
    
    public ObservableList<Migrante> filtrosOrigenes(String filtro){
        ObservableList<Migrante> data= FXCollections.observableArrayList();
        HashMap<Migrante,HashMap<String,LinkedList<RegistroMigratorio>>> run=Registrador.leerArchivo();
        Set<Migrante>migrantes=new HashSet<>();
        run.forEach((k,v)->{
        if ((k.getNacionalidad().getCiudad().equals(filtro))){
            migrantes.add(k);
        }else if (k.getNacionalidad().getCanton().equals(filtro)){
            migrantes.add(k);
        }}
        );
        migrantes.forEach(evento->{
            data.add(evento);
        });
        
            
        
    
        return data;
    }
    public ObservableList<Migrante> filtrosData(String filtro){
        ObservableList<Migrante> data= FXCollections.observableArrayList();
        HashMap<Migrante,HashMap<String,LinkedList<RegistroMigratorio>>> run=Registrador.leerArchivo();
        run.forEach((k,v)->{
            if(!(data.contains(k))){
            HashMap<String,LinkedList<RegistroMigratorio>> p=v;
            v.values().forEach((e)->{
            e.forEach((registro)->{
            if(registro.getFecha().equals(filtro)){
                data.add(k);
                
            }else if(registro instanceof Salida){
                Salida salida=(Salida) registro;
                if(salida.getCiudadDestino().equals(filtro)){
                    if(!(data.contains(k))){
                     data.add(k);   
                    }
                    
                }
            }
            });
            
        });   
        }    
            });
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
        if(dp.getValue()!=null && dp.getValue().toString().equals(filtro)){
            tabla.setItems(filtrosData(filtro));
        }
        else if(textoDestino.getText()!=null && textoDestino.getText().equals(filtro)){ 
            tabla.setItems(filtrosData(filtro));
        }
        else if(textoCanton.getText()!=null && textoCanton.getText().equals(filtro)){
                tabla.setItems(filtrosOrigenes(filtro));
        }else if (texto.getText()!=null && texto.getText().equals(filtro)){
            tabla.setItems(filtrosOrigenes(filtro));
        }
        tabla.getColumns().addAll(cedulaColumns,nombreColumns,apellidoColumns,sexoColumns,nacionalidadColumns,fechaNacimientoColumns,tipoColumns);
        root.getChildren().add(tabla);
        
        seleccionDato();
    }
    
    private DatePicker crearCalender(){
        
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
    
    private void seleccionDato(){
        tabla.setRowFactory(eventoP->{
        TableRow<Migrante> row= new TableRow<>();
        row.setOnMouseClicked((evento)->{
        if (!row.isEmpty() && evento.getButton()==MouseButton.PRIMARY 
             && evento.getClickCount() == 2) {
              Migrante mig=row.getItem();
              miniPaneBusqueda mini= new miniPaneBusqueda(mig);
              mini.mostrarVentana();
            //Migrante clickedRow = row.getItem();
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
    
}
/**
 * || k.getNacionalidad().getCanton().equals(filtro)
 * 
 */