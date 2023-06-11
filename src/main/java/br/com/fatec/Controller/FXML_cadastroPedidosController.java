/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package br.com.fatec.Controller;

import br.com.fatec.CadastroPedido;
import br.com.fatec.DAO.AndamentoDAO;
import br.com.fatec.DAO.ClienteDAO;
import br.com.fatec.DAO.PedidoDAO;
import br.com.fatec.model.Andamento;
import br.com.fatec.model.Cliente;
import br.com.fatec.model.Pedido;
import java.io.IOException;
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
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author juroc
 */
public class FXML_cadastroPedidosController implements Initializable {

    @FXML
    private TextField txt_idPedido;
    @FXML
    private TextField txtCelular;
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
    private ComboBox<String> cb_statusPedido;
    @FXML
    private TextField txtNomeCliente;
    @FXML
    private TextField txtPreco;
    @FXML
    private TextField txtCPF;

    private ObservableList<String> status
            = FXCollections.observableArrayList();

    @FXML
    private TextField txtData;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        preencheComboStatus();
    }

    //preenche a combobox cb_statusPedido com os itens abaixo
    public void preencheComboStatus() {
        status.add("Finalizado");
        status.add("Em processo");
        status.add("Cancelado");
        cb_statusPedido.setItems(status);
    }

    //fecha a tela atual
    @FXML
    private void btnMenu_Ped_click(ActionEvent event) throws IOException {
        CadastroPedido cadPed = new CadastroPedido();
        cadPed.tela.close();
    }

    //Cria um cliente 
    @FXML
    private void btnIncluir_Ped_click(ActionEvent event) throws SQLException {
        PedidoDAO pedidoDAO = new PedidoDAO();
        Pedido pedido = new Pedido();

        if (!estaVazio()) {
            pedido.setIdPedido(Integer.parseInt(txt_idPedido.getText()));
            if (pedidoDAO.buscaID(pedido) == null) {
                if (pedidoDAO.insere(moveDadosTelaModel())) {
                    Alert alerta = new Alert(Alert.AlertType.INFORMATION,
                            "Pedido incluido com sucesso",
                            ButtonType.OK);
                    alerta.showAndWait();
                    limpar();
                } else {
                    mensagem("Não foi possivel efetuar o cadastro deste pedido");
                }
            } else {
                mensagem("Este ID já foi registrado para um pedido!");
            }
        } else {
            mensagem("Preencha todos os campos");
        }

        //pedidoDAO.insere(model)
    }

    //pega os dados da tela e manda para o model
    private Pedido moveDadosTelaModel() {
        Pedido p = new Pedido();
        p.setCpf(txtCPF.getText());
        p.setStatus(cb_statusPedido.getValue());
        p.setCelular(txtCelular.getText());
        p.setDataPedido(txtData.getText());
        p.setNomeCliente(txtNomeCliente.getText());
        p.setObsPedido(txtObsPed.getText());
        p.setPedido(Integer.parseInt(txt_idPedido.getText()));
        p.setPreco(txtPreco.getText());

        return p;
    }

    //Recebe um produto e manda para tela 
    private void moveDadosModelTela(Pedido p) throws SQLException {
        txtData.setText(p.getDataPedido());
        txtObsPed.setText(p.getObsPedido());
        txtPreco.setText(p.getPreco());
        txt_idPedido.setText(Integer.toString(p.getIdPedido()));
        txtCPF.setText(p.getCpf());
        buscaCliente();
        buscaStatusPedido();

    }

    //Recebe um cliente e manda para a tela
    private void moveDadosModelTelaCliente(Cliente c) {
        if (c != null) {
            txtCPF.setText(c.getCpf());
            txtCelular.setText(c.getCelular());
            txtNomeCliente.setText(c.getNomeCliente().toString());
        } else {
            mensagem("Este CPF não foi cadastrado na base de dados");
        }
    }

    //pesquisa o CPF do cliente quando o mouse sair da área do txtCPF
    @FXML
    private void pesquisaCPF(MouseEvent event) throws SQLException {
        ClienteDAO cliDAO = new ClienteDAO();
        Cliente cli = new Cliente();
        if (txtCPF.getText().isEmpty()) {
            mensagem("Digite um CPF");
        } else {
            if (txtCPF.getText().length() == 11) {
                cli.setCpf(txtCPF.getText());
                try {
                    cliDAO.buscaID(cli);
                    moveDadosModelTelaCliente(cliDAO.buscaID(cli));
                } catch (SQLException ex) {
                    System.out.println("SQLEx: " + ex);
                }
            } else {
                mensagem("Digite um cpf valido!");
            }

        }
    }

    //busca um cliente através do cpf do cliente
    public void buscaCliente() throws SQLException {
        ClienteDAO cliDAO = new ClienteDAO();
        Cliente cli = new Cliente();
        cli.setCpf(txtCPF.getText());
        cliDAO.buscaID(cli);
        moveDadosModelTelaCliente(cliDAO.buscaID(cli));
    }

    //Cria uma mensagem
    private void mensagem(String mensagem) {
        Alert alerta = new Alert(Alert.AlertType.INFORMATION,
                mensagem,
                ButtonType.OK);
        alerta.showAndWait();
    }

    //Verifica se os campos do formulario estão vazios
    public boolean estaVazio() {
        if (txtCPF.getText().isEmpty() || txtCelular.getText().isEmpty() || txtData.getText().isEmpty() || txtNomeCliente.getText().isEmpty() || txtObsPed.getText().isEmpty() || txtPreco.getText().isEmpty() || txt_idPedido.getText().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    //Busca o pedido através do idPedido
    @FXML
    private void btnPesquisar_Ped(ActionEvent event) throws SQLException {
        PedidoDAO pedidoDAO = new PedidoDAO();
        Pedido pedido = null;

        if (txt_idPedido.getText().isEmpty()) {
            mensagem("Digite um ID para realizar a pesquisa!");
        } else {
            pedido = pedidoDAO.buscaID(moveDadosTelaModel());

            if (pedido == null) {
                Alert alerta = new Alert(Alert.AlertType.ERROR,
                        "Não foi possível localizar o registro",
                        ButtonType.OK
                );
                limpar();
                alerta.showAndWait();

            } else {
                moveDadosModelTela(pedido);
            }
        }

    }

    //busca o status do pedido
    private void buscaStatusPedido() throws SQLException {
        AndamentoDAO andamentoDAO = new AndamentoDAO();
        Andamento andamento = new Andamento();

        andamento.setIdPedido(Integer.parseInt(txt_idPedido.getText()));
        andamento = andamentoDAO.buscaID(andamento);
        if (andamento == null) {
            mensagem("Algo deu errado ao buscar o status do pedido!");
        } else {
            cb_statusPedido.setValue(andamento.getAndamentoPedido());
        }
    }

    @FXML
    private void btnAlterar_click(ActionEvent event) throws SQLException {
        PedidoDAO pDAO = new PedidoDAO();
        if (!estaVazio()) {
            if (pDAO.altera(moveDadosTelaModel())) {
                mensagem("Dados alterados com sucesso!");
                limpar();
            }
        } else {
            mensagem("Preencha todos os campos");
        }
    }

    public void limpar() {
        txtCPF.clear();
        txtCelular.clear();
        txtData.clear();
        txtNomeCliente.clear();
        txtObsPed.clear();
        txtPreco.clear();
        txt_idPedido.clear();
        cb_statusPedido.setValue(null);
    }

    @FXML
    private void btnExcluir_click(ActionEvent event) throws SQLException {
        PedidoDAO pedidoDAO = new PedidoDAO();
        Alert alerta = new Alert(Alert.AlertType.CONFIRMATION);

        if (!estaVazio()) {
            alerta.setTitle("ALERTA");
            alerta.setContentText("Você realmente deseja deletar este registro?");
            Optional<ButtonType> result = alerta.showAndWait();
            if (result.isEmpty()) {
                System.out.println("Alerta fechado");
            } else if (result.get() == ButtonType.OK) {
                pedidoDAO.remove(moveDadosTelaModel());
                alerta = new Alert(Alert.AlertType.INFORMATION,
                        "Pedido deletado com sucesso!",
                        ButtonType.OK
                );
                alerta.showAndWait();
                limpar();
            }
        } else {
            mensagem("Preencha todos os campos!");
        }

    }
}
