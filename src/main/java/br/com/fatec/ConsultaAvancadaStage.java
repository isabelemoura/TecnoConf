/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.fatec;

import br.com.fatec.Controller.FXML_consultaAvancadaController;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Isaque
 */
public class ConsultaAvancadaStage {
    public static Stage tela;
    
    public void start(Stage tela) throws IOException {
        setStage(tela);
        
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("FXML_consultaAvancada.fxml"));
        Parent root = fxmlLoader.load();
        FXML_consultaAvancadaController controler = fxmlLoader.getController();
        Scene scene = new Scene(root);
        
        tela.setScene(scene);
        tela.show();        

    }
    
    public static void setStage(Stage t) {
        tela = t;
    }
}
