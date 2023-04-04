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
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class DodajStavkuNaRacunController implements Initializable {
    Alert alertError;
    Alert alertSuccess;

    @FXML
    private TextField kolicinaTextField;
    @FXML
    private Button otkaziButton;
    @FXML
    private ChoiceBox<String> modelChoiceBox;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        List<ModelNamjestajaDTO> all = new ModelNamjestajaDAO().dohvatiSveModeleNamjestaja();
        List<ModelNamjestajaDTO> result = new ArrayList<>();
        for(ModelNamjestajaDTO x : all) {
            if(new ModelNamjestajaSeCuvaUSalonuNamjestajaDAO().daLiModelNamjestajaPostojiUSalonu(MenuController.getTrgovac().getIdSalonaNamjestaja(), x.getIdModelaNamjestaja())) {
                result.add(x);
            }
        }
        for(ModelNamjestajaDTO x : result) {
            modelChoiceBox.getItems().add(x.getNazivModela());
        }
        alertError = new Alert(Alert.AlertType.ERROR);
        alertError.setTitle("McMilan");
        alertSuccess = new Alert(Alert.AlertType.INFORMATION);
        alertSuccess.setTitle("McMilan");
        ((Stage)alertError.getDialogPane().getScene().getWindow()).getIcons().add(new Image("img/icon.png"));
        ((Stage)alertSuccess.getDialogPane().getScene().getWindow()).getIcons().add(new Image("img/icon.png"));
    }

    @FXML
    void otkaziButtonOnAction(ActionEvent event) {
        closeStage();
    }

    @FXML
    void prihvatiButtonOnAction(ActionEvent event) {
        String choice = modelChoiceBox.getValue();
        ModelNamjestajaDTO model = new ModelNamjestajaDAO().dohvatiModelNamjestajaPoNazivuModela(choice);

        if(new FiskalniRacunStavkaDAO().daLiRacunSadrziModel(RacunController.getSelection().getIdRacuna(), model.getIdModelaNamjestaja())) {
            alertError.setHeaderText("Taj model namještaja se već nalazi na ovom računu. Koristite opciju OBRIŠI ili AŽURIRAJ");
            alertError.setContentText("Provjerite unos pa pokušajte ponovo");
            alertError.showAndWait();
            return;
        }

        Integer kolicina;
        try {
            kolicina = Integer.parseInt(kolicinaTextField.getText().trim());
            if(kolicina <= 0) {
                alertError.setHeaderText("Polje količina mora biti pozitivno!");
                alertError.setContentText("Provjerite unos pa pokušajte ponovo");
                alertError.showAndWait();
                return;
            } else if(!new ModelNamjestajaSeCuvaUSalonuNamjestajaDAO().daLiImaDovoljnoNamjestajaUSalonu(model.getIdModelaNamjestaja(), RacunController.getSelection().getIdSalonaNamjestaja(), kolicina)) {
                alertError.setHeaderText("U salonu nema dovoljno namještaja");
                alertError.setContentText("Provjerite unos pa pokušajte ponovo");
                alertError.showAndWait();
                return;
            }
        } catch (NumberFormatException ex) {
            alertError.setHeaderText("Količina mora biti ispravno unijeta.");
            alertError.setContentText("Provjerite unos pa pokušajte ponovo");
            alertError.showAndWait();
            return;
        }

        if(new FiskalniRacunStavkaDAO().dodaj(RacunController.getSelection().getIdRacuna(), model.getIdModelaNamjestaja(), kolicina)) {
            alertSuccess.setHeaderText("Uspješno ste dodali novu stavku na račun");
            alertSuccess.showAndWait();
            closeStage();
        } else {
            alertError.setHeaderText("Greška pri dodavanju namještaja");
            alertError.showAndWait();
            clearForm();
            return;
        }
    }

    private void clearForm(){
        kolicinaTextField.clear();
        modelChoiceBox.getSelectionModel().clearSelection();
    }

    private void closeStage(){
        Stage stage = (Stage) otkaziButton.getScene().getWindow();
        stage.close();
    }
}