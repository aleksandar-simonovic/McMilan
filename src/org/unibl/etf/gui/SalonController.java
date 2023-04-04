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
import org.unibl.etf.db.dao.ModelNamjestajaSeCuvaUSalonuNamjestajaDAO;
import org.unibl.etf.db.dao.SalonStatistikaDAO;
import org.unibl.etf.db.dto.SalonStatistikaDTO;

import java.io.File;
import java.math.BigDecimal;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class SalonController implements Initializable {
    private Alert alertError;
    private Alert alertSuccess;

    private static SalonStatistikaDTO selection;
    public static SalonStatistikaDTO getSelection() { return selection; }
    public static void setSelection(SalonStatistikaDTO selection) { SalonController.selection = selection; }

    private final ObservableList<SalonStatistikaDTO> dataList = FXCollections.observableArrayList();

    @FXML
    private TableView<SalonStatistikaDTO> stanjeSalonaTableView;
    @FXML
    private TableColumn<SalonStatistikaDTO, Integer> idNamjestajaColumn;
    @FXML
    private TableColumn<SalonStatistikaDTO, String> tipNamjestajaColumn;
    @FXML
    private TableColumn<SalonStatistikaDTO, String> nazivModelaColumn;
    @FXML
    private TableColumn<SalonStatistikaDTO, String> katalogColumn;
    @FXML
    private TableColumn<SalonStatistikaDTO, BigDecimal> prodajnaCijenaColumn;
    @FXML
    private TableColumn<SalonStatistikaDTO, Integer> kolicinaColumn;

    @FXML
    private Button backButton;
    @FXML
    private ImageView salonImageView;
    @FXML
    private ImageView backImageView;
    @FXML
    private Label imeTrgovcaLabel;
    @FXML
    private Label usernameLabel;
    @FXML
    private TextField pretragaPoNazivuModelaTextField;
    @FXML
    private MenuButton dodajDropDown;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        salonImageView.setImage(new Image(new File("src/img/warehouse.png").toURI().toString()));
        backImageView.setImage(new Image(new File("src/img/previous.png").toURI().toString()));

        imeTrgovcaLabel.setText(MenuController.getTrgovac().getIme() + " " + MenuController.getTrgovac().getPrezime());
        usernameLabel.setText(MenuController.getTrgovac().getUsername());

        idNamjestajaColumn.setCellValueFactory(new PropertyValueFactory<SalonStatistikaDTO, Integer>("idModelaNamjestaja"));
        tipNamjestajaColumn.setCellValueFactory(new PropertyValueFactory<SalonStatistikaDTO, String>("tipNamjestaja"));
        nazivModelaColumn.setCellValueFactory(new PropertyValueFactory<SalonStatistikaDTO, String>("nazivModela"));
        katalogColumn.setCellValueFactory(new PropertyValueFactory<SalonStatistikaDTO, String>("nazivKataloga"));
        prodajnaCijenaColumn.setCellValueFactory(new PropertyValueFactory<SalonStatistikaDTO, BigDecimal>("prodajnaCijena"));
        kolicinaColumn.setCellValueFactory(new PropertyValueFactory<SalonStatistikaDTO, Integer>("kolicina"));

        idNamjestajaColumn.setReorderable(false);
        tipNamjestajaColumn.setReorderable(false);
        nazivModelaColumn.setReorderable(false);
        katalogColumn.setReorderable(false);
        prodajnaCijenaColumn.setReorderable(false);
        kolicinaColumn.setReorderable(false);

        stanjeSalonaTableView.setItems(FXCollections.observableArrayList(new SalonStatistikaDAO().dohvatiStatistikuZaSalon(MenuController.getTrgovac().getIdSalonaNamjestaja())));

        alertError = new Alert(Alert.AlertType.ERROR);
        alertError.setTitle("McMilan");
        alertSuccess = new Alert(Alert.AlertType.INFORMATION);
        alertSuccess.setTitle("McMilan");
    }
    @FXML
    void backButtonOnAction(ActionEvent event) {
        changeStage("/org/unibl/etf/gui/menu.fxml", 800, 600);
    }

    @FXML
    void prikaziSveButtonOnAction(ActionEvent event) {
        refreshTableView();
    }

    @FXML
    void dodajButtonOnAction(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/org/unibl/etf/gui/dodajSalon.fxml"));
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
    void obrisiButtonOnAction(ActionEvent event) {
        if (selection != null) {
            ButtonType da = new ButtonType("Da", ButtonBar.ButtonData.YES);
            ButtonType ne = new ButtonType("Ne", ButtonBar.ButtonData.NO);
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Da li ste sigurni da zelite obrisati unos?", da, ne);
            ((Stage) alert.getDialogPane().getScene().getWindow()).getIcons().add(new Image("img/icon.png"));
            alert.setHeaderText("Brisanje unosa");

            Optional<ButtonType> action = alert.showAndWait();
            if (action.get() == da) {
                if (new ModelNamjestajaSeCuvaUSalonuNamjestajaDAO().obrisi(selection.getIdModelaNamjestaja(), MenuController.getTrgovac().getIdSalonaNamjestaja()) == true) {
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
    void izmijeniButtonOnAction(ActionEvent event) {
        if (selection != null) {
            try {
                Parent root = FXMLLoader.load(getClass().getResource("/org/unibl/etf/gui/izmijeniSalon.fxml"));
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
        } else {
            alertError.setHeaderText("Morate prvo selektovati neki red u tabeli");
            alertError.showAndWait();
        }
    }

    @FXML
    void pretragaPoNazivuModelaOnAction(ActionEvent event) {
        List<SalonStatistikaDTO> result = new SalonStatistikaDAO().dohvatiStatistikuZaSalonPoNazivuModela(MenuController.getTrgovac().getIdSalonaNamjestaja(), pretragaPoNazivuModelaTextField.getText());
        dataList.clear();
        dataList.addAll(result);
        stanjeSalonaTableView.setItems(dataList);
    }

    @FXML
    void mouseClickedOnAction(MouseEvent event) {
        SalonStatistikaDTO model = stanjeSalonaTableView.getSelectionModel().getSelectedItem();
        selection = model;
        if (event.getClickCount() == 2) {
            izmijeniButtonOnAction(null);
        }
    }

    @FXML
    void backgroundClickedOnAction(MouseEvent event) {
        selection = null;
    }

    private Stage changeStage(String sceneFxml, int dim1, int dim2) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource(sceneFxml));
            Stage newStage = new Stage();
            newStage.setScene(new Scene(root, dim1, dim2));
            newStage.setResizable(false);
            newStage.setTitle("McMilan");
            newStage.getIcons().add(new Image(McMilan.class.getResourceAsStream("/img/icon.png")));
            newStage.show();

            Stage oldStage = (Stage) backButton.getScene().getWindow();
            oldStage.close();
            return newStage;
        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
        return null;
    }

    private void refreshTableView() {
        List <SalonStatistikaDTO> result = new SalonStatistikaDAO().dohvatiStatistikuZaSalon(MenuController.getTrgovac().getIdSalonaNamjestaja());
        dataList.clear();
        dataList.addAll(result);
        stanjeSalonaTableView.setItems(dataList);
        stanjeSalonaTableView.refresh();
    }
}