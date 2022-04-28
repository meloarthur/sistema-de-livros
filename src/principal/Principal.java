package principal;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Principal extends Application{
    private static Stage stagePrincipal;

    @Override
    public void start(Stage stage) throws Exception{
        stagePrincipal = stage;
        mostrarMenu();
    }

    public static void mostrarMenu(){
        try{
            Parent root = FXMLLoader.load(Principal.class.getResource("../javafx/menu.fxml"));
            Scene scene = new Scene(root);

            stagePrincipal.setTitle("Sistema de Livros");
            stagePrincipal.setScene(scene);
            stagePrincipal.show();
            stagePrincipal.centerOnScreen();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void mostrarLivro(){
        try {
            Parent root = FXMLLoader.load(Principal.class.getResource("../javafx/livros.fxml"));
            Scene scene = new Scene(root);

            stagePrincipal.setTitle("Sistema de Livros - Consulta de Livros");
            stagePrincipal.setScene(scene);
            stagePrincipal.show();
            stagePrincipal.centerOnScreen();
        } catch (Exception e) {
            e.printStackTrace();
        } 
    }

    public static void mostrarAutor(){
        try {
            Parent root = FXMLLoader.load(Principal.class.getResource("../javafx/autores.fxml"));
            Scene scene = new Scene(root);

            stagePrincipal.setTitle("Sistema de Livros - Consulta de Autores");
            stagePrincipal.setScene(scene);
            stagePrincipal.show();
            stagePrincipal.centerOnScreen();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
