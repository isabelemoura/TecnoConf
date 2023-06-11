/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package br.com.fatec.Controller;

import br.com.fatec.ConsultaAvancadaStage;
import br.com.fatec.model.ConsultaAvancada;
import br.com.fatec.DAO.ConsultaDAO;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
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
    private ComboBox<String> cb_Selecione;
    @FXML
    private Button btn_PesquisarPed;
    @FXML
    private TableColumn<ConsultaAvancada, String> colCPF;
    @FXML
    private TableColumn<ConsultaAvancada, String> colNomeCliente;
    @FXML
    private TableColumn<ConsultaAvancada, String> colPreco;
    @FXML
    private TableColumn<ConsultaAvancada, String> colCelular;
    @FXML
    private TableColumn<ConsultaAvancada, String> colDataPedido;
    @FXML
    private TableColumn<ConsultaAvancada, String> colStatusPedido;
    @FXML
    private TableColumn<ConsultaAvancada, String> colObsPedido;
    @FXML
    private TableView<ConsultaAvancada> tblPedido;
    @FXML
    private TextField txtPesquisa;

    private String condicional = "";

    private ObservableList<String> pesquisas
            = FXCollections.observableArrayList("Nome do cliente", "CPF", "Status pedido", "Data do pedido");

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        cb_Selecione.setItems(pesquisas);

        colCPF.setCellValueFactory(
                new PropertyValueFactory<>("cpf"));

        colNomeCliente.setCellValueFactory(
                new PropertyValueFactory<>("nomeCliente"));

        colPreco.setCellValueFactory(
                new PropertyValueFactory<>("preco"));

        colCelular.setCellValueFactory(
                new PropertyValueFactory<>("celular"));

        colDataPedido.setCellValueFactory(
                new PropertyValueFactory<>("dataPedido"));

        colStatusPedido.setCellValueFactory(
                new PropertyValueFactory<>("statusPedido"));

        colObsPedido.setCellValueFactory(
                new PropertyValueFactory<>("obsPedido"));

        tblPedido.setItems(preencheTabela());
    }

    private ObservableList<ConsultaAvancada> preencheTabela() {
        ConsultaDAO dao = new ConsultaDAO();
        ObservableList<ConsultaAvancada> view
                = FXCollections.observableArrayList();

        try {
            if (!view.addAll(dao.lista(condicional))) {
                Alert alerta = new Alert(Alert.AlertType.ERROR,
                        "Não foi possivel recuperar estes registros!: ",
                        ButtonType.OK);
                alerta.showAndWait();
                condicional = "";
                view.addAll(dao.lista(condicional));
            }
        } catch (SQLException ex) {
            Alert alerta = new Alert(Alert.AlertType.ERROR,
                    "Erro: " + ex.getMessage(),
                    ButtonType.OK);
            alerta.showAndWait();
        }
        return view;

    }

    @FXML
    private void btn_menuPed_click(ActionEvent event) throws IOException {
        ConsultaAvancadaStage conAva = new ConsultaAvancadaStage();
        conAva.tela.close();
    }

    @FXML
    private void btn_PesquisarPed(ActionEvent event) {
        if (!txtPesquisa.getText().isEmpty()) {
            if (cb_Selecione.getValue() == "Nome do cliente") {
                condicional = "nomeCliente = 'Isaque'";
                tblPedido.setItems(preencheTabela());
            } else if (cb_Selecione.getValue() == "CPF") {
                try {
                    condicional = "cliente.cpfCliente = '" + txtPesquisa.getText() + "'";
                    tblPedido.setItems(preencheTabela());
                } catch (Exception ex) {
                    Alert alerta = new Alert(Alert.AlertType.ERROR,
                            "Erro: " + ex.getMessage(),
                            ButtonType.OK);
                    alerta.showAndWait();
                    condicional = "";
                    tblPedido.setItems(preencheTabela());
                }
            } else if (cb_Selecione.getValue() == "Status pedido") {
                condicional = "andamento.andamentoPedido = '" + txtPesquisa.getText() + "'";
                tblPedido.setItems(preencheTabela());
            } else if (cb_Selecione.getValue() == "Data do pedido") {
                condicional = "pedido.dataPedido = '" + txtPesquisa.getText() + "'";
                tblPedido.setItems(preencheTabela());
            } 
        }
    }
}
