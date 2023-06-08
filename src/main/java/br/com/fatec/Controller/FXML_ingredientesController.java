/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.fatec.Controller;

import br.com.fatec.DAO.FornecedorDAO;
import br.com.fatec.DAO.IngredientesDAO;
import br.com.fatec.model.Fornecedor;
import br.com.fatec.model.Ingredientes;
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
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Aluno
 */
public class FXML_ingredientesController implements Initializable {

    @FXML
    private TextField txtNomeProduto;
    @FXML
    private TextField txtQuantidade;
    
    @FXML
    private Button btnIncluir;
    @FXML
    private Button btnBuscar;
    @FXML
    private Button btnAlterar;
    @FXML
    private Button btnMenu;
    @FXML
    private ComboBox<Fornecedor> cb_fornecedor;

    private Fornecedor fornecedor;
    
    private FornecedorDAO forneDAO = new FornecedorDAO();

    private ObservableList<Fornecedor> fornecedores
            = FXCollections.observableArrayList();
    
    private ObservableList<String> unidadesMedidas
            = FXCollections.observableArrayList();
    
    private ObservableList<String> tiposDeProdutos
            = FXCollections.observableArrayList();
    @FXML
    private ComboBox<String> cb_unidadesMedidas;
    @FXML
    private ComboBox<String> cb_tipoProduto;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        preencheCombo();
        preencheUnidades();
        preencheTiposP();
    }

    private void preencheUnidades(){
        unidadesMedidas.add("KG");
        unidadesMedidas.add("ML");
        unidadesMedidas.add("CAIXA");
        cb_unidadesMedidas.setItems(unidadesMedidas);
    }
    
    private void preencheTiposP(){
        tiposDeProdutos.add("PERECIVEL");
        tiposDeProdutos.add("NÃO PERECIVEL");
        cb_tipoProduto.setItems(tiposDeProdutos);
    }
    
    
    private void preencheCombo() {
        try {
            fornecedores.addAll(forneDAO.lista(""));
        } catch (SQLException ex) {
            System.out.println("Erro no preenchimento da combo" + ex);
        }
        cb_fornecedor.setItems(fornecedores);
    }

    @FXML
    private void btnIncluir_click(ActionEvent event) throws SQLException {
        IngredientesDAO ingreDAO = new IngredientesDAO();
        try {
            if (ingreDAO.insere(moveDadosTelaModel())) {
                Alert alerta = new Alert(Alert.AlertType.INFORMATION,
                        "Ingrediente criado com sucesso!",
                        ButtonType.OK
                );
            }
        } catch (SQLException ex) {
            System.out.println("Erro: " + ex.getMessage());
        }

    }

    @FXML
    private void btnBuscar_click(ActionEvent event) {
    }

    @FXML
    private void btnAlterar_click(ActionEvent event) {
    }

    public Ingredientes moveDadosTelaModel() {
        Ingredientes ingre = new Ingredientes();
        ingre.setFornecedor(cb_fornecedor.getValue());
        ingre.setQuantidade(Integer.parseInt(txtQuantidade.getText()));
        ingre.setNomeProduto(txtNomeProduto.getText());
        ingre.setCNPJ("");
        ingre.setTipoProduto(cb_tipoProduto.getValue());
        ingre.setUnidadeMedida(cb_unidadesMedidas.getValue());
        
        return ingre;
    }

    @FXML
    private void cb_fornecedor_click(ActionEvent event) {
        
       
    }

}
