/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insa.winkler.projettreillis.gui;

import fr.insa.winkler.projettreillis.Treillis;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author mariewinkler
 */
public class Main extends Application {
    @Override
    public void start(Stage stage) {
        Scene sc = new Scene(new MainPane(stage,new Treillis()),800,600);
        stage.setScene(sc);
          stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
    
}
