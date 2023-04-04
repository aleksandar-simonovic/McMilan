package org.unibl.etf.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import org.unibl.etf.db.dao.KatalogNamjestajaDAO;
import org.unibl.etf.db.dao.ModelNamjestajaDAO;
import org.unibl.etf.db.dto.ModelNamjestajaDTO;
import java.math.BigDecimal;
import java.net.URL;
import java.util.ResourceBundle;

public class IzmijeniNamjestajController implements Initializable {
    private int id;
    private String naziv;

    @FXML
    private TextField bojaTextField;
    @FXML
    private TextField dubinaTextField;
    @FXML
    private TextField idKatalogaTextField;
    @FXML
    private TextField idNamjestajaTextField;
    @FXML
    private TextField nazivModelaTextField;
    @FXML
    private TextField prodajnaCijenaTextField;
    @FXML
    private TextField sirinaTextField;
    @FXML
    private TextField tipNamjestajaTextField;
    @FXML
    private TextField visinaTextField;
    @FXML
    private Button otkaziButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ModelNamjestajaDTO sm = ModelNamjestajaController.getSelectedModel();
        id = sm.getIdModelaNamjestaja();
        naziv = sm.getNazivModela();
        bojaTextField.setText(sm.getBoja() == null ? "" : sm.getBoja());
        dubinaTextField.setText(sm.getDubinaCm() == null ? "" : String.valueOf(sm.getDubinaCm()));
        idKatalogaTextField.setText(sm.getIdKataloga() == null ? "" : String.valueOf(sm.getIdKataloga()));
        idNamjestajaTextField.setText(sm.getIdModelaNamjestaja() == null ? "" : String.valueOf(sm.getIdModelaNamjestaja()));
        nazivModelaTextField.setText(sm.getNazivModela() == null ? "" : sm.getNazivModela());
        prodajnaCijenaTextField.setText(sm.getProdajnaCijena() == null ? "" : String.valueOf(sm.getProdajnaCijena()));
        sirinaTextField.setText(sm.getSirinaCm() == null ? "" : String.valueOf(sm.getSirinaCm()));
        tipNamjestajaTextField.setText(sm.getTipNamjestaja() == null ? "" : sm.getTipNamjestaja());
        visinaTextField.setText(sm.getVisinaCm() == null ? "" : String.valueOf(sm.getVisinaCm()));
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
        success.getDialogPane().setGraphic(new ImageView("/img/success.png"));
        success.setTitle("McMilan");
        Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
        stage.getIcons().add(new Image("img/icon.png"));

        String nazivModela = nazivModelaTextField.getText().trim();

        String tipNamjestaja = tipNamjestajaTextField.getText().trim();
        if(tipNamjestaja.isEmpty()) tipNamjestaja = null;

        String boja = bojaTextField.getText().trim();
        if(boja.isEmpty()) boja = null;

        Integer idNamjestaja;
        Integer idKataloga;
        Double prodajnaCijenaTemp;
        BigDecimal prodajnaCijena;
        Double visinaTemp;
        BigDecimal visina;
        Double sirinaTemp;
        BigDecimal sirina;
        Double dubinaTemp;
        BigDecimal dubina;

        if(nazivModela.isEmpty()) {
            alert.setHeaderText("Polje naziv modela ne smije biti prazno!");
            alert.setContentText("Provjerite unos pa pokušajte ponovo");
            alert.showAndWait();
            return;
        }

        if(!naziv.equals(nazivModela) && new ModelNamjestajaDAO().dohvatiModelNamjestajaPoNazivuModela(nazivModela) != null) {
            alert.setHeaderText("Model namještaja sa tim nazivom već postoji!");
            alert.setContentText("Provjerite unos pa pokušajte ponovo");
            alert.showAndWait();
            return;
        }

        try {
            prodajnaCijenaTemp = Double.parseDouble(prodajnaCijenaTextField.getText().trim());
            if(prodajnaCijenaTemp <= 0) {
                alert.setHeaderText("Prodajna cijena mora biti pozitivna!");
                alert.setContentText("Provjerite unos pa pokušajte ponovo");
                alert.showAndWait();
                return;
            } else {
                prodajnaCijena = new BigDecimal(prodajnaCijenaTemp);
            }
        } catch (NumberFormatException ex) {
            alert.setHeaderText("Prodajna cijena mora biti ispravno unijeta.");
            alert.setContentText("Provjerite unos pa pokušajte ponovo");
            alert.showAndWait();
            return;
        }

        try {
            visinaTemp = Double.parseDouble(visinaTextField.getText().trim());
            if(visinaTemp <= 0) {
                alert.setHeaderText("Polje visina mora biti pozitivno!");
                alert.setContentText("Provjerite unos pa pokušajte ponovo");
                alert.showAndWait();
                return;
            } else {
                visina = new BigDecimal(visinaTemp);
            }
        } catch (NumberFormatException ex) {
            visina = null;
        }

        try {
            sirinaTemp = Double.parseDouble(sirinaTextField.getText().trim());
            if(sirinaTemp <= 0) {
                alert.setHeaderText("Polje širina mora biti pozitivno!");
                alert.setContentText("Provjerite unos pa pokušajte ponovo");
                alert.showAndWait();
                return;
            } else {
                sirina = new BigDecimal(sirinaTemp);
            }
        } catch (NumberFormatException ex) {
            sirina = null;
        }

        try {
            dubinaTemp = Double.parseDouble(dubinaTextField.getText().trim());
            if(dubinaTemp <= 0) {
                alert.setHeaderText("Polje dubina mora biti pozitivno!");
                alert.setContentText("Provjerite unos pa pokušajte ponovo");
                alert.showAndWait();
                return;
            } else {
                dubina = new BigDecimal(dubinaTemp);
            }
        } catch (NumberFormatException ex) {
            dubina = null;
        }

        try {
            idNamjestaja = Integer.parseInt(idNamjestajaTextField.getText().trim());
            if(idNamjestaja != id && new ModelNamjestajaDAO().dohvatiModelNamjestajaPoIdentifikatoru(idNamjestaja) != null) {
                alert.setHeaderText("Namještaj sa tim identifikatorom već postoji!");
                alert.setContentText("Provjerite unos pa pokušajte ponovo");
                alert.showAndWait();
                return;
            }
        } catch (NumberFormatException ex) {
            idNamjestaja = null;
        }

        try {
            idKataloga = Integer.parseInt(idKatalogaTextField.getText().trim());
            if(new KatalogNamjestajaDAO().dohvatiKatalogPoIdentifikatoru(idKataloga) == null) {
                alert.setHeaderText("Referencirali ste nepostojeći katalog");
                alert.setContentText("Provjerite unos pa pokušajte ponovo");
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

        if(new ModelNamjestajaDAO().azurirajModelNamjestaja(userInput)) {
            success.setHeaderText("Uspješno ste ažurirali model namještaja");
            success.showAndWait();
            closeStage();
        } else {
            alert.setHeaderText("Greška pri ažuriranju modela");
            alert.showAndWait();
            clearForm();
            return;
        }
    }

    private void clearForm(){
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
        ModelNamjestajaController.setSelectedModel(null);
        Stage stage = (Stage) otkaziButton.getScene().getWindow();
        stage.close();
    }
}