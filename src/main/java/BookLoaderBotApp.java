import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class BookLoaderBotApp extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        Stage mainStage=primaryStage;
        Parent root = FXMLLoader.load(getClass().getResource("main-menu.fxml"));
        mainStage.setTitle("Загружчик книг в книжкин дом");
        mainStage.setScene(new Scene(root));
        mainStage.show();
    }
}
