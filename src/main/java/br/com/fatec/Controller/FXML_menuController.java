/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package br.com.fatec.Controller;

import br.com.fatec.CadastroCliente;
import br.com.fatec.CadastroPedido;
import br.com.fatec.CadastroProdutos;
import br.com.fatec.ConsultaAvancada;
import br.com.fatec.FornecedorStage;
import br.com.fatec.IngredientesStage;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author juroc
 */
public class FXML_menuController implements Initializable {

    @FXML
    private Button btnClientes;
    @FXML
    private Button btnPedidos;
    @FXML
    private Button btnProdutos;
    @FXML
    private Button btnConsultar;
    @FXML
    private Button btnFornecedores;
    @FXML
    private Button btnCadastrarIngredientes;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void btnClientes_click(ActionEvent event) throws IOException  {
        CadastroCliente cadCli = new CadastroCliente();
        cadCli.start(new Stage());
    }

    @FXML
    private void btnPedidos_click(ActionEvent event)throws IOException {
        CadastroPedido cadPed = new CadastroPedido();
        cadPed.start(new Stage());
    }

    @FXML
    private void btnProdutos_click(ActionEvent event)throws IOException {
        CadastroProdutos cadProd = new CadastroProdutos();
        cadProd.start(new Stage());
    }

    @FXML
    private void btnConsultar_click(ActionEvent event)throws IOException {
        ConsultaAvancada conAvan = new ConsultaAvancada();
        conAvan.start(new Stage());
    }

    @FXML
    private void btnFornecedores_click(ActionEvent event) throws IOException {
        FornecedorStage fornecedor = new FornecedorStage();
        fornecedor.start(new Stage());
    }

    @FXML
    private void btnCadastrarIngredientes_click(ActionEvent event) throws IOException {
        IngredientesStage ingre = new IngredientesStage();
        ingre.start(new Stage());
    }
    
}
