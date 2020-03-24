import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class BookLoaderBotApp extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("main-menu.fxml"));
        primaryStage.setTitle("Загружчик книг в книжкин дом");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }
}
