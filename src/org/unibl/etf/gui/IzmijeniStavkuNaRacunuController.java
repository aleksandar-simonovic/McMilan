package org.unibl.etf.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.unibl.etf.db.dao.FiskalniRacunStavkaDAO;
import org.unibl.etf.db.dao.ModelNamjestajaDAO;
import org.unibl.etf.db.dao.ModelNamjestajaSeCuvaUSalonuNamjestajaDAO;
import org.unibl.etf.db.dto.ModelNamjestajaDTO;

import java.net.URL;
import java.util.ResourceBundle;

public class IzmijeniStavkuNaRacunuController implements Initializable {
    private Integer oldKolicina;

    Alert alertError;
    Alert alertSuccess;

    @FXML
    private TextField kolicinaTextField;
    @FXML
    private Button otkaziButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        alertError = new Alert(Alert.AlertType.ERROR);
        alertError.setTitle("McMilan");
        alertSuccess = new Alert(Alert.AlertType.INFORMATION);
        alertSuccess.setTitle("McMilan");
        ((Stage)alertError.getDialogPane().getScene().getWindow()).getIcons().add(new Image("img/icon.png"));
        ((Stage)alertSuccess.getDialogPane().getScene().getWindow()).getIcons().add(new Image("img/icon.png"));
        kolicinaTextField.setText(IzmijeniRacunController.getSelection().getKolicina().toString());
        oldKolicina = Integer.parseInt(kolicinaTextField.getText());
    }

    @FXML
    void otkaziButtonOnAction(ActionEvent event) {
        closeStage();
    }

    @FXML
    void prihvatiButtonOnAction(ActionEvent event) {
        ModelNamjestajaDTO model = new ModelNamjestajaDAO().dohvatiModelNamjestajaPoNazivuModela(IzmijeniRacunController.getSelection().nazivModela);
        Integer kolicina;
        try {
            kolicina = Integer.parseInt(kolicinaTextField.getText().trim());
            if(kolicina <= 0) {
                alertError.setHeaderText("Polje količina mora biti pozitivno!");
                alertError.setContentText("Provjerite unos pa pokušajte ponovo");
                alertError.showAndWait();
                return;
            }
            if(kolicina > oldKolicina && !new ModelNamjestajaSeCuvaUSalonuNamjestajaDAO().daLiImaDovoljnoNamjestajaUSalonu(model.getIdModelaNamjestaja(),
                    RacunController.getSelection().getIdSalonaNamjestaja(), kolicina - oldKolicina)) {
                alertError.setHeaderText("U salonu nema dovoljno namještaja");
                alertError.setContentText("Provjerite unos pa pokušajte ponovo");
                alertError.showAndWait();
                return;
            }
            new FiskalniRacunStavkaDAO().obrisiStavkuSaRacuna(RacunController.getSelection().getIdRacuna(),
                    RacunStavkaDisplay.dohvatiIdModelaPoNazivuModela(model.getNazivModela()));
            new FiskalniRacunStavkaDAO().dodaj(RacunController.getSelection().getIdRacuna(), model.getIdModelaNamjestaja(), kolicina);
            alertSuccess.setHeaderText("Uspješno ažurirana stavka na računu");
            alertSuccess.showAndWait();
            closeStage();
        } catch (NumberFormatException ex) {
            alertError.setHeaderText("Količina mora biti ispravno unijeta.");
            alertError.setContentText("Provjerite unos pa pokušajte ponovo");
            alertError.showAndWait();
            return;
        }
    }

    private void clearForm(){
        kolicinaTextField.clear();
    }

    private void closeStage(){
        Stage stage = (Stage) otkaziButton.getScene().getWindow();
        stage.close();
    }
}