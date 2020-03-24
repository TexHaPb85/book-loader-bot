package controller;

import entities.Good;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.stage.Stage;
import services.DownLoadService;
import services.UploadService;
import util.FileSupplierUtil;
import util.SettingsUtil;
import util.WebDriverSupplier;

import java.io.File;
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

    private boolean isSet = false;

    public void downloadByUrl(ActionEvent actionEvent) {
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
    }

    public void uploadToBookdom(ActionEvent actionEvent) {
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

    public void chooseDriver(ActionEvent actionEvent) {
        File file = FileSupplierUtil.openFile(new Stage());
        System.out.println(file.getPath());
    }
}
