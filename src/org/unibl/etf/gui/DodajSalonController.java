package org.unibl.etf.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.unibl.etf.db.dao.ModelNamjestajaDAO;
import org.unibl.etf.db.dao.ModelNamjestajaSeCuvaUSalonuNamjestajaDAO;
import org.unibl.etf.db.dto.ModelNamjestajaDTO;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class DodajSalonController implements Initializable {
    @FXML
    private TextField kolicinaTextField;
    @FXML
    private Button otkaziButton;
    @FXML
    private ChoiceBox<String> modelChoiceBox;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        List<ModelNamjestajaDTO> result = new ModelNamjestajaDAO().dohvatiSveModeleNamjestaja();
        for(ModelNamjestajaDTO x : result) {
            modelChoiceBox.getItems().add(x.getNazivModela());
        }
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
                alert.setHeaderText("Polje količina mora biti pozitivno!");
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

        String choice = modelChoiceBox.getValue();
        ModelNamjestajaDTO model = new ModelNamjestajaDAO().dohvatiModelNamjestajaPoNazivuModela(choice);

        if(new ModelNamjestajaSeCuvaUSalonuNamjestajaDAO().daLiModelNamjestajaPostojiUSalonu(MenuController.getTrgovac().getIdSalonaNamjestaja(), model.getIdModelaNamjestaja())) {
            alert.setHeaderText("Taj model namještaja već postoji u vašem salonu, koristite opciju AŽURIRAJ ili OBRIŠI");
            alert.setContentText("Provjerite unos pa pokušajte ponovo");
            alert.showAndWait();
            return;
        }

        if(new ModelNamjestajaSeCuvaUSalonuNamjestajaDAO().dodaj(model.getIdModelaNamjestaja(), MenuController.getTrgovac().getIdSalonaNamjestaja(), kolicina)) {
            success.setHeaderText("Uspješno ste dodali novi namještaj u vaš salon");
            success.showAndWait();
            closeStage();
        } else {
            alert.setHeaderText("Greška pri dodavanju namještaja");
            alert.showAndWait();
            clearForm();
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