/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package br.com.fatec.Controller;

import br.com.fatec.CadastroPedido;
import br.com.fatec.DAO.PedidoDAO;
import br.com.fatec.Menu;
import br.com.fatec.model.Pedido;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author juroc
 */
public class FXML_cadastroPedidosController implements Initializable {

    @FXML
    private TextField txt_idPedido;
    private TextField txtNomeCli_Ped;
    @FXML
    private TextField txtCelular;
    @FXML
    private ComboBox<?> cbNomeProd_Ped;
    @FXML
    private TextField txtObsPed;
    @FXML
    private Button btnIncluir_Ped;
    @FXML
    private Button btnPesquisar_Ped;
    @FXML
    private Button btnAlterar_Ped;
    @FXML
    private Button btnExcluir_Ped;
    @FXML
    private Button btnMenu_Ped;
    @FXML
    private DatePicker txtDataPedido;
    @FXML
    private ComboBox<?> cb_statusPedido;
    @FXML
    private TextField txtNomeCliente;
    @FXML
    private TextField txtPreco;
    @FXML
    private TextField txtCPF;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void btnMenu_Ped_click(ActionEvent event) throws IOException {
        CadastroPedido cadPed = new CadastroPedido();
        cadPed.tela.close();
    }

    @FXML
    private void btnIncluir_Ped_click(ActionEvent event) throws SQLException {
        PedidoDAO pedidoDAO = new PedidoDAO();
        if (pedidoDAO.insere(moveDadosTelaModel())) {
            Alert alerta = new Alert(Alert.AlertType.INFORMATION,
                    "Pedido incluido com sucesso",
                    ButtonType.OK);
            alerta.showAndWait();
            
        }
        //pedidoDAO.insere(model)
    }

    private Pedido moveDadosTelaModel() {
        Pedido p = new Pedido();
        p.setCelular(txtCelular.getText());
        p.setDataPedido(txtDataPedido.getValue());
        p.setNomeCliente(txtNomeCliente.getText());
        p.setObsPedido(txtObsPed.getText());
        p.setPedido(Integer.parseInt(txt_idPedido.getText()));
        p.setPreco(Float.parseFloat(txtPreco.getText()));
        //p.setStatus((String) cb_statusPedido.getValue());
        return p;
    }

}
