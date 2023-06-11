/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package br.com.fatec.Controller;

import br.com.fatec.ConsultaAvancada;
import br.com.fatec.Menu;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author juroc
 */
public class FXML_consultaAvancadaController implements Initializable {

    @FXML
    private Button btn_menuPed;
    @FXML
    private ComboBox<?> cb_Selecione;
    @FXML
    private Button btn_PesquisarPed;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void btn_menuPed_click(ActionEvent event) throws IOException {
        ConsultaAvancada conAva = new ConsultaAvancada();
        conAva.tela.close();
    }
}
