package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class PrincipalController {

    @FXML
    private Label lbNomeInserido;

    @FXML
    private Button btnMostrarNome;

    @FXML
    private TextField txtNome;

    @FXML
    void handlerMostrarNome(ActionEvent event) {
        lbNomeInserido.setText(txtNome.getText());
        lbNomeInserido.setVisible(true);
    }

}
