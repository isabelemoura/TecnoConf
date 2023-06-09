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
import br.com.fatec.IngredientesStage;
import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
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
import javafx.scene.input.DragEvent;
import javafx.scene.input.MouseEvent;

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
    @FXML
    private TextField txtIdProduto;
    @FXML
    private Button btnDelete;

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

        if (txtIdProduto.getText().equals("")) {
            mensagem("Digite o ID do produto");
        } else {
            if (cb_fornecedor.getValue() == null) {
                mensagem("Selecione um fornecedor");
            } else {
                Ingredientes ingre = ingreDAO.buscaID(moveDadosTelaModel());
                if (ingre == null) {
                    Alert alerta = new Alert(Alert.AlertType.ERROR,
                            "Não foi possível localizar o registro pois este fornecedor pode não fornecer este produto",
                            ButtonType.OK
                    );
                    limpar();
                    alerta.showAndWait();
                } else {
                    moveDadosModelTela(ingre);
                }
            }
        }
    }

    @FXML
    private void btnAlterar_click(ActionEvent event) throws SQLException {
        IngredientesDAO ingreDAO = new IngredientesDAO();
        boolean alterou = false;
        if (!estaVazio()) {
            alterou = ingreDAO.altera(moveDadosTelaModel());
            if (alterou) {
                mensagem("Os dados foram alterados com sucesso!");
            } else {
                mensagem("Verifique os campos");
            }
        } else {
            mensagem("Preencha todos os campos");
        }
    }

    public Ingredientes moveDadosTelaModel() {
        Ingredientes ingre = new Ingredientes();

        ingre.setFornecedor(cb_fornecedor.getValue());
        ingre.setQuantidade(txtQuantidade.getText());
        ingre.setNomeProduto(txtNomeProduto.getText());
        ingre.setTipoProduto(cb_tipoProduto.getValue());
        ingre.setUnidadeMedida(cb_unidadesMedidas.getValue());
        ingre.setIdIngrediente(Integer.parseInt(txtIdProduto.getText()));
        ingre.setIdIngrediente(Integer.parseInt(txtIdProduto.getText()));
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
        txtIdProduto.setText(Integer.toString(ingre.getIdIngrediente()));
        cb_tipoProduto.setValue(ingre.getTipoProduto());
        cb_unidadesMedidas.setValue(ingre.getUnidadeMedida());
    }

    @FXML
    private void cb_fornecedor_click(ActionEvent event) throws SQLException {

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

    @FXML
    private void txtIdProduto_exited(MouseEvent event) {

    }

    @FXML
    private void btnDelete_click(ActionEvent event) throws SQLException {
        IngredientesDAO ingreDAO = new IngredientesDAO();
        Alert alerta = new Alert(Alert.AlertType.CONFIRMATION);

        

        if (!estaVazio()) {
            alerta.setTitle("ALERTA");
            alerta.setContentText("Você realmente deseja deletar este registro?");
            Optional<ButtonType> result = alerta.showAndWait();
            if (result.isEmpty()) {
                System.out.println("Alerta fechado");
            } else if (result.get() == ButtonType.OK) {
                if (ingreDAO.remove(moveDadosTelaModel())) {
                    alerta = new Alert(Alert.AlertType.INFORMATION,
                            "Ingrediente deletado com sucesso!",
                            ButtonType.OK
                    );
                    alerta.showAndWait();
                    limpar();
                } else {
                    alerta = new Alert(Alert.AlertType.INFORMATION,
                            "Não foi possivel deletar este ingrediente!",
                            ButtonType.OK
                    );
                    alerta.showAndWait();
                }

            }
        } else {
            mensagem("Preencha todos os campos");
        }

    }

    @FXML
    private void btnMenu_click(ActionEvent event) {
        IngredientesStage stage = new IngredientesStage();
        stage.tela.close();
    }

}
