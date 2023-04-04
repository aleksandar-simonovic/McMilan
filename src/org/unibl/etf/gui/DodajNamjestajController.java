package org.unibl.etf.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.unibl.etf.db.dao.KatalogNamjestajaDAO;
import org.unibl.etf.db.dao.ModelNamjestajaDAO;
import org.unibl.etf.db.dto.ModelNamjestajaDTO;
import java.math.BigDecimal;

public class DodajNamjestajController {
    @FXML
    private TextField bojaTextField;
    @FXML
    private TextField dubinaTextField;
    @FXML
    private Label errorMessageLabel;
    @FXML
    private TextField idKatalogaTextField;
    @FXML
    private TextField idNamjestajaTextField;
    @FXML
    private TextField nazivModelaTextField;
    @FXML
    private Button otkaziButton;
    @FXML
    private TextField prodajnaCijenaTextField;
    @FXML
    private TextField sirinaTextField;
    @FXML
    private TextField tipNamjestajaTextField;
    @FXML
    private TextField visinaTextField;

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

        String nazivModela = nazivModelaTextField.getText().trim();
        if(nazivModela.isEmpty()) {
            alert.setHeaderText("Polje naziv modela ne smije biti prazno!");
            alert.setContentText("Provjerite unos pa pokusajte ponovo");
            alert.showAndWait();
            return;
        }
        if(new ModelNamjestajaDAO().dohvatiModelNamjestajaPoNazivuModela(nazivModela) != null) {
            alert.setHeaderText("Model namjestaja sa tim nazivom vec postoji!");
            alert.setContentText("Provjerite unos pa pokusajte ponovo");
            alert.showAndWait();
            return;
        }

        String tipNamjestaja = tipNamjestajaTextField.getText().trim();
        if(tipNamjestaja.isEmpty()) tipNamjestaja = null;

        Integer idNamjestaja;
        try {
            idNamjestaja = Integer.parseInt(idNamjestajaTextField.getText().trim());
            if(new ModelNamjestajaDAO().dohvatiModelNamjestajaPoIdentifikatoru(idNamjestaja) != null) {
                alert.setHeaderText("Namjestaj sa tim identifikatorom vec postoji!");
                alert.setContentText("Provjerite unos pa pokusajte ponovo");
                alert.showAndWait();
                return;
            }
        } catch (NumberFormatException ex) {
            idNamjestaja = null;
        }

        Double prodajnaCijenaTemp;
        BigDecimal prodajnaCijena;
        try {
            prodajnaCijenaTemp = Double.parseDouble(prodajnaCijenaTextField.getText().trim());
            if(prodajnaCijenaTemp <= 0) {
                alert.setHeaderText("Prodajna cijena mora biti pozitivna");
                alert.setContentText("Provjerite unos pa pokusajte ponovo");
                alert.showAndWait();
                return;
            } else {
                prodajnaCijena = new BigDecimal(prodajnaCijenaTemp);
            }
        } catch (NumberFormatException ex) {
            alert.setHeaderText("Prodajna cijena mora biti ispravno unijeta.");
            alert.setContentText("Provjerite unos pa pokusajte ponovo");
            alert.showAndWait();
            return;
        }

        Double visinaTemp;
        BigDecimal visina;
        try {
            visinaTemp = Double.parseDouble(visinaTextField.getText().trim());
            if(visinaTemp <= 0) {
                alert.setHeaderText("Polje visina mora biti pozitivno!");
                alert.setContentText("Provjerite unos pa pokusajte ponovo");
                alert.showAndWait();
                return;
            } else {
                visina = new BigDecimal(visinaTemp);
            }
        } catch (NumberFormatException ex) {
            visina = null;
        }

        Double sirinaTemp;
        BigDecimal sirina;
        try {
            sirinaTemp = Double.parseDouble(sirinaTextField.getText().trim());
            if(sirinaTemp <= 0) {
                alert.setHeaderText("Polje sirina mora biti pozitivno!");
                alert.setContentText("Provjerite unos pa pokusajte ponovo");
                alert.showAndWait();
                return;
            } else {
                sirina = new BigDecimal(sirinaTemp);
            }
        } catch (NumberFormatException ex) {
            sirina = null;
        }

        Double dubinaTemp;
        BigDecimal dubina;
        try {
            dubinaTemp = Double.parseDouble(dubinaTextField.getText().trim());
            if(dubinaTemp <= 0) {
                alert.setHeaderText("Polje dubina mora biti pozitivno!");
                alert.setContentText("Provjerite unos pa pokusajte ponovo");
                alert.showAndWait();
                return;
            } else {
                dubina = new BigDecimal(dubinaTemp);
            }
        } catch (NumberFormatException ex) {
            dubina = null;
        }

        String boja = bojaTextField.getText().trim();
        if(boja.isEmpty()) boja = null;

        Integer idKataloga;
        try {
            idKataloga = Integer.parseInt(idKatalogaTextField.getText().trim());
            if(new KatalogNamjestajaDAO().dohvatiKatalogPoIdentifikatoru(idKataloga) == null) {
                alert.setHeaderText("Referencirali ste nepostojeci katalog");
                alert.setContentText("Provjerite unos pa pokusajte ponovo");
                alert.showAndWait();
                return;
            }
        } catch (NumberFormatException ex) {
            idKataloga = null;
        }

        ModelNamjestajaDTO userInput = new ModelNamjestajaDTO(
                idNamjestaja,
                tipNamjestaja,
                nazivModela,
                visina,
                sirina,
                dubina,
                boja,
                prodajnaCijena,
                idKataloga);

        if(new ModelNamjestajaDAO().dodajModelNamjestaja(userInput)) {
            success.setHeaderText("Uspjesno ste dodali novi namjestaj");
            success.showAndWait();
            closeStage();
        } else {
            alert.setHeaderText("Greska pri dodavanju modela namjestaja");
            alert.showAndWait();
            clearForm();
            return;
        }
    }

    private void clearForm() {
        bojaTextField.clear();
        dubinaTextField.clear();
        idKatalogaTextField.clear();
        idNamjestajaTextField.clear();
        nazivModelaTextField.clear();
        prodajnaCijenaTextField.clear();
        sirinaTextField.clear();
        tipNamjestajaTextField.clear();
        visinaTextField.clear();
    }

    private void closeStage(){
        Stage stage = (Stage) otkaziButton.getScene().getWindow();
        stage.close();
    }
}