/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package br.com.fatec.Controller;

import br.com.fatec.CadastroProdutos;
import br.com.fatec.model.Produto;
import java.io.IOException;
import java.net.URL;
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
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author juroc
 */
public class FXML_cadastroProdutosController implements Initializable {

    @FXML
    private TextField txt_idProduto;
    @FXML
    private TextField txtCategoria;
    @FXML
    private TextField txtQuantidade;
    @FXML
    private ComboBox<Produto> cb_Categoria;
    @FXML
    private TextField txtNomeProd_Prod;
    @FXML
    private Button btnIncluir_Prod;
    @FXML
    private Button btnPesquisar_Prod;
    @FXML
    private Button btnAlterar_Prod;
    @FXML
    private Button btnExcluir_Prod;
    @FXML
    private Button btnMenu_Pro;

    ObservableList<Produto> produtos
            = FXCollections.observableArrayList();
    @FXML
    private DatePicker txtDataVencimento;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //colocar a coleção dentro da comboBox
        cb_Categoria.setItems(produtos);

        //programa evento de seleção na comboBox
        eventoChangeValueCombo();
    }

    private void eventoChangeValueCombo() {
        cb_Categoria.valueProperty().addListener((ov, velho, novo) -> {
            moveDadosModelTela(novo);
        });
    }

    @FXML
    private void btnMenu_Pro_click(ActionEvent event) throws IOException {
        CadastroProdutos cadProd = new CadastroProdutos();
        cadProd.tela.close();
    }

    /**
     * Move os dados que estão na tela para um objeto
     *
     * @return Objeto que receberá os dados
     */
    private Produto moveDadosTelaModel() {
        Produto p = new Produto();
        p.setIdProduto(Integer.parseInt(txt_idProduto.getText()));
        p.setNomeProduto(txtNomeProd_Prod.getText());
        p.setQuantidade(Integer.parseInt(txtQuantidade.getText()));
        p.setDataVencimento(txtDataVencimento.getValue());
        p.setCategoria(cb_Categoria.getValue());
        
        return p;
}


    /**
     * Move os dados do objeto para a tela
     *
     * @param p objeto que contem os dados para exibição na tela
     */
    private void moveDadosModelTela(Produto p) {
        txt_idProduto.setText(Integer.toString(p.getIdProduto()));
        txtNomeProd_Prod.setText(p.getNomeProduto());
        txtQuantidade.setText(Integer.toString(p.getQuantidade()));
        txtDataVencimento.setValue(p.getDataVencimento());
        txtCategoria.setText(p.getCategoria());
}
    
    @FXML
    private void btnIncluir_Prod_click(ActionEvent event) {
        Produto p = moveDadosTelaModel();
        if (p.getNomeProduto().equals("") || p.getCategoria().equals("")|| p.getIdProduto() == 0 || p.getQuantidade() == 0 || p.getDataVencimento() == null) {
            Alert alerta = new Alert(Alert.AlertType.WARNING,
                    "Preencha todos os campos",
                    ButtonType.OK);
            alerta.showAndWait();
        } else {
            if (produtos.contains(p)) {
                Alert alerta = new Alert(Alert.AlertType.WARNING,
                        "Não é possível inserir produtos duplicados",
                        ButtonType.OK);
                alerta.showAndWait();
            } else {
                produtos.add(p);
                Alert alerta = new Alert(Alert.AlertType.INFORMATION,
                        "Produtos cadastrados com sucesso",
                        ButtonType.OK);
                alerta.showAndWait();

            }
        }
        limparInput();
    }

    @FXML
    private void btnPesquisar_Prod_click(ActionEvent event) {
        String nomePesquisa = txtNomeProd_Prod.getText();

        // Verifica se o campo de pesquisa está vazio
        if (nomePesquisa.isEmpty()) {
            Alert alerta = new Alert(Alert.AlertType.WARNING,
                    "Por favor, digite um nome para pesquisa",
                    ButtonType.OK);
            alerta.showAndWait();
            return;
        }

        // Percorre a lista de produtos para encontrar o produto com o nome pesquisado
        for (Produto p : produtos) {
            if (p.getCategoria().equalsIgnoreCase(nomePesquisa)) {
                moveDadosModelTela(p);
                return;
            }
        }

        // Se não encontrou nenhum produto com o nome pesquisado, exibe uma mensagem de alerta
        Alert alerta = new Alert(Alert.AlertType.WARNING,
                "Produto não encontrado",
                ButtonType.OK);
        alerta.showAndWait();
    }


    public void limparInput() {
        txtNomeProd_Prod.clear();
        txt_idProduto.clear();
        txtQuantidade.clear();
        txtDataVencimento.setValue(null);
        txtCategoria.clear();
}


    @FXML
    private void btnAlterar_Prod_click(ActionEvent event) {
        Produto produtoSelecionado = cb_Categoria.getValue();

        if (produtoSelecionado != null) {
            Produto novoProduto = moveDadosTelaModel();

            // Verifica se os campos obrigatórios foram preenchidos
            if (novoProduto.getNomeProduto().isEmpty() || novoProduto.getCategoria().isEmpty() || novoProduto.getIdProduto() == 0 || novoProduto.getQuantidade() == 0 || novoProduto.getDataVencimento() == null) {
                Alert alerta = new Alert(Alert.AlertType.WARNING,
                        "Preencha todos os campos",
                        ButtonType.OK);
                alerta.showAndWait();
            } else {
                // Verifica se o novo produto é um duplicado
                if (produtos.contains(novoProduto) && !novoProduto.equals(produtoSelecionado)) {
                    Alert alerta = new Alert(Alert.AlertType.WARNING,
                            "Não é possível inserir produtos duplicados",
                            ButtonType.OK);
                    alerta.showAndWait();
                } else {
                    // Atualiza o produto na lista
                    int index = produtos.indexOf(produtoSelecionado);
                    produtos.set(index, novoProduto);

                    Alert alerta = new Alert(Alert.AlertType.INFORMATION,
                            "Produto alterado com sucesso",
                            ButtonType.OK);
                    alerta.showAndWait();
                }
            }

            limparInput();
        } else {
            Alert alerta = new Alert(Alert.AlertType.WARNING,
                    "Por favor, selecione algum produto",
                    ButtonType.OK);
            alerta.showAndWait();
        }
    }

    @FXML
    private void btnExcluir_Prod_click(ActionEvent event) {
        Produto produtoSelecionado = cb_Categoria.getValue();

        if (produtoSelecionado != null) {
            produtos.remove(produtoSelecionado);

            Alert alerta = new Alert(Alert.AlertType.INFORMATION,
                    "Produto excluído com sucesso",
                    ButtonType.OK);
            alerta.showAndWait();

            limparInput();
        } else {
            Alert alerta = new Alert(Alert.AlertType.WARNING,
                    "Por favor, selecione algum produto",
                    ButtonType.OK);
            alerta.showAndWait();
        }
    }
}

