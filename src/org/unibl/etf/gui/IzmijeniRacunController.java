package org.unibl.etf.gui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.unibl.etf.app.McMilan;
import org.unibl.etf.db.dao.FiskalniRacunDAO;
import org.unibl.etf.db.dao.FiskalniRacunStavkaDAO;
import org.unibl.etf.db.dao.ModelNamjestajaDAO;
import org.unibl.etf.db.dto.FiskalniRacunStavkaDTO;
import org.unibl.etf.db.dto.ModelNamjestajaDTO;
import org.unibl.etf.gui.RacunStavkaDisplay;
import java.math.BigDecimal;
import java.net.URL;
import java.util.*;

public class IzmijeniRacunController implements Initializable {
    Alert alertError;
    Alert alertSuccess;

    private static RacunStavkaDisplay selection;
    public static RacunStavkaDisplay getSelection() { return selection; }
    public static void setSelection(RacunStavkaDisplay selection) { IzmijeniRacunController.selection = selection; }

    private final ObservableList<RacunStavkaDisplay> dataList = FXCollections.observableArrayList();

    @FXML
    private TableView<RacunStavkaDisplay> izmijeniRacunTableView;
    @FXML
    private TableColumn<RacunStavkaDisplay, BigDecimal> cijenaColumn;
    @FXML
    private TableColumn<RacunStavkaDisplay, Integer> kolicinaColumn;
    @FXML
    private TableColumn<RacunStavkaDisplay, String> nazivModelaColumn;
    @FXML
    private Button nazadButton;
    @FXML
    private TextField ukupanIznosLabel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ukupanIznosLabel.setText(RacunController.getSelection().getUkupanIznos().toString());

        cijenaColumn.setCellValueFactory(new PropertyValueFactory<RacunStavkaDisplay, BigDecimal>("cijena"));
        kolicinaColumn.setCellValueFactory(new PropertyValueFactory<RacunStavkaDisplay, Integer>("kolicina"));
        nazivModelaColumn.setCellValueFactory(new PropertyValueFactory<RacunStavkaDisplay, String>("nazivModela"));
        izmijeniRacunTableView.setItems(FXCollections.observableArrayList(RacunStavkaDisplay.dohvatiStavkeZaRacunPoIdentifikatoru(RacunController.getSelection().getIdRacuna())));

        cijenaColumn.setReorderable(false);
        kolicinaColumn.setReorderable(false);
        nazivModelaColumn.setReorderable(false);

        alertError = new Alert(Alert.AlertType.ERROR);
        alertError.setTitle("McMilan");
        alertSuccess = new Alert(Alert.AlertType.INFORMATION);
        alertSuccess.setTitle("McMilan");
        alertSuccess.getDialogPane().setGraphic(new ImageView("/img/success.png"));
        ((Stage)alertError.getDialogPane().getScene().getWindow()).getIcons().add(new Image("img/icon.png"));
        ((Stage)alertSuccess.getDialogPane().getScene().getWindow()).getIcons().add(new Image("img/icon.png"));
    }

    @FXML
    void backgroundClickedOnAction(MouseEvent event) {
        selection = null;
    }

    @FXML
    void dodajButtonOnAction(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/org/unibl/etf/gui/dodajStavkuNaRacun.fxml"));
            Stage newStage = new Stage();
            newStage.setScene(new Scene(root, 300, 200));
            newStage.setResizable(false);
            newStage.setTitle("McMilan");
            newStage.getIcons().add(new Image(McMilan.class.getResourceAsStream("/img/icon.png")));
            newStage.setOnHidden(p -> refreshTableView());
            newStage.initModality(Modality.APPLICATION_MODAL);
            newStage.show();
        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }

    @FXML
    void izmijeniButtonOnAction(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/org/unibl/etf/gui/izmijeniStavkuNaRacunu.fxml"));
            Stage newStage = new Stage();
            newStage.setScene(new Scene(root, 300, 120));
            newStage.setResizable(false);
            newStage.setTitle("McMilan");
            newStage.getIcons().add(new Image(McMilan.class.getResourceAsStream("/img/icon.png")));
            newStage.setOnHidden(p -> refreshTableView());
            newStage.initModality(Modality.APPLICATION_MODAL);
            newStage.show();
        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }

    @FXML
    void mouseClickedOnAction(MouseEvent event) {
        RacunStavkaDisplay clicked = izmijeniRacunTableView.getSelectionModel().getSelectedItem();
        selection = clicked;
        if (event.getClickCount() == 2) {
            System.out.println("Pritisnuli ste 2 puta");
            izmijeniButtonOnAction(null);
        }
    }

    @FXML
    void obrisiButtonOnAction(ActionEvent event) {
        if(selection != null) {
            ButtonType da = new ButtonType("Da", ButtonBar.ButtonData.YES);
            ButtonType ne = new ButtonType("Ne", ButtonBar.ButtonData.NO);
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION,"Da li ste sigurni da zelite obrisati unos?", da, ne);
            ((Stage)alert.getDialogPane().getScene().getWindow()).getIcons().add(new Image("img/icon.png"));
            alert.setHeaderText("Brisanje unosa");

            Optional<ButtonType> action = alert.showAndWait();
            if(action.get() == da) {
                if(new FiskalniRacunStavkaDAO().obrisiStavkuSaRacuna(RacunController.getSelection().getIdRacuna(),
                        RacunStavkaDisplay.dohvatiIdModelaPoNazivuModela(selection.getNazivModela())) == true) {
                    alertSuccess.setHeaderText("Uspješno obrisan red u tabeli");
                    alertSuccess.showAndWait();
                    selection = null;
                }
            }
        } else {
            alertError.setHeaderText("Morate prvo selektovati neki red u tabeli");
            alertError.showAndWait();
        }
        refreshTableView();
        selection = null;
    }

    @FXML
    void nazadButtonOnAction(ActionEvent event) {
        closeStage();
    }

    private void closeStage(){
        RacunController.setSelection(null);
        Stage stage = (Stage) nazadButton.getScene().getWindow();
        stage.close();
    }

    private void refreshTableView() {
        List<RacunStavkaDisplay> result = RacunStavkaDisplay.dohvatiStavkeZaRacunPoIdentifikatoru(RacunController.getSelection().getIdRacuna());
        dataList.clear();
        dataList.addAll(result);
        izmijeniRacunTableView.setItems(dataList);
        izmijeniRacunTableView.refresh();
        ukupanIznosLabel.setText(new FiskalniRacunDAO().dohvatiRacunPoIdentifikatoru(RacunController.getSelection().getIdRacuna()).getUkupanIznos().toString());
    }
}