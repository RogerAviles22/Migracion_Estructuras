/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package migracionproyecto;

import Controlador.VentanaEmergente;
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
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 *
 * @author Francisco
 */
public class SistemaRegistroVista {
    private GridPane root;
    private Button confirmarRegistro;
    private Button retrocederRegistro;
    private Button vaciarRegistro;
    private  Migrante migrante;///
    private Registrador agente=new Registrador();///
    private  RegistroMigratorio registro;///
    private GridPane root2;
    private FlowPane labels;
    
    //CREE ESTAS INSTANCIAS PARA PODER UTILIZAR LOS VALORES DE LOS TEXTFIELDS LUEGO AL CREAR EL MIGRANTE Y SU REGISTRO MIGRATORIO
    private TextField cedulaT;
    private TextField nombreT;
    private TextField apellidoT;
    private ComboBox sexoT;
    private TextField fechaNacimientoT;
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
    
    
    public SistemaRegistroVista() {
        root = new GridPane();
        crearPanel();
        root.setHgap(5);
        root.setVgap(5);
        root.paddingProperty();
        root.setPadding(new Insets(20));
        //Image image = new Image ("/Recursos/migranteRegistro.jpg");
        /*root.setBackground(new Background(new BackgroundImage(image,BackgroundRepeat.REPEAT,
                                                                  BackgroundRepeat.REPEAT,
                                                                  BackgroundPosition.CENTER,
                                                                  BackgroundSize.DEFAULT)));*/
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
        VBox encabeza= new VBox();
        Label encabezado= new Label();
        encabezado.setText("BIENVENIDO AL SISTEMA DE REGISTRO DE UN MIGRANTE, "
                );
        Label encabezado2= new Label("POR FAVOR ESCRIBIR LOS DATOS CORRECTAMENTE ANTES DE GUARDAR");
        encabeza.getChildren().addAll(encabezado,encabezado2);
        root.add(encabeza, 0, 0);
    }
    private void crearLabels(){
        labels= new FlowPane();
        root2= new GridPane();
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
    private void crearTextFields(){
        
        cedulaT= new TextField();
        cedulaT.setOnKeyPressed(e->{
            comprobarMigrante(cedulaT.getText());
        });
        nombreT= new TextField();
        apellidoT= new TextField();
        sexoT= new ComboBox();
        sexoT.getItems().addAll("Hombre","Mujer","LGBTI");
        sexoT.setPromptText("Hombre");
        fechaNacimientoT= new TextField();
        fechaNacimientoT.setText("mm/dd/yyyy");
        paisOrigenT= new TextField();
        ciudadOrigenT= new TextField();
        cantonOrigenT= new TextField();
        continenteOrigenT= new TextField();
        tipoT= new ComboBox();
        tipoT.getItems().addAll("Discapacidad","3era Edad","Normal");
        tipoT.setPromptText("Normal");
        categoriaT= new ComboBox();
        categoriaT.getItems().addAll("Entrada","Salida");
        categoriaT.setPromptText("Entrada");
        categoriaT.setOnAction(e->{
            
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
        root2.add(fechaNacimientoT, 1, 9, 1, 1);
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
    
    private void SesionEntrada(){
        
        Label medioTrans= new Label("Medio de Transportacion: ");
        Label paisEntrada= new Label("Pais al que Ingresa: ");
        Label ciudadEntrada= new Label("Ciudad al que Ingresa: ");
        Label continenteEntrada= new Label("Continente al que Ingresa ");
        medioTransT= new TextField();
        paisT= new TextField();
        ciudadT= new TextField();
        continenteT= new TextField();
        
        root2.add(medioTrans, 0, 25, 1, 1);
        root2.add(paisEntrada, 0, 27,1,1);
        root2.add(ciudadEntrada, 0, 29,1,1);
        root2.add(continenteEntrada, 0, 31,1,1);
        root2.add(medioTransT, 1, 25, 1, 1);
        root2.add(paisT, 1, 27,1,1);
        root2.add(ciudadT, 1, 29,1,1);
        root2.add(continenteT, 1, 31,1,1);
        
    }
    
    //EN ESTA SESION SE CREA LA SALIDA DEL EMIGRANTE
    private void SesionSalida(){
        
        Label medioTrans= new Label("Medio de Transportacion: ");
        Label paisSalida= new Label("Pais al que se dirige ");
        Label ciudadSalida= new Label("Ciudad a la que se dirige: ");
        Label continenteSalida= new Label("Continente al que se dirige ");
        medioTransT= new TextField();
        paisT= new TextField();
        ciudadT= new TextField();
        continenteT= new TextField();
        root2.add(medioTrans, 0, 25, 1, 1);
        root2.add(paisSalida, 0, 27,1,1);
        root2.add(ciudadSalida, 0, 29,1,1);
        root2.add(continenteSalida, 0, 31,1,1);
        root2.add(medioTransT, 1, 25, 1, 1);
        root2.add(paisT, 1, 27,1,1);
        root2.add(ciudadT, 1, 29,1,1);
        root2.add(continenteT, 1, 31,1,1);
    }
    
    // EN ESTA SESION SE EJECUTA LOS BOTONES
    public void sesionButones(){
        HBox botones= new HBox();
        confirmarRegistro=new Button("CONFIRMAR");
        confirmarRegistro.setOnAction(e->{
             Nacionalidad nacionalidad= new Nacionalidad(paisOrigenT.getText(),continenteOrigenT.getText(),ciudadOrigenT.getText(),cantonOrigenT.getText());
             migrante=new Migrante(cedulaT.getText(),nombreT.getText(),apellidoT.getText(),sexoT.getPromptText(),nacionalidad,fechaNacimientoT.getText(),tipoT.getPromptText());
             registro=new Salida(medioTransT.getText(),fechaActualT.getText(),ciudadT.getText(),paisT.getText(),continenteT.getText());
             agente.EscribirArchivo(categoriaT.getPromptText(), migrante, registro);
             VentanaEmergente.crearMigrante();
             limpiarData();
        });
        vaciarRegistro= new Button("VACIAR DATA");
        vaciarRegistro.setOnAction(e->{limpiarData();});
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
        botones.setSpacing(30);
        root.add(botones, 0, 3);
    }

    //EN ESTA SESION SIRVE PARA LIMPIAR LOS CAMPOS
    private void limpiarData(){
        cedulaT.clear();
        nombreT.clear();
        apellidoT.clear();
        fechaNacimientoT.setText("mm/dd/yyyy");
        paisOrigenT.clear();
        tipoT.setPromptText("Normal");
        ciudadOrigenT.clear();
        cantonOrigenT.clear();
        continenteOrigenT.clear();
        fechaActualT.setText(LocalDate.now().toString());
        medioTransT.clear();
        paisT.clear();
        ciudadT.clear();
        continenteT.clear();
        
    }
    
    private boolean comprobarMigrante(String cedula){
        agente.leerArchivo().forEach((k,v)->{
            if(k.getCedula().equals(cedula)){
                cedulaT.setText(k.getCedula());
                nombreT.setText(k.getNombre());
                apellidoT.setText(k.getApellido());
                fechaNacimientoT.setText(k.getFechaNacimiento());
                sexoT.setPromptText(k.getSexo());
                tipoT.setPromptText(k.getTipo());
                paisOrigenT.setText(k.getNacionalidad().getPais());
                ciudadOrigenT.setText(k.getNacionalidad().getCiudad());
                cantonOrigenT.setText(k.getNacionalidad().getCanton());
                continenteOrigenT.setText(k.getNacionalidad().getContinente());
                
            }
            
        });
        return true;
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
    