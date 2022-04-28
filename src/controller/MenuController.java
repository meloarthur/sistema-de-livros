package controller;

import javax.swing.JOptionPane;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import principal.Principal;

public class MenuController {

    @FXML
    private Button btnSair;

    @FXML
    private Button btnCadAutores;

    @FXML
    private Button btnCadLivros;

    @FXML
    void handlerCadLivros(ActionEvent event) {
        Principal.mostrarLivro();
    }

    @FXML
    void handlerCadAutores(ActionEvent event) {
        Principal.mostrarAutor();
    }

    @FXML
    void handlerSair(ActionEvent event) {
        String mensagem = "Deseja fechar o sistema?";
        String title = "Confirmar operação";
        int res = JOptionPane.showConfirmDialog(null, mensagem, title, JOptionPane.YES_NO_OPTION);

        if (res == 0)
            System.exit(0);
    }

}
