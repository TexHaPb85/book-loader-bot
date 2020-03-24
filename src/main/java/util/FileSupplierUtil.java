package util;

import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

public class FileSupplierUtil {
    public static File openFile(Stage stage) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Відкрий мене");
        fileChooser.getExtensionFilters().addAll(          //задаємо розширення файлів
                new FileChooser.ExtensionFilter("Всі файли", "*.*"),
                new FileChooser.ExtensionFilter("Файл .exe", ".exe")
        );

        return fileChooser.showOpenDialog(stage);
    }
}
