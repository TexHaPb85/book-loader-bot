package controller;

import entities.Good;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import services.DownLoadService;
import services.UploadService;
import util.FileSupplierUtil;
import util.PropertiesSupplier;
import util.SettingsUtil;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class MainMenuController {
    public TextField urlField;
    public Button loadBtn;
    public TextField nameF;
    public TextField authorF;
    public TextArea descriptionF;
    public TextField priceF;
    public TextField keyWordsF;
    public TextField categoriesF;
    public ChoiceBox browserChoiceBox;
    public CheckBox publishCheck;
    public TextField driverPathF;
    public TextField propertiesField;

    private boolean isSet = false;
    private String propertiesPath = getClass().getClassLoader().getResource("application.properties").getPath();

    @FXML
    private void initialize() {
        driverPathF.setPromptText(PropertiesSupplier.getAppProperties().getProperty("path_to_geckodriver"));
        propertiesField.setPromptText(propertiesPath);
    }

    public void downloadByUrl(ActionEvent actionEvent) {
        try {
            if (!isSet) {
                SettingsUtil.setUpApplication((String) browserChoiceBox.getValue());
                isSet = true;
            }

            Good good = DownLoadService.loadByUrl(urlField.getText());
            nameF.setText(good.getTitleOfGood());
            authorF.setText(good.getAuthor());
            descriptionF.setWrapText(true);
            descriptionF.setText(good.getDescription());
            priceF.setText(String.valueOf(good.getPrice()));
            keyWordsF.setText(good.getKeyWordsStr());
            categoriesF.setText(good.getCategoriesStr());
        } catch (Exception iae) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(iae.getLocalizedMessage());
            alert.setContentText(iae.getMessage());
            alert.show();
        }
    }

    public void uploadToBookdom(ActionEvent actionEvent) {
        try {
            String title = nameF.getText();
            String author = authorF.getText();
            String description = descriptionF.getText();
            int price = Integer.parseInt(priceF.getText());
            List<String> kw = Arrays.stream(keyWordsF.getText()
                    .split("[,]"))
                    .map(String::trim)
                    .collect(Collectors.toList());
            List<String> categories = Arrays.stream(categoriesF.getText().split("[,]")).map(String::trim).collect(Collectors.toList());
            Good good = new Good(author, title, description, price, kw, categories);
            urlField.clear();

            UploadService uploadService = UploadService.getInstance();
            uploadService.uploadToBookDom(good, publishCheck.isSelected());
        } catch (Exception iae) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(iae.getLocalizedMessage());
            alert.setContentText(iae.getMessage());
            alert.show();
        }
    }

    private void clear() {
        urlField.clear();
        nameF.clear();
        authorF.clear();
        descriptionF.clear();
        priceF.clear();
        keyWordsF.clear();
        categoriesF.clear();
    }

    public void exit(ActionEvent actionEvent) {
        System.exit(0);
    }

    public void chooseDriver(ActionEvent actionEvent) throws IOException {
        try {
            File file = FileSupplierUtil.openFile(new Stage());
            PropertiesSupplier.getAppProperties().setProperty("path_to_geckodriver", file.getAbsolutePath());
            OutputStream propertiesOutputStream = new FileOutputStream(propertiesPath);
            PropertiesSupplier.getAppProperties().store(propertiesOutputStream, "path to geckodriver.exe is changed");

            isSet = false;
            initialize();
        } catch (Exception iae) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(iae.getLocalizedMessage());
            alert.setContentText(iae.getMessage());
            alert.show();
        }

    }

    public void setPropertiesPath(ActionEvent actionEvent) {
        try {
            File file = FileSupplierUtil.openFile(new Stage());
            propertiesPath = file.getAbsolutePath();
            isSet = false;
            initialize();
        } catch (Exception iae) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(iae.getLocalizedMessage());
            alert.setContentText(iae.getMessage());
            alert.show();
        }
    }
}
