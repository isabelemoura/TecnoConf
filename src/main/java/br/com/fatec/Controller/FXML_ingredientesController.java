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

    private void preencheUnidades() {
        unidadesMedidas.add("KG");
        unidadesMedidas.add("ML");
        unidadesMedidas.add("CAIXA");

        cb_unidadesMedidas.setItems(unidadesMedidas);
    }

    private void preencheTiposP() {
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
        if (estaVazio()) {
            mensagem("Preencha todos os campos");
        } else {
            if (ingreDAO.insere(moveDadosTelaModel()) == true) {
                mensagem("Ingrediente criado com sucesso ");
               // limpar();
            } else if (moveDadosTelaModel() == null) {
                System.out.println("Verifique os campos");
            }
        }
    }

    public void mensagem(String mensagem) {
        Alert alerta = new Alert(Alert.AlertType.INFORMATION,
                mensagem,
                ButtonType.OK
        );
        alerta.showAndWait();
    }

    @FXML
    private void btnBuscar_click(ActionEvent event) throws SQLException {
       IngredientesDAO ingreDAO = new IngredientesDAO();
        Ingredientes cliente = ingreDAO.buscaID(moveDadosTelaModel());
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
    private void btnAlterar_click(ActionEvent event) {
    }

    public Ingredientes moveDadosTelaModel() {
        Ingredientes ingre = new Ingredientes();
    
            ingre.setFornecedor(cb_fornecedor.getValue());
            ingre.setQuantidade(txtQuantidade.getText());
            ingre.setNomeProduto(txtNomeProduto.getText());
            ingre.setFornecedor(cb_fornecedor.getValue());
            ingre.setTipoProduto(cb_tipoProduto.getValue());
            ingre.setUnidadeMedida(cb_unidadesMedidas.getValue());
            for (Fornecedor forne : fornecedores) {
                if (cb_fornecedor.getValue().toString().equals(forne.getNomeFornecedor())) {
                    ingre.setCNPJ(forne.getCNPJ());
                }
            }

        

        return ingre;
    }

    private void moveDadosModelTela(Ingredientes ingre) throws SQLException {
        txtNomeProduto.setText(ingre.getNomeProduto());
        txtQuantidade.setText(ingre.getQuantidade());
        cb_fornecedor.setValue(ingre.getFornecedor());
        cb_tipoProduto.setValue(ingre.getTipoProduto());
        cb_unidadesMedidas.setValue(ingre.getUnidadeMedida());
    }

    @FXML
    private void cb_fornecedor_click(ActionEvent event) {
//        Ingredientes ingre = new Ingredientes();
//        for (Fornecedor forne : fornecedores) {
//            if (cb_fornecedor.getValue().toString().equals(forne.getNomeFornecedor())) {
//                ingre.setCNPJ(forne.getCNPJ());
//                System.out.println("CNPJ: " + forne.getCNPJ());
//                return ingre.getCNPJ();
//            }
//        }
//        return null;
        Ingredientes in = moveDadosTelaModel();
        System.out.println(in.getCNPJ());
    }

    public boolean estaVazio() {
        if (txtNomeProduto.getText().isEmpty() || txtQuantidade.getText().isEmpty() || cb_fornecedor.getValue() == null || cb_tipoProduto.getValue() == null || cb_unidadesMedidas.getValue() == null) {
            return true;
        } else {
            return false;
        }
    }

    private void limpar() {
        txtNomeProduto.clear();
        txtQuantidade.clear();
        cb_fornecedor.setValue(null);
        cb_tipoProduto.setValue(null);
        cb_unidadesMedidas.setValue(null);

    }

}
