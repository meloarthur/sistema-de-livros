package controller;

import javax.swing.JOptionPane;

import dao.LivroDao;
import domain.Livro;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class LivroController {

        @FXML
        private TextField txtId;
    
        @FXML
        private TextField txtAutor;
    
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

        public boolean isNumeric(String str) {
                try {
                        int valor = Integer.parseInt(str);
                        return true;
                } catch (NumberFormatException e) {
                        return false;
                }
        }

        void limparCampos(){
                txtAutor.setText("");
                txtEdicao.setText("");
                txtISBN.setText("");
                txtDescricao.setText("");
                txtTitulo.setText("");
                txtId.setText("");
        }

        @FXML
        void handlerLimparCampos(ActionEvent event) {
                limparCampos();
                txtTitulo.requestFocus();
        }

        @FXML
        void handlerCadastrarLivro(ActionEvent event) {
                if (txtTitulo.getText().isEmpty() || txtAutor.getText().isEmpty() || txtDescricao.getText().isEmpty() || txtEdicao.getText().isEmpty() || txtISBN.getText().isEmpty()){
                        JOptionPane.showMessageDialog(null, "Preencha os campos vazios!", "Dados incompletos", JOptionPane.OK_OPTION);
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

                dao.inserir(livro);

                limparCampos();
                txtTitulo.requestFocus();
        }

        @FXML
        void handlerExcluirLivro(ActionEvent event) {
                Livro livro = new Livro();
                LivroDao dao = new LivroDao();

                livro.setId(Integer.valueOf(txtId.getText()));

                dao.deletar(livro);

                limparCampos();
                txtTitulo.requestFocus();
        }

        @FXML
        void handlerAtualizarLivro(ActionEvent event) {

        }

}
