package controller;

import java.util.List;
import javax.swing.JOptionPane;
import dao.LivroDao;
import domain.Livro;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import principal.Principal;

public class LivroController {
    
        @FXML
        private TextField txtAutor;

        @FXML
        private TextField txtId;
    
        @FXML
        private TextField txtEdicao;
    
        @FXML
        private Button btnCadastrar;
    
        @FXML
        private TextField txtISBN;
    
        @FXML
        private TextArea txtDescricao;
    
        @FXML
        private Button btnExcluir;
    
        @FXML
        private TextField txtTitulo;
    
        @FXML
        private Button btnLimpar;
    
        @FXML
        private Button btnEditar;

        @FXML
        private Button btnVoltar;

        @FXML
        private TextField txtPesquisar;

        @FXML
        private TableView<Livro> tvTabela;

        @FXML
        private TableColumn<Livro, String> clnTitulo;

        @FXML
        private TableColumn<Livro, String> clnAutor;

        @FXML
        private TableColumn<Livro, String> clnISBN;

        @FXML
        private TableColumn<Livro, String> clnDescricao;

        @FXML
        private TableColumn<Livro, Integer> clnEdicao;

        private List<Livro> livros;

        ObservableList<Livro> livrosOL;

        public boolean isNumeric(String str) {
                try {
                        int valor = Integer.parseInt(str);
                        return true;
                } catch (NumberFormatException e) {
                        return false;
                }
        }

        public void initialize(){
                clnTitulo.setCellValueFactory(new PropertyValueFactory<Livro, String>("titulo"));
                clnISBN.setCellValueFactory(new PropertyValueFactory<Livro, String>("isbn"));
                clnAutor.setCellValueFactory(new PropertyValueFactory<Livro, String>("autor"));
                clnDescricao.setCellValueFactory(new PropertyValueFactory<Livro, String>("descricao"));
                clnEdicao.setCellValueFactory(new PropertyValueFactory<Livro, Integer>("edicao"));
                mostrarTabela("");
        }

        void limparCampos(){
                txtAutor.setText("");
                txtEdicao.setText("");
                txtISBN.setText("");
                txtDescricao.setText("");
                txtTitulo.setText("");
                txtPesquisar.setText("");
                txtId.setText("");
                mostrarTabela("");
        }

        void mostrarTabela(String pesquisa){
                String titulo;
                LivroDao dao = new LivroDao();

                if(!pesquisa.isEmpty()){
                        titulo = txtPesquisar.getText();
                        livros = dao.obterPorTitulo(titulo);
                        livrosOL = FXCollections.observableArrayList(livros);
                        tvTabela.setItems(livrosOL);
                } else {
                        livros = dao.obterTodos();
                        livrosOL = FXCollections.observableArrayList(livros);
                        tvTabela.setItems(livrosOL);
                }
        }

        @FXML
        void handlerLimparCampos(ActionEvent event) {
                limparCampos();
                txtTitulo.requestFocus();
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
        void handlerPesquisarLivro(ActionEvent event) {
                mostrarTabela(txtPesquisar.getText());
                txtPesquisar.requestFocus();
        }

        @FXML
        void handlerTabelaSeta(KeyEvent event){
                Livro livro = tvTabela.getSelectionModel().getSelectedItem();

                if (event.getCode() == KeyCode.UP){
                        txtTitulo.setText(livro.getTitulo());
                        txtISBN.setText(livro.getIsbn());
                        txtAutor.setText(livro.getAutor());
                        txtDescricao.setText(livro.getDescricao());
                        txtEdicao.setText(String.valueOf(livro.getEdicao()));
                        txtId.setText(String.valueOf(livro.getId()));
                } else if (event.getCode() == KeyCode.DOWN){
                        txtTitulo.setText(livro.getTitulo());
                        txtISBN.setText(livro.getIsbn());
                        txtAutor.setText(livro.getAutor());
                        txtDescricao.setText(livro.getDescricao());
                        txtEdicao.setText(String.valueOf(livro.getEdicao()));
                        txtId.setText(String.valueOf(livro.getId()));
                }
        }

        @FXML
        void handlerTabelaMouse(MouseEvent event) {
                Livro livro = tvTabela.getSelectionModel().getSelectedItem();

                if (event.getClickCount() == 1 && livro != null){
                        txtTitulo.setText(livro.getTitulo());
                        txtISBN.setText(livro.getIsbn());
                        txtAutor.setText(livro.getAutor());
                        txtDescricao.setText(livro.getDescricao());
                        txtEdicao.setText(String.valueOf(livro.getEdicao()));
                        txtId.setText(String.valueOf(livro.getId()));
                }
        }

        @FXML
        void handlerCadastrarLivro(ActionEvent event) {
                if (txtTitulo.getText().isEmpty() || txtAutor.getText().isEmpty() || txtDescricao.getText().isEmpty() || txtEdicao.getText().isEmpty() || txtISBN.getText().isEmpty()){
                        JOptionPane.showMessageDialog(null, "Preencha os campos vazios!", "Dados incompletos", JOptionPane.OK_OPTION);
                        return;
                }

                if (txtDescricao.getText().length() > 100){
                        JOptionPane.showMessageDialog(null, "Limite de caracteres atingido!\n\nDescrição: Máximo 100 caracteres", "Falha Encontrada", JOptionPane.OK_OPTION);
                        txtDescricao.requestFocus();
                        return;
                }
                
                if (!isNumeric(txtEdicao.getText())){
                        JOptionPane.showMessageDialog(null, "Dados inseridos incorretamente!", "Erro ao inserir", JOptionPane.OK_OPTION);
                        txtEdicao.requestFocus();
                        return;
                }

                Livro livro = new Livro();
                LivroDao dao = new LivroDao();

                livro.setTitulo(txtTitulo.getText());
                livro.setIsbn(txtISBN.getText());
                livro.setEdicao(Integer.valueOf(txtEdicao.getText()));
                livro.setDescricao(txtDescricao.getText());
                livro.setAutor(txtAutor.getText());

                if ((dao.verificaIsbn(livro.getIsbn()) == true) && (dao.verificaTitulo(livro.getTitulo()) == true)){
                        JOptionPane.showMessageDialog(null, "Título e ISBN já existentes!", "Falha Encontrada", JOptionPane.OK_OPTION);
                        txtTitulo.requestFocus();
                        return;
                }

                if (dao.verificaIsbn(livro.getIsbn()) == true){
                        JOptionPane.showMessageDialog(null, "ISBN informado já existe!", "Falha Encontrada", JOptionPane.OK_OPTION);
                        txtTitulo.requestFocus();
                        return;
                }

                if (dao.verificaTitulo(livro.getTitulo()) == true){
                        JOptionPane.showMessageDialog(null, "Título informado já existe!", "Falha Encontrada", JOptionPane.OK_OPTION);
                        txtTitulo.requestFocus();
                        return;
                }

                dao.inserir(livro);

                limparCampos();
                txtTitulo.requestFocus();
                mostrarTabela("");
        }

        @FXML
        void handlerEditarLivro(ActionEvent event) {
                if (txtTitulo.getText().isEmpty() || txtAutor.getText().isEmpty() || txtDescricao.getText().isEmpty() || txtEdicao.getText().isEmpty() || txtISBN.getText().isEmpty()){
                        JOptionPane.showMessageDialog(null, "Preencha os campos vazios!", "Dados incompletos", JOptionPane.OK_OPTION);
                        return;
                }

                if (txtDescricao.getText().length() > 100){
                        JOptionPane.showMessageDialog(null, "Limite de caracteres atingido!\n\nDescrição: Máximo 100 caracteres", "Falha Encontrada", JOptionPane.OK_OPTION);
                        txtDescricao.requestFocus();
                        return;
                }
                
                if (!isNumeric(txtEdicao.getText())){
                        JOptionPane.showMessageDialog(null, "Dados inseridos incorretamente!", "Erro ao inserir", JOptionPane.OK_OPTION);
                        txtEdicao.requestFocus();
                        return;
                }
                
                Livro livro = new Livro();
                LivroDao dao = new LivroDao();

                livro.setTitulo(txtTitulo.getText());
                livro.setIsbn(txtISBN.getText());
                livro.setEdicao(Integer.valueOf(txtEdicao.getText()));
                livro.setDescricao(txtDescricao.getText());
                livro.setAutor(txtAutor.getText());
                livro.setId(Integer.valueOf(txtId.getText()));

                dao.atualizar(livro);

                limparCampos();
                txtTitulo.requestFocus();
                mostrarTabela("");
        }

        @FXML
        void handlerExcluirLivro(ActionEvent event) {
                if (txtTitulo.getText().isEmpty()){
                        JOptionPane.showMessageDialog(null, "Informe o título do livro!", "Dados incompletos", JOptionPane.OK_OPTION);
                        return;
                }

                String titulo;
                LivroDao dao = new LivroDao();

                titulo = txtTitulo.getText();

                dao.deletar(titulo);

                limparCampos();
                txtTitulo.requestFocus();
                mostrarTabela("");
        }

}
