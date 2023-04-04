package org.unibl.etf.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import org.unibl.etf.db.dao.MaterijalDAO;
import org.unibl.etf.db.dto.MaterijalDTO;


public class DodajMaterijalController {
    @FXML
    private TextField idMaterijalaTextField;
    @FXML
    private TextField nazivMaterijalaTextField;
    @FXML
    private TextField tipMaterijalaTextField;
    @FXML
    private Button otkaziButton;

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

        String nazivMaterijala = nazivMaterijalaTextField.getText().trim();
        if(nazivMaterijala.isEmpty()) {
            alert.setHeaderText("Polje naziv materijala ne smije biti prazno!");
            alert.setContentText("Provjerite unos pa pokušajte ponovo");
            alert.showAndWait();
            return;
        }

        if(new MaterijalDAO().dohvatiMaterijalPoNazivuMaterijala(nazivMaterijala) != null) {
            alert.setHeaderText("Materijal sa tim nazivom već postoji!");
            alert.setContentText("Provjerite unos pa pokušajte ponovo");
            alert.showAndWait();
            return;
        }

        String tipMaterijala = tipMaterijalaTextField.getText().trim();
        if(tipMaterijala.isEmpty()) tipMaterijala = null;

        Integer idMaterijala;
        try {
            idMaterijala = Integer.parseInt(idMaterijalaTextField.getText().trim());
            if(new MaterijalDAO().dohvatiMaterijalPoIdentifikatoru(idMaterijala) != null) {
                alert.setHeaderText("Materijal sa tim identifikatorom već postoji!");
                alert.setContentText("Provjerite unos pa pokušajte ponovo");
                alert.showAndWait();
                return;
            }
        } catch (NumberFormatException ex) {
            idMaterijala = null;
        }

        MaterijalDTO userInput = new MaterijalDTO(
                idMaterijala,
                tipMaterijala,
                nazivMaterijala);

        if(new MaterijalDAO().dodajMaterijal(userInput)) {
            success.setHeaderText("Uspješno ste dodali novi materijal");
            success.showAndWait();
            closeStage();
        } else {
            alert.setHeaderText("Greška pri dodavanju materijala");
            alert.showAndWait();
            clearForm();
            return;
        }
    }

    private void clearForm(){
        idMaterijalaTextField.clear();
        nazivMaterijalaTextField.clear();
        tipMaterijalaTextField.clear();
    }

    private void closeStage(){
        Stage stage = (Stage) otkaziButton.getScene().getWindow();
        stage.close();
    }
}