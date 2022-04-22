import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Principal extends Application{
    @Override
    public void start(Stage stagePrincipal) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("./javafx/livros.fxml"));
        Scene scene = new Scene(root);

        stagePrincipal.setTitle("Sistema de Livros");
        stagePrincipal.setScene(scene);
        stagePrincipal.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
