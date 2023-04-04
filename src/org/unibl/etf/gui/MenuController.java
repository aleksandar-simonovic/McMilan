package org.unibl.etf.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import org.unibl.etf.app.McMilan;
import org.unibl.etf.db.dao.TrgovacDAO;
import org.unibl.etf.db.dto.TrgovacDTO;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class MenuController implements Initializable {
    private Alert alert;

    private static TrgovacDTO logovaniTrgovac;
    public static void setTrgovac(TrgovacDTO logovaniTrgovac) {
        MenuController.logovaniTrgovac = logovaniTrgovac;
    }
    public static TrgovacDTO getTrgovac() {
        return MenuController.logovaniTrgovac;
    }

    @FXML
    private Label imeTrgovcaLabel;
    @FXML
    private Label usernameLabel;
    @FXML
    private ImageView trgovacImageView;
    @FXML
    private ImageView prodajaImageView;
    @FXML
    private ImageView skladisteImageView;
    @FXML
    private ImageView statistikaImageView;
    @FXML
    private ImageView logoutImageView;
    @FXML
    private ImageView modelNamjestajaImageView;
    @FXML
    private ImageView materijalImageView;
    @FXML
    private ImageView logoutInfo;
    @FXML
    private ImageView materijalInfo;
    @FXML
    private ImageView modelNamjestajaInfo;
    @FXML
    private ImageView prodajaInfo;
    @FXML
    private ImageView salonInfo;
    @FXML
    private ImageView statistikaInfo;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        TrgovacDTO trgovac = new TrgovacDAO().dohvatiTrgovcaPoUsername(logovaniTrgovac.getUsername());
        setTrgovac(trgovac);
        trgovacImageView.setImage(new Image(new File("src/img/clerk.png").toURI().toString()));
        prodajaImageView.setImage(new Image(new File("src/img/trade.png").toURI().toString()));
        skladisteImageView.setImage(new Image(new File("src/img/warehouse.png").toURI().toString()));
        statistikaImageView.setImage(new Image(new File("src/img/trend.png").toURI().toString()));
        logoutImageView.setImage(new Image(new File("src/img/logout.png").toURI().toString()));
        modelNamjestajaImageView.setImage(new Image(new File("src/img/furnitureModel.png").toURI().toString()));
        materijalImageView.setImage(new Image(new File("src/img/wood.png").toURI().toString()));
        logoutInfo.setImage(new Image(new File("src/img/info.png").toURI().toString()));
        materijalInfo.setImage(new Image(new File("src/img/info.png").toURI().toString()));
        modelNamjestajaInfo.setImage(new Image(new File("src/img/info.png").toURI().toString()));
        prodajaInfo.setImage(new Image(new File("src/img/info.png").toURI().toString()));
        salonInfo.setImage(new Image(new File("src/img/info.png").toURI().toString()));
        statistikaInfo.setImage(new Image(new File("src/img/info.png").toURI().toString()));
        imeTrgovcaLabel.setText(logovaniTrgovac.getIme() + " " + logovaniTrgovac.getPrezime());
        usernameLabel.setText(logovaniTrgovac.getUsername());

        alert = new Alert(Alert.AlertType.INFORMATION);
        alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
        alert.setTitle("McMilan");
        ((Stage)alert.getDialogPane().getScene().getWindow()).getIcons().add(new Image("img/icon.png"));
    }

    @FXML
    void prodajaButtonOnAction(ActionEvent event) {
        changeScene("/org/unibl/etf/gui/racun.fxml", 800, 600);
    }

    @FXML
    void modelNamjestajaButtonOnAction(ActionEvent event) { changeScene("/org/unibl/etf/gui/modelNamjestaja.fxml", 800, 600); }

    @FXML
    void statistikaButtonOnAction(ActionEvent event) {
        changeScene("/org/unibl/etf/gui/statistika.fxml", 800, 600);
    }

    @FXML
    void salonButtonOnAction(ActionEvent event) {
        changeScene("/org/unibl/etf/gui/salon.fxml", 800, 600);
    }

    @FXML
    void materijalButtonOnAction(ActionEvent event) {
        changeScene("/org/unibl/etf/gui/materijal.fxml", 800, 600);
    }

    @FXML
    void logoutButtonOnAction(ActionEvent event) {
        logovaniTrgovac = null;
        changeScene("/org/unibl/etf/gui/login.fxml", 350, 470);
    }

    @FXML
    void modelNamjestajaInfoAction(MouseEvent event) {
        alert.setHeaderText("Model namještaja info");
        alert.setContentText("Ova opcija demonstrira rad sa šifarnicima. Demonstriran je rad sa CRUD operacijama (Create, Read, Uprade, Delete).");
        alert.showAndWait();
    }

    @FXML
    void materijalInfoAction(MouseEvent event) {
        alert.setHeaderText("Materijal info");
        alert.setContentText("Ova opcija demonstrira rad sa šifarnicima. Demonstriran je rad sa CRUD operacijama (Create, Read, Uprade, Delete).");
        alert.showAndWait();
    }

    @FXML
    void prodajaInfoAction(MouseEvent event) {
        alert.setHeaderText("Prodaja info");
        alert.setContentText("Ova opcija predstavlja poslovnu logiku aplikacije, a pri radu se koriste uskladištene procedure i trigeri.");
        alert.showAndWait();
    }

    @FXML
    void salonInfoAction(MouseEvent event) {
        alert.setHeaderText("Uvid u stanje salona info");
        alert.setContentText("Ova opcija demonstrira rad sa pogledima, tj. koristi se pogled salon_statistika.");
        alert.showAndWait();
    }

    @FXML
    void statistikaInfoAction(MouseEvent event) {
        alert.setHeaderText("Uvid u statistiku prodaje info");
        alert.setContentText("Ova opcija demonstrira rad sa pogledima, tj. koristi se pogled trgovac_view koji spaja podatke iz 7 tabela i demonstrira rad sa agregatnim funkcijama.");
        alert.showAndWait();
    }

    @FXML
    void logoutInfoAction(MouseEvent event) {
        alert.setHeaderText("Logout info");
        alert.setContentText("Ova opcija omogućava promjenu korisnika, tj. demonstrira rad sa tabelom KORISNICKI_NALOG, gdje se pri login-u koriste uskladištene procedure za provjeru login kredencijala.");
        alert.showAndWait();
    }

    private void changeScene(String sceneFxml, int dim1, int dim2) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource(sceneFxml));
            Stage newStage = new Stage();
            newStage.setScene(new Scene(root, dim1, dim2));
            newStage.setResizable(false);
            newStage.setTitle("McMilan");
            newStage.getIcons().add(new Image(McMilan.class.getResourceAsStream("/img/icon.png")));
            newStage.show();

            Stage oldStage = (Stage) trgovacImageView.getScene().getWindow();
            oldStage.close();
        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }
}
