/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package br.com.fatec.Controller;

import br.com.fatec.ConsultaAvancada;
import br.com.fatec.FornecedorStage;
import br.com.fatec.DAO.FornecedorDAO;
import br.com.fatec.model.Fornecedor;
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
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author Isaque
 */
public class FXML_fornecedorController implements Initializable {

    @FXML
    private Button btnMenu_Forn;
    @FXML
    private Button btnIncluir_Forn;
    @FXML
    private Button btnPesquisar_Forn;
    @FXML
    private Button btnAlterar_Forn;
    @FXML
    private Button btnExcluir_Forn;
    @FXML
    private TextField txtNomeFor;
    @FXML
    private TextField txtCNPJ;
    @FXML
    private TextField txtCelular;
    @FXML
    private TextField txtEndereco;
    private TextField txtProduto;

    private Fornecedor fornecedor;
    private FornecedorDAO fornecedorDAO = new FornecedorDAO();

    private ObservableList<Fornecedor> fornecedores
            = FXCollections.observableArrayList();
    @FXML
    private Label lblCNPJ;
    @FXML
    private TextField txtCEP;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    @FXML
    private void btnMenu_click(ActionEvent event) throws IOException {
        FornecedorStage forne = new FornecedorStage();
        forne.tela.close();
    }

    @FXML
    private void btnPesquisar_click(ActionEvent event) throws SQLException {
        FornecedorDAO forneDAO = new FornecedorDAO();
        if (forneDAO.buscaID(moveDadosTelaModel()) == null) {
            Alert alerta = new Alert(Alert.AlertType.ERROR,
                    "Não foi possivel localizar o registro",
                    ButtonType.OK
            );
            alerta.showAndWait();
        } else {
            moveDadosModelTela(forneDAO.buscaID(moveDadosTelaModel()));
        }
    }

    @FXML
    private void btnAlterar_click(ActionEvent event) throws SQLException {
        if (estaVazio()) {
            Alert alerta = new Alert(Alert.AlertType.INFORMATION,
                    "Preencha todos os campos!",
                    ButtonType.OK);
            alerta.showAndWait();
        } else {
            FornecedorDAO forneDAO = new FornecedorDAO();
            if (forneDAO.altera(moveDadosTelaModel())) {
                Alert alerta = new Alert(Alert.AlertType.INFORMATION,
                        "Fornecedor alterado com sucesso!",
                        ButtonType.OK);
                alerta.showAndWait();
                limpar();
            }
        }
    }

    @FXML
    private void btnExcluir_click(ActionEvent event) throws SQLException {
        FornecedorDAO forneDAO = new FornecedorDAO();
        if (txtCNPJ.getText().isEmpty()) {
            Alert alerta = new Alert(Alert.AlertType.INFORMATION,
                    "Preencha o campo CNPJ para excluir o fornecedor!",
                    ButtonType.OK);
            alerta.showAndWait();
        } else {
            if (forneDAO.remove(moveDadosTelaModel())) {
                Alert alerta = new Alert(Alert.AlertType.INFORMATION,
                        "O CNPJ " + txtCNPJ.getText() + " foi excluido com sucesso!",
                        ButtonType.OK);
                alerta.showAndWait();
                limpar();
            }
        }
    }

    @FXML
    private void btnIncluir_click(ActionEvent event) throws SQLException {
        FornecedorDAO forDAO = new FornecedorDAO();

        if (!estaVazio()) {
            if (txtCNPJ.getText().length() != 14) {
                Alert alerta = new Alert(Alert.AlertType.ERROR,
                        "CNPJ INVALIDO!",
                        ButtonType.OK);
                alerta.showAndWait();
            } else {
                if (forDAO.buscaID(moveDadosTelaModel()) != null) {
                    Alert alerta = new Alert(Alert.AlertType.ERROR,
                            "Este CNPJ já foi cadastrado!",
                            ButtonType.OK
                    );
                    alerta.showAndWait();
                } else {
                    if (forDAO.insere(moveDadosTelaModel())) {
                        Alert alerta = new Alert(Alert.AlertType.INFORMATION,
                                "Fornecedor cadastrado com sucesso!",
                                ButtonType.OK);
                        alerta.showAndWait();
                        limpar();
                    }
                }
            }
            //verificar se o CNPJ existe no banco
        } else {
            Alert alerta = new Alert(Alert.AlertType.INFORMATION,
                    "Preencha todos os campos",
                    ButtonType.OK);
            alerta.showAndWait();
        }

    }

    public boolean estaVazio() {
        if (txtCNPJ.getText().isEmpty() || txtCelular.getText().isEmpty() || txtEndereco.getText().isEmpty() || txtNomeFor.getText().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    private void moveDadosModelTela(Fornecedor f) {
        txtCNPJ.setText(f.getCNPJ());
        txtCelular.setText(f.getCelular());
        txtEndereco.setText(f.getEndereco());
        txtNomeFor.setText(f.getNomeFornecedor());
        txtCEP.setText(f.getCep());

    }

    private Fornecedor moveDadosTelaModel() {
        Fornecedor f = new Fornecedor();
        f.setCNPJ(txtCNPJ.getText());
        f.setNomeFornecedor(txtNomeFor.getText());
        f.setEndereco(txtEndereco.getText());
        f.setCelular(txtCelular.getText());
        f.setCep(txtCEP.getText());
        return f;
    }
    
    public void limpar(){
        txtCEP.clear();
        txtCNPJ.clear();
        txtCelular.clear();
        txtEndereco.clear();
        txtNomeFor.clear();
    }

    @FXML
    private void validarCNPJ(MouseEvent event) {
    }

}
