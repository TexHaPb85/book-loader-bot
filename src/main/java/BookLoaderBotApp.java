import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import util.FileSupplierUtil;

public class BookLoaderBotApp extends Application {
    public static Stage mainStage;

    @Override
    public void start(Stage primaryStage) throws Exception {
        mainStage=primaryStage;
        Parent root = FXMLLoader.load(getClass().getResource("main-menu.fxml"));
        mainStage.setTitle("Загружчик книг в книжкин дом");
        mainStage.setScene(new Scene(root));
        mainStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
