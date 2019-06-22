/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package migracionproyecto;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 *
 * @author Rogencio
 */
public class MigracionProyecto extends Application {
    public static Scene scene;
    
    @Override
    public void start(Stage primaryStage) {
        SistemaTurnosVista stv= new SistemaTurnosVista();
        
        
        scene = new Scene(new Group(), 800, 600);
        scene.setRoot(stv.getRoot());
        
        Image image= new Image("/Recursos/Logo.png");
        primaryStage.getIcons().add(image);
        
        primaryStage.setTitle("Migración");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
