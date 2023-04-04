package org.unibl.etf.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.unibl.etf.db.dao.ModelNamjestajaSeCuvaUSalonuNamjestajaDAO;
import org.unibl.etf.db.dao.SalonStatistikaDAO;
import org.unibl.etf.db.dto.SalonStatistikaDTO;
import java.net.URL;
import java.util.ResourceBundle;

public class IzmijeniSalonController implements Initializable {
    @FXML
    private TextField kolicinaTextField;
    @FXML
    private Button otkaziButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        SalonStatistikaDTO m = SalonController.getSelection();
        kolicinaTextField.setText(m.getKolicina() == null ? "" : String.valueOf(m.getKolicina()));
    }

    @FXML
    void otkaziButtonOnAction(ActionEvent event) {
        closeStage();
    }

    @FXML
    void prihvatiButtonOnAction(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("McMilan");
        Alert success = new Alert(Alert.AlertType.INFORMATION);
        success.setTitle("McMilan");
        Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
        stage.getIcons().add(new Image("img/icon.png"));

        Integer kolicina;
        try {
            kolicina = Integer.parseInt(kolicinaTextField.getText().trim());
            if(kolicina <= 0) {
                alert.setHeaderText("Polje količina mora biti pozitivno! Ako želite da obrišete koristite opciju OBRIŠI");
                alert.setContentText("Provjerite unos pa pokušajte ponovo");
                alert.showAndWait();
                return;
            }
        } catch (NumberFormatException ex) {
            alert.setHeaderText("Količina mora biti ispravno unijeta.");
            alert.setContentText("Provjerite unos pa pokušajte ponovo");
            alert.showAndWait();
            return;
        }

        if(new ModelNamjestajaSeCuvaUSalonuNamjestajaDAO().azurirajKolicinu(SalonController.getSelection().getIdModelaNamjestaja(), MenuController.getTrgovac().getIdSalonaNamjestaja(), kolicina)) {
            success.setHeaderText("Uspješno ste ažurirali trenutno stanje u salonu");
            success.showAndWait();
            closeStage();
        } else {
            alert.setHeaderText("Greška pri ažuriranju trenutnog stanja u salonu");
            alert.showAndWait();
            clearForm();
            return;
        }
    }

    private void closeStage(){
        SalonController.setSelection(null);
        Stage stage = (Stage) otkaziButton.getScene().getWindow();
        stage.close();
    }

    private void clearForm(){
        kolicinaTextField.clear();
    }
}