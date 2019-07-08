/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package migracionproyecto;

import Controlador.VentanaEmergente;
import Modelo.Entrada;
import Modelo.Migrante;
import Modelo.Nacionalidad;
import Modelo.Registrador;
import Modelo.RegistroMigratorio;
import Modelo.Salida;
import java.time.LocalDate;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 *
 * @author Francisco
 */
public class Datos {
    private GridPane root;
    private Button ModificarRegistro;
    private Button AtrasRegistro;
    private  Migrante migrante;///
    private Registrador agente=new Registrador();///
    private  RegistroMigratorio registro;///
    private GridPane root2;
    private FlowPane labels;
    private DatePicker dp;
    private String filtro;
    
    private TextField cedulaT;
    private TextField nombreT;
    private TextField apellidoT;
    private ComboBox sexoT;
    private LocalDate fechaNacimientoT;
    private TextField paisOrigenT;
    private TextField ciudadOrigenT;
    private TextField cantonOrigenT;
    private TextField continenteOrigenT;
    private ComboBox tipoT;
    private ComboBox categoriaT;
    private TextField fechaActualT;
    private TextField medioTransT;
    private TextField paisT;
    private TextField ciudadT;
    private TextField continenteT;
    
    private  Label medioTrans;
    private  Label pais;
    private  Label ciudad;
    private  Label continente;
    
    
    public Datos(RegistroMigratorio registro, Migrante migrante,String filtro){
        root=new GridPane();
        
        this.registro=registro;
        this.migrante=migrante;
        this.filtro=filtro;
        crearPanel();
        root.setHgap(5);
        root.setVgap(5);
        root.paddingProperty();
        root.setPadding(new Insets(20));
        Image image = new Image ("/Recursos/mapa2.png");
        root.setBackground(new Background(new BackgroundImage(image,BackgroundRepeat.REPEAT,
                                                                  BackgroundRepeat.REPEAT,
                                                                  BackgroundPosition.CENTER,
                                                                  BackgroundSize.DEFAULT)));
        root.setHgap(25);
        root.setVgap(15);
    }
     private void crearPanel(){
        crearLabel();
        crearLabels();
        crearTextFields();
        sesionButones();
        llenarMigrante();
       

    }
     private void crearLabel(){
        VBox encabeza= new VBox();
        Label encabezado= new Label();
        encabezado.setText("BIENVENIDO AL SISTEMA DE MODIFICACION DE DATOS DE UN MIGRANTE, "
                );
        Label encabezado2= new Label("POR FAVOR ESCRIBIR LOS DATOS CORRECTAMENTE ANTES DE MODIFICAR");
        encabeza.getChildren().addAll(encabezado,encabezado2);
        root.add(encabeza, 0, 0);
    }
     
     private void crearTextFields(){
        
        cedulaT= new TextField();
        nombreT= new TextField();
        apellidoT= new TextField();
        sexoT= new ComboBox();
        sexoT.getItems().addAll("Hombre","Mujer","LGBTI");
        sexoT.setPromptText("Hombre");
        dp= new DatePicker();
        dp.setOnAction((e)->{
           fechaNacimientoT= dp.getValue();
        });
        paisOrigenT= new TextField();
        ciudadOrigenT= new TextField();
        cantonOrigenT= new TextField();
        continenteOrigenT= new TextField();
        tipoT= new ComboBox();
        tipoT.getItems().addAll("Discapacidad","3era Edad","Normal");
        tipoT.setPromptText("Normal");
        categoriaT=new ComboBox();
        categoriaT.getItems().addAll("Entrada","Salida");
        
        categoriaT.setValue(filtro);
        if(filtro.equals("Entrada")){
            SesionEntrada();
        }else{
            SesionSalida();
        }
        categoriaT.setOnAction(e->{
            eliminarSesiones();
            if(categoriaT.getValue().equals("Entrada")){
                SesionEntrada();
            }else{
                SesionSalida();
            }
        });
        fechaActualT=new TextField();
        fechaActualT.setText(LocalDate.now().toString());
        
        root2.add(cedulaT, 1, 1, 1, 1);
        root2.add(nombreT, 1, 3, 1, 1);
        root2.add(apellidoT, 1, 5, 1, 1);
        root2.add(sexoT, 1, 7, 1, 1);
        root2.add(dp, 1, 9, 1, 1);
        root2.add(paisOrigenT, 1, 11, 1, 1);
        root2.add(ciudadOrigenT, 1, 13, 1, 1);
        root2.add(cantonOrigenT, 1, 15, 1, 1);
        root2.add(continenteOrigenT, 1, 17, 1, 1);
        root2.add(tipoT, 1, 19, 1, 1);
        root2.add(categoriaT, 1, 21,1,1);
        root2.add(fechaActualT, 1, 23, 1, 1);
        root2.setHgap(5);
        root2.setVgap(5);
        
    
    }
    

    private void crearLabels(){
        labels= new FlowPane();
        root2= new GridPane();
        Label cedula= new Label("Cedula:");
        Label nombre= new Label("Nombre:");
        Label apellido= new Label("Apellido:");
        Label sexo= new Label("Sexo:");
        Label fechaNacimiento= new Label("Fecha Nacimiento:");
        Label paisOrigen= new Label("Pais de Origen:");
        Label ciudadOrigen= new Label("Provincia de Origen:");
        Label cantonOrigen= new Label("Canton de Origen: ");
        Label continenteOrigen= new Label("Continente de Origen");
        Label tipo= new Label("Tipo: ");
        Label categoria= new Label("ENTRADA/SALIDA: ");
        Label fechaActual= new Label("Fecha Migratoria");
        
        root2.add(cedula, 0, 1, 1, 1);
        root2.add(nombre, 0, 3,1, 1);
        root2.add(apellido, 0, 5,1,1);
        root2.add(sexo, 0, 7, 1, 1);
        root2.add(fechaNacimiento, 0,9, 1, 1);
        root2.add(paisOrigen, 0, 11, 1, 1);
        root2.add(ciudadOrigen, 0, 13, 1, 1);
        root2.add(cantonOrigen, 0, 15, 1, 1);
        root2.add(continenteOrigen, 0, 17, 1, 1);
        root2.add(tipo, 0, 19, 1, 1);
        root2.add(categoria, 0, 21,1,1);
        root2.add(fechaActual, 0, 23, 1, 1);
        root2.setHgap(5);
        root2.setVgap(5);
        labels.getChildren().add(root2);
        root.add(labels, 0, 2);
        
        
    
    }
    private void SesionEntrada(){
        
        medioTrans= new Label("Medio de Transportacion: ");
        pais= new Label("Pais al que Ingresa: ");
        ciudad= new Label("Ciudad al que Ingresa: ");
        continente= new Label("Continente al que Ingresa ");
        medioTransT= new TextField();
        paisT= new TextField();
        ciudadT= new TextField();
        continenteT= new TextField();
        
        root2.add(medioTrans, 0, 25, 1, 1);
        root2.add(pais, 0, 27,1,1);
        root2.add(ciudad, 0, 29,1,1);
        root2.add(continente, 0, 31,1,1);
        root2.add(medioTransT,1, 25, 1, 1);
        root2.add(paisT, 1, 27,1,1);
        root2.add(ciudadT, 1, 29,1,1);
        root2.add(continenteT, 1, 31,1,1);
        
    }
    
    //EN ESTA SESION SE CREA LA SALIDA DEL EMIGRANTE
    private void SesionSalida(){
        
        medioTrans= new Label("Medio de Transportacion: ");
        pais= new Label("Pais al que se dirige ");
        ciudad= new Label("Ciudad a la que se dirige: ");
        continente= new Label("Continente al que se dirige ");
        medioTransT= new TextField();
        paisT= new TextField();
        ciudadT= new TextField();
        continenteT= new TextField();
        root2.add(medioTrans, 0, 25, 1, 1);
        root2.add(pais, 0, 27,1,1);
        root2.add(ciudad, 0, 29,1,1);
        root2.add(continente, 0, 31,1,1);
        root2.add(medioTransT, 1, 25, 1, 1);
        root2.add(paisT, 1, 27,1,1);
        root2.add(ciudadT, 1, 29,1,1);
        root2.add(continenteT, 1, 31,1,1);
    }
    
    private boolean llenarMigrante(){
                System.out.println(migrante.getCedula());
                cedulaT.setText(migrante.getCedula());
                nombreT.setText(migrante.getNombre());
                apellidoT.setText(migrante.getApellido());
                dp.setValue(LocalDate.parse(migrante.getFechaNacimiento()));
                sexoT.setValue(migrante.getSexo());
                tipoT.setValue(migrante.getTipo());
                paisOrigenT.setText(migrante.getNacionalidad().getPais());
                ciudadOrigenT.setText(migrante.getNacionalidad().getCiudad());
                cantonOrigenT.setText(migrante.getNacionalidad().getCanton());
                continenteOrigenT.setText(migrante.getNacionalidad().getContinente());
                fechaActualT.setPromptText(registro.getFecha());
                medioTransT.setPromptText(registro.getMedioTrans());
                if(registro instanceof Entrada){
                    Entrada entrada=(Entrada ) registro;
                    paisT.setPromptText(entrada.getPaisIngreso());
                    ciudadT.setPromptText(entrada.getCiudadIngreso());
                    continenteT.setPromptText(entrada.getContinenteIngreso());
                }else{
                    Salida salida=(Salida) registro;
                    paisT.setPromptText(salida.getPaisDestino());
                    ciudadT.setPromptText(salida.getCiudadDestino());
                    continenteT.setPromptText(salida.getContinenteDestino());
                }
                    
        return true;
    }
    public void sesionButones(){
        HBox botones= new HBox();
        ModificarRegistro=new Button("CONFIRMAR");
        ModificarRegistro.setOnAction(e->{
            migrante.setCedula(cedulaT.getText());
            migrante.setNombre(nombreT.getText());
            migrante.setApellido(apellidoT.getText());
            migrante.setSexo(sexoT.getValue().toString());
            Nacionalidad nacionalidad=new Nacionalidad(paisOrigenT.getText(),continenteOrigenT.getText(),ciudadOrigenT.getText(),cantonOrigenT.getText());
            migrante.setNacionalidad(nacionalidad);
            registro.setFecha(fechaActualT.getText());
            registro.setMedioTrans(medioTransT.getText());
            if(registro instanceof Entrada){
                Entrada entrada = (Entrada ) registro;
                entrada.setCiudadIngreso(ciudadT.getText());
                entrada.setContinenteIngreso(continenteT.getText());
                entrada.setPaisIngreso(paisT.getText());
            }else{
                Salida salida = (Salida) registro;
                salida.setCiudadDestino(ciudadT.getText());
                salida.setContinenteDestino(continenteT.getText());
                salida.setPaisDestino(paisT.getText());
            }
            VentanaEmergente.modificarMigrante();
            SistemaMenuPrincipal p = new SistemaMenuPrincipal();
            MigracionProyecto.scene.setRoot(p.getRoot());
        });
        Button Eliminar= new Button("ELIMINAR");
        Eliminar.setOnAction((e)->{
            SistemaMenuPrincipal p = new SistemaMenuPrincipal();
            MigracionProyecto.scene.setRoot(p.getRoot());
        });
        Image image = new Image(getClass().getResourceAsStream("/Recursos/back.png"));
        ImageView view = new ImageView(image);
        AtrasRegistro= new Button();
        AtrasRegistro.setBackground(Background.EMPTY);
        AtrasRegistro.setContentDisplay(ContentDisplay.TOP);
        AtrasRegistro.setGraphic(view);
        AtrasRegistro.setOnAction(e->{
            SistemaMenuPrincipal p = new SistemaMenuPrincipal();
            MigracionProyecto.scene.setRoot(p.getRoot());
        });
        botones.getChildren().addAll(ModificarRegistro,Eliminar,AtrasRegistro);
        botones.setAlignment(Pos.CENTER);
        botones.setPadding(Insets.EMPTY);
        botones.setSpacing(30);
        root.add(botones, 0, 3);
    }
    private void eliminarSesiones(){
        root2.getChildren().removeAll(medioTrans,pais,ciudad,continente,medioTransT,paisT,ciudadT,continenteT);
    }

    public GridPane getRoot() {
        return root;
    }

    public void setRoot(GridPane root) {
        this.root = root;
    }
    
}
