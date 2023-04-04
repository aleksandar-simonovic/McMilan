package org.unibl.etf.gui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.event.ActionEvent;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import org.unibl.etf.app.McMilan;
import org.unibl.etf.db.dao.KorisnickiNalogDAO;
import org.unibl.etf.db.dao.TrgovacDAO;
import org.unibl.etf.db.dto.KorisnickiNalogDTO;
import org.unibl.etf.db.dto.TrgovacDTO;

public class LoginController implements Initializable {
    private Alert alert;

    @FXML
    private Button cancelButton;
    @FXML
    private ImageView lockImageView;
    @FXML
    private TextField usernameTextField;
    @FXML
    private PasswordField enterPasswordField;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        lockImageView.setImage(new Image(new File("src/img/login-logo.png").toURI().toString()));
        alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("McMilan");
        ((Stage)alert.getDialogPane().getScene().getWindow()).getIcons().add(new Image("img/icon.png"));
    }

    @FXML
    void loginButtonOnAction(ActionEvent event) {
        login();
    }

    @FXML
    void cancelButtonOnAction(ActionEvent event) {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    private void login() {
        if(usernameTextField.getText().isBlank() == false && enterPasswordField.getText().isBlank() == false) {
            validateLogin(usernameTextField.getText(), enterPasswordField.getText());
        } else {
            alert.setHeaderText("Morate unijeti korisnicko ime i lozinku!");
            alert.setContentText("Provjerite unos pa pokusajte ponovo");
            alert.showAndWait();
        }
    }

    private void validateLogin(String username, String password) {
        KorisnickiNalogDTO account = new KorisnickiNalogDAO().dohvatiKorisnickiNalog(username, password);
        if(null == account) {
            alert.setHeaderText("Pogresni login podaci!");
            alert.setContentText("Provjerite unos pa pokusajte ponovo");
            alert.showAndWait();
        } else {
            TrgovacDTO trgovac = new TrgovacDAO().dohvatiTrgovcaPoUsername(account.getUsername());
            if(trgovac == null) {
                alert.setHeaderText("Aplikaciju trenutno mogu koristiti samo trgovci!");
                alert.setContentText("Provjerite unos pa pokusajte ponovo");
                alert.showAndWait();
            } else {
                switchToMenu(trgovac);
            }
        }
    }

    private void switchToMenu(TrgovacDTO trgovac) {
        try {
            MenuController.setTrgovac(trgovac);
            Parent root = FXMLLoader.load(getClass().getResource("/org/unibl/etf/gui/menu.fxml"));
            Stage menuStage = new Stage();
            menuStage.setScene(new Scene(root, 800, 600));
            menuStage.setResizable(false);
            menuStage.setTitle("McMilan");
            menuStage.getIcons().add(new Image(McMilan.class.getResourceAsStream("/img/icon.png")));
            menuStage.show();

            Stage stage = (Stage) cancelButton.getScene().getWindow();
            stage.close();

        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }
}