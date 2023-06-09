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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
    private ComboBox<String> cb_statusPedido;
    @FXML
    private TextField txtNomeCliente;
    @FXML
    private TextField txtPreco;
    @FXML
    private TextField txtCPF;

    private ObservableList<String> status
            = FXCollections.observableArrayList();
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        preencheComboStatus();
    }

    public void preencheComboStatus(){
        status.add("Finalizado");
        status.add("Cancelado");
        status.add("Em processo");
        cb_statusPedido.setItems(status);
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
        
        p.setStatus(cb_statusPedido.getValue());
        return null;
    }

}
