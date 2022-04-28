package controller;

import java.util.List;
import javax.swing.JOptionPane;
import dao.AutorDao;
import domain.Autor;
import principal.Principal;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

public class AutorController {

    @FXML
    private TextField txtNacional;

    @FXML
    private TableColumn<Autor, String> clnNacionalidade;

    @FXML
    private TableView<Autor> tvTabela;

    @FXML
    private Button btnCadastrar;

    @FXML
    private TextField txtNome;

    @FXML
    private Button btnAtualizar;

    @FXML
    private TextField txtAno;

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtPesquisar;

    @FXML
    private TableColumn<Autor, Integer> clnAnoNasc;

    @FXML
    private TableColumn<Autor, String> clnNome;

    @FXML
    private Button btnExcluir;

    @FXML
    private Button btnCadastrar1;

    @FXML
    private Button btnLimpar;

    @FXML
    private Button btnPesquisar;

    private List<Autor> autores;

    ObservableList<Autor> autoresOL;

    public boolean isNumeric(String str) {
        try {
                int valor = Integer.parseInt(str);
                return true;
        } catch (NumberFormatException e) {
                return false;
        }
    }

    public void initialize(){
        clnNome.setCellValueFactory(new PropertyValueFactory<Autor, String>("nome"));
        clnNacionalidade.setCellValueFactory(new PropertyValueFactory<Autor, String>("nacionalidade"));
        clnAnoNasc.setCellValueFactory(new PropertyValueFactory<Autor, Integer>("ano_nasc"));
        mostrarTabela("");
    }

    void limparCampos(){
        txtAno.setText("");
        txtId.setText("");
        txtNacional.setText("");
        txtNome.setText("");
        mostrarTabela("");
    }

    void mostrarTabela(String pesquisa){
        String nome;
        AutorDao dao = new AutorDao();

        if(!pesquisa.isEmpty()){
            nome = txtPesquisar.getText();
            autores = dao.obterPorNome(nome);
            autoresOL = FXCollections.observableArrayList(autores);
            tvTabela.setItems(autoresOL);
        } else {
            autores = dao.obterTodos();
            autoresOL = FXCollections.observableArrayList(autores);
            tvTabela.setItems(autoresOL);
        }
    }

    @FXML
    void handlerCadastrarAutor(ActionEvent event) {
        if (txtNome.getText().isEmpty() || txtNacional.getText().isEmpty() || txtAno.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "Preencha os campos vazios!", "Dados incompletos", JOptionPane.OK_OPTION);
            return;
        }
    
        if (!isNumeric(txtAno.getText())){
            JOptionPane.showMessageDialog(null, "Dados inseridos incorretamente!", "Erro ao inserir", JOptionPane.OK_OPTION);
            txtAno.requestFocus();
            return;
        }

        Autor autor = new Autor();
        AutorDao dao = new AutorDao();

        autor.setNome(txtNome.getText());
        autor.setNacionalidade(txtNacional.getText());
        autor.setAno_nasc(Integer.valueOf(txtAno.getText()));

        if (dao.verificaNome(autor.getNome()) == true){
            JOptionPane.showMessageDialog(null, "Autor(a) já existente!", "Falha Encontrada", JOptionPane.OK_OPTION);
            txtNome.requestFocus();
            return;
        }

        dao.inserir(autor);

        limparCampos();
        txtNome.requestFocus();
        mostrarTabela("");
    }

    @FXML
    void handlerAtualizarAutor(ActionEvent event) {
        if (txtNome.getText().isEmpty() || txtNacional.getText().isEmpty() || txtAno.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "Preencha os campos vazios!", "Dados incompletos", JOptionPane.OK_OPTION);
            return;
        }
    
        if (!isNumeric(txtAno.getText())){
            JOptionPane.showMessageDialog(null, "Dados inseridos incorretamente!", "Erro ao inserir", JOptionPane.OK_OPTION);
            txtAno.requestFocus();
            return;
        }
    
        Autor autor = new Autor();
        AutorDao dao = new AutorDao();

        autor.setNome(txtNome.getText());
        autor.setNacionalidade(txtNacional.getText());
        autor.setAno_nasc(Integer.valueOf(txtAno.getText()));
        autor.setId(Integer.valueOf(txtId.getText()));

        dao.atualizar(autor);

        limparCampos();
        txtNome.requestFocus();
        mostrarTabela("");
    }

    @FXML
    void handlerExcluirAutor(ActionEvent event) {
        if (txtNome.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "Informe o título do livro!", "Dados incompletos", JOptionPane.OK_OPTION);
            return;
        }

        String nome;
        AutorDao dao = new AutorDao();

        nome = txtNome.getText();

        dao.deletar(nome);

        limparCampos();
        txtNome.requestFocus();
        mostrarTabela("");
    }

    @FXML
    void handlerLimparCampos(ActionEvent event) {
        limparCampos();
        txtNome.requestFocus();
    }

    @FXML
    void handlerVoltar(ActionEvent event) {
        String mensagem = "Deseja voltar ao menu?";
        String title = "Confirmar operação";
        int res = JOptionPane.showConfirmDialog(null, mensagem, title, JOptionPane.YES_NO_OPTION);

        if (res == 0)
                Principal.mostrarMenu();
    }

    @FXML
    void handlerPesquisarAutor(ActionEvent event) {
        mostrarTabela(txtPesquisar.getText());
        txtPesquisar.requestFocus();
    }

    @FXML
    void handlerTabelaSeta(KeyEvent event){
        Autor autor = tvTabela.getSelectionModel().getSelectedItem();

        if (event.getCode() == KeyCode.UP){
            txtAno.setText(String.valueOf(autor.getAno_nasc()));
            txtNacional.setText(autor.getNacionalidade());
            txtNome.setText(autor.getNome());
            txtId.setText(String.valueOf(autor.getId()));
        } else if (event.getCode() == KeyCode.DOWN){
            txtAno.setText(String.valueOf(autor.getAno_nasc()));
            txtNacional.setText(autor.getNacionalidade());
            txtNome.setText(autor.getNome());
            txtId.setText(String.valueOf(autor.getId()));
        }
    }

    @FXML
    void handlerTabelaMouse(MouseEvent event) {
        Autor autor = tvTabela.getSelectionModel().getSelectedItem();

        if (event.getClickCount() == 1 && autor != null){
            txtAno.setText(String.valueOf(autor.getAno_nasc()));
            txtNacional.setText(autor.getNacionalidade());
            txtNome.setText(autor.getNome());
            txtId.setText(String.valueOf(autor.getId()));
        }
    }
}
