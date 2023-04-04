package org.unibl.etf.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import org.unibl.etf.app.McMilan;
import org.unibl.etf.db.dto.TrgovacDTO;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class StatistikaController implements Initializable {
    @FXML
    private Button backButton;
    @FXML
    private ImageView statistikaImageView;
    @FXML
    private ImageView backImageView;
    @FXML
    private Label imeTrgovcaLabel;
    @FXML
    private Label usernameLabel;
    @FXML
    private Label ukupnoRacunaLabel;
    @FXML
    private Label radnoMjestoLabel;
    @FXML
    private Label ukupanIznosLabel;
    @FXML
    private Label ugovorOdLabel;
    @FXML
    private Label ugovorDoLabel;
    @FXML
    private Label imeLabel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        TrgovacDTO trgovac = MenuController.getTrgovac();
        statistikaImageView.setImage(new Image(new File("src/img/trend.png").toURI().toString()));
        backImageView.setImage(new Image(new File("src/img/previous.png").toURI().toString()));
        imeTrgovcaLabel.setText(trgovac.getIme() + " " + trgovac.getPrezime());
        imeLabel.setText(trgovac.getIme() + " " + trgovac.getPrezime());
        usernameLabel.setText(trgovac.getUsername());
        ukupnoRacunaLabel.setText(trgovac.getUkupnoIzdatihRacuna().toString());
        radnoMjestoLabel.setText(trgovac.getNazivSalona());
        ukupanIznosLabel.setText(trgovac.getUkupanIznosProdaje().toString() + " BAM");
        ugovorOdLabel.setText(trgovac.getUgovorOd().toString());
        ugovorDoLabel.setText(trgovac.getUgovorDo().toString());
    }

    @FXML
    void backButtonOnAction(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/org/unibl/etf/gui/menu.fxml"));
            Stage menuStage = new Stage();
            menuStage.setScene(new Scene(root, 800, 600));
            menuStage.setResizable(false);
            menuStage.setTitle("McMilan");
            menuStage.getIcons().add(new Image(McMilan.class.getResourceAsStream("/img/icon.png")));
            menuStage.show();

            Stage stage = (Stage) backButton.getScene().getWindow();
            stage.close();
        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }
}
