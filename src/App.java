import dao.LivroDao;
import domain.Livro;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application{
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("hellofx.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 400, 300));
        primaryStage.show();

        Livro livro = new Livro();

        livro.setTitulo("Harry Potter");
        livro.setIsbn("IDSN234SD");
        livro.setEdicao(14);
        livro.setAutor("J.K. Rowling");
        livro.setDescricao("Uma criança da Inglaterra que descobre ser um bruxo.");

        LivroDao livroDao = new LivroDao();
        livroDao.obterTodos();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
