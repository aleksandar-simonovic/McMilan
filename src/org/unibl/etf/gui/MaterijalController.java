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
import org.unibl.etf.db.dao.MaterijalDAO;
import org.unibl.etf.db.dto.MaterijalDTO;

import java.io.File;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class MaterijalController implements Initializable {
    private static MaterijalDTO selectedMaterial;
    public static MaterijalDTO getSelectedMaterial() { return selectedMaterial; }
    public static void setSelectedMaterial(MaterijalDTO selectedMaterial) { MaterijalController.selectedMaterial = selectedMaterial; }

    private final ObservableList<MaterijalDTO> dataList = FXCollections.observableArrayList();

    private Alert alertError;
    private Alert alertSuccess;

    @FXML
    private TableView<MaterijalDTO> materijalTableView;
    @FXML
    private TableColumn<MaterijalDTO, Integer> idMaterijalaColumn;
    @FXML
    private TableColumn<MaterijalDTO, String> tipMaterijalaColumn;
    @FXML
    private TableColumn<MaterijalDTO, String> nazivMaterijalaColumn;
    @FXML
    private Button backButton;
    @FXML
    private ImageView materijalImageView;
    @FXML
    private ImageView backImageView;
    @FXML
    private Label imeTrgovcaLabel;
    @FXML
    private Label usernameLabel;
    @FXML
    private TextField pretragaPoNazivuMaterijalaTextField;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        materijalImageView.setImage(new Image(new File("src/img/wood.png").toURI().toString()));
        backImageView.setImage(new Image(new File("src/img/previous.png").toURI().toString()));
        imeTrgovcaLabel.setText(MenuController.getTrgovac().getIme() + " " + MenuController.getTrgovac().getPrezime());
        usernameLabel.setText(MenuController.getTrgovac().getUsername());

        idMaterijalaColumn.setCellValueFactory(new PropertyValueFactory<MaterijalDTO, Integer>("idMaterijala"));
        tipMaterijalaColumn.setCellValueFactory(new PropertyValueFactory<MaterijalDTO, String>("tipMaterijala"));
        nazivMaterijalaColumn.setCellValueFactory(new PropertyValueFactory<MaterijalDTO, String>("nazivMaterijala"));

        materijalTableView.setItems(FXCollections.observableArrayList(new MaterijalDAO().dohvatiSveMaterijale()));

        idMaterijalaColumn.setReorderable(false);
        tipMaterijalaColumn.setReorderable(false);
        nazivMaterijalaColumn.setReorderable(false);

        alertError = new Alert(Alert.AlertType.ERROR);
        alertError.setTitle("McMilan");
        alertSuccess = new Alert(Alert.AlertType.INFORMATION);
        alertSuccess.getDialogPane().setGraphic(new ImageView("/img/success.png"));
        alertSuccess.setTitle("McMilan");
        ((Stage)alertError.getDialogPane().getScene().getWindow()).getIcons().add(new Image("img/icon.png"));
        ((Stage)alertSuccess.getDialogPane().getScene().getWindow()).getIcons().add(new Image("img/icon.png"));
    }

    @FXML
    void backButtonOnAction(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/org/unibl/etf/gui/menu.fxml"));
            Stage newStage = new Stage();
            newStage.setScene(new Scene(root, 800, 600));
            newStage.setResizable(false);
            newStage.setTitle("McMilan");
            newStage.getIcons().add(new Image(McMilan.class.getResourceAsStream("/img/icon.png")));
            newStage.show();

            Stage currentStage = (Stage) backButton.getScene().getWindow();
            currentStage.close();

        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }
    @FXML
    void prikaziSveButtonOnAction(ActionEvent event) {
        refreshTableView();
    }

    @FXML
    void dodajButtonOnAction(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/org/unibl/etf/gui/dodajMaterijal.fxml"));
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
        if(selectedMaterial != null) {
            ButtonType da = new ButtonType("Da", ButtonBar.ButtonData.YES);
            ButtonType ne = new ButtonType("Ne", ButtonBar.ButtonData.NO);
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION,"Da li ste sigurni da zelite obrisati unos?", da, ne);
            ((Stage)alert.getDialogPane().getScene().getWindow()).getIcons().add(new Image("img/icon.png"));
            alert.setHeaderText("Brisanje unosa");
            alert.setTitle("McMilan");

            Optional<ButtonType> action = alert.showAndWait();
            if(action.get() == da) {
                if(new MaterijalDAO().obrisiMaterijal(selectedMaterial.getIdMaterijala()) == true) {
                    alertSuccess.setHeaderText("Uspješno obrisan red u tabeli");
                    alertSuccess.showAndWait();
                    selectedMaterial = null;
                }
            }
        } else {
            alertError.setHeaderText("Morate prvo selektovati neki red u tabeli");
            alertError.showAndWait();
        }
        refreshTableView();
        selectedMaterial = null;
    }

    @FXML
    void izmijeniButtonOnAction(ActionEvent event) {
        if(selectedMaterial != null) {
            try {
                Parent root = FXMLLoader.load(getClass().getResource("/org/unibl/etf/gui/izmijeniMaterijal.fxml"));
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
        } else {
            alertError.setHeaderText("Morate prvo selektovati neki red u tabeli");
            alertError.showAndWait();
        }
    }

    @FXML
    void pretragaPoNazivuMaterijalaOnAction(ActionEvent event) {
        List<MaterijalDTO> result = new MaterijalDAO().dohvatiPoNazivuMaterijala(pretragaPoNazivuMaterijalaTextField.getText());
        dataList.clear();
        dataList.addAll(result);
        materijalTableView.setItems(dataList);
        materijalTableView.refresh();
    }

    @FXML
    void mouseClickedOnAction(MouseEvent event) {
        MaterijalDTO mouseSelection = materijalTableView.getSelectionModel().getSelectedItem();
        selectedMaterial = mouseSelection;
        if (event.getClickCount() == 2) {
            izmijeniButtonOnAction(null);
        }
    }

    @FXML
    void backgroundClickedOnAction(MouseEvent event) {
        selectedMaterial = null;
    }

    private void refreshTableView() {
        List <MaterijalDTO> result = new MaterijalDAO().dohvatiSveMaterijale();
        dataList.clear();
        dataList.addAll(result);
        materijalTableView.setItems(dataList);
        materijalTableView.refresh();
    }
}