/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package br.com.fatec.Controller;

import br.com.fatec.CadastroCliente;
import br.com.fatec.DAO.ClienteDAO;
import br.com.fatec.DAO.EstadoCodeDAO;
import br.com.fatec.Menu;
import br.com.fatec.model.Cliente;
import br.com.fatec.model.EstadoCode;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.util.List;
import java.util.ArrayList;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * FXML Controller class
 *
 * @author juroc
 */
public class FXML_cadastrarClientesController implements Initializable {

    @FXML
    private TextField txtCpf_Cli;
    @FXML
    private TextField txtNomeCl_Cli;
    @FXML
    private TextField txtEmail_Cli;
    @FXML
    private TextField txtCel_Cli;
    @FXML
    private TextField txtEnd_Cli;
    @FXML
    private Button btnIncluir_Cli;
    @FXML
    private Button btnPesquisar_Cli;
    @FXML
    private Button btnAlterar_Cli;
    @FXML
    private Button btnExcluir_Cli;
    @FXML
    private Button btnMenu_Cli;



    /**
     * Initializes the controller class.
     */

    //auxiliar para a comboBox
    private ObservableList<EstadoCode> estados
            = FXCollections.observableArrayList();
    @FXML
    private TextField txtCEP;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
   
    }

    private void preencheCombo() {
        
    }

    @FXML
    private void btnMenu_Cli_click(ActionEvent event) throws IOException {
        CadastroCliente cadCli = new CadastroCliente();
        cadCli.tela.close();

    }

    @FXML
    private void btnIncluir_Cli_click(ActionEvent event) throws SQLException {
        boolean inseriu = false;
        ClienteDAO cliDAO = new ClienteDAO();
        //verifica se o cliente já existe no banco
        if (cliDAO.buscaID(moveDadosTelaModel()) != null) {
            Alert alerta = new Alert(Alert.AlertType.ERROR,
                    "Este cliente já foi cadastrado!",
                    ButtonType.OK
            );
            alerta.showAndWait();
        } else {
            //verifica se os campos estão vazios
            if (estaVazio()) {
                Alert alerta = new Alert(Alert.AlertType.INFORMATION,
                        "Preencha todos os campos",
                        ButtonType.OK);
                alerta.showAndWait();
            } else {
                //validar total de caracteres
                if (txtCpf_Cli.getText().length() == 11) {
                    inseriu = cliDAO.insere(moveDadosTelaModel());
                    if (inseriu) {
                        Alert alerta = new Alert(Alert.AlertType.INFORMATION,
                                "Cliente criado com sucesso",
                                ButtonType.OK);
                        alerta.showAndWait();
                        limpar();
                    } else {
                        Alert alerta = new Alert(Alert.AlertType.ERROR,
                                "O cliente não pode ser cadastrado",
                                ButtonType.OK);
                        alerta.showAndWait();
                    }
                }
                //insere novo cliente no banco

            }
        }

    }

    private void moveDadosModelTela(Cliente c) throws SQLException {
        txtCpf_Cli.setText(c.getCpf());
        txtNomeCl_Cli.setText(c.getNomeCliente());
        txtEmail_Cli.setText(c.getEmail());
        txtCel_Cli.setText(c.getCelular());
        txtEnd_Cli.setText(c.getEndereco());
        txtCEP.setText(c.getCep());

    }

    private Cliente moveDadosTelaModel() {
        Cliente cliente = new Cliente();

        cliente.setCpf(txtCpf_Cli.getText());
        cliente.setNomeCliente(txtNomeCl_Cli.getText());
        cliente.setEmail(txtEmail_Cli.getText());
        cliente.setEndereco(txtEnd_Cli.getText());
        cliente.setCelular(txtCel_Cli.getText());
        cliente.setCep(txtCEP.getText());
        return cliente;
    }

    public void limpar() {
        txtCel_Cli.clear();
        txtCpf_Cli.clear();
        txtEmail_Cli.clear();
        txtEnd_Cli.clear();
        txtNomeCl_Cli.clear();
        txtCEP.clear();
    }

    @FXML
    private void btnPesquisar_click(ActionEvent event) throws SQLException {
        ClienteDAO cliDAO = new ClienteDAO();
        Cliente cliente = cliDAO.buscaID(moveDadosTelaModel());
        if (cliente == null) {
            Alert alerta = new Alert(Alert.AlertType.ERROR,
                    "Não foi possível localizar o registro",
                    ButtonType.OK
            );
            alerta.showAndWait();
        } else {
            moveDadosModelTela(cliente);
        }
    }

    @FXML
    private void btnExcluir_click(ActionEvent event) throws SQLException {
        ClienteDAO cliDAO = new ClienteDAO();
        Alert alerta = new Alert(Alert.AlertType.CONFIRMATION);
        alerta.setTitle("ALERTA");
        alerta.setContentText("Você realmente deseja deletar este registro?");

        Optional<ButtonType> result = alerta.showAndWait();

        if (result.isEmpty()) {
            System.out.println("Alerta fechado");
        } else if (result.get() == ButtonType.OK) {
            cliDAO.remove(moveDadosTelaModel());
            alerta = new Alert(Alert.AlertType.INFORMATION,
                    "Cliente deletado com sucesso!",
                    ButtonType.OK
            );
            alerta.showAndWait();
            limpar();
        }

    }

    @FXML
    private void btnAlterar_click(ActionEvent event) throws SQLException {
        if (estaVazio()) {
            Alert alerta = new Alert(Alert.AlertType.INFORMATION,
                    "Preencha todos os campos",
                    ButtonType.OK);
            alerta.showAndWait();
        } else {
            ClienteDAO cliDAO = new ClienteDAO();
            if (cliDAO.altera(moveDadosTelaModel())) {
                Alert alerta = new Alert(Alert.AlertType.INFORMATION,
                        "Os dados do cliente foram alterados com sucesso",
                        ButtonType.OK);
                alerta.showAndWait();
                limpar();
            } else {
                Alert alerta = new Alert(Alert.AlertType.INFORMATION,
                        "Não foi possivel alterar os dados do cliente",
                        ButtonType.OK);
                alerta.showAndWait();
            }
        }
    }

    public boolean estaVazio() {
        if (txtCEP.getText().isEmpty() || txtCel_Cli.getText().isEmpty() || txtCpf_Cli.getText().isEmpty() || txtEmail_Cli.getText().isEmpty() || txtEnd_Cli.getText().isEmpty() || txtNomeCl_Cli.getText().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

}
