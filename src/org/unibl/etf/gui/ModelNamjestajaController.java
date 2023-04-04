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
import org.unibl.etf.db.dao.ModelNamjestajaDAO;
import org.unibl.etf.db.dto.ModelNamjestajaDTO;
import java.io.File;
import java.math.BigDecimal;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class ModelNamjestajaController implements Initializable {
    private Alert alertError;
    private Alert alertSuccess;

    private static ModelNamjestajaDTO selectedModel;
    public static ModelNamjestajaDTO getSelectedModel() {
        return selectedModel;
    }
    public static void setSelectedModel(ModelNamjestajaDTO selectedModel) { ModelNamjestajaController.selectedModel = selectedModel; }

    private final ObservableList<ModelNamjestajaDTO> dataList = FXCollections.observableArrayList();

    @FXML
    private TableView<ModelNamjestajaDTO> modelNamjestajaTableView;
    @FXML
    private TableColumn<ModelNamjestajaDTO, Integer> idNamjestajaColumn;
    @FXML
    private TableColumn<ModelNamjestajaDTO, String> tipNamjestajaColumn;
    @FXML
    private TableColumn<ModelNamjestajaDTO, String> nazivModelaColumn;
    @FXML
    private TableColumn<ModelNamjestajaDTO, Integer> katalogColumn;
    @FXML
    private TableColumn<ModelNamjestajaDTO, BigDecimal> prodajnaCijenaColumn;
    @FXML
    private TableColumn<ModelNamjestajaDTO, String> bojaColumn;
    @FXML
    private TableColumn<ModelNamjestajaDTO, BigDecimal> visinaColumn;
    @FXML
    private TableColumn<ModelNamjestajaDTO, BigDecimal> sirinaColumn;
    @FXML
    private TableColumn<ModelNamjestajaDTO, BigDecimal> dubinaColumn;
    @FXML
    private Button backButton;
    @FXML
    private ImageView namjestajImageView;
    @FXML
    private ImageView backImageView;
    @FXML
    private Label imeTrgovcaLabel;
    @FXML
    private Label usernameLabel;
    @FXML
    private TextField pretragaPoNazivuModelaTextField;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        namjestajImageView.setImage(new Image(new File("src/img/furnitureModel.png").toURI().toString()));
        backImageView.setImage(new Image(new File("src/img/previous.png").toURI().toString()));

        imeTrgovcaLabel.setText(MenuController.getTrgovac().getIme() + " " + MenuController.getTrgovac().getPrezime());
        usernameLabel.setText(MenuController.getTrgovac().getUsername());

        idNamjestajaColumn.setCellValueFactory(new PropertyValueFactory<ModelNamjestajaDTO, Integer>("idModelaNamjestaja"));
        tipNamjestajaColumn.setCellValueFactory(new PropertyValueFactory<ModelNamjestajaDTO, String>("tipNamjestaja"));
        nazivModelaColumn.setCellValueFactory(new PropertyValueFactory<ModelNamjestajaDTO, String>("nazivModela"));
        katalogColumn.setCellValueFactory(new PropertyValueFactory<ModelNamjestajaDTO, Integer>("idKataloga"));
        prodajnaCijenaColumn.setCellValueFactory(new PropertyValueFactory<ModelNamjestajaDTO, BigDecimal>("prodajnaCijena"));
        bojaColumn.setCellValueFactory(new PropertyValueFactory<ModelNamjestajaDTO, String>("boja"));
        visinaColumn.setCellValueFactory(new PropertyValueFactory<ModelNamjestajaDTO, BigDecimal>("visinaCm"));
        sirinaColumn.setCellValueFactory(new PropertyValueFactory<ModelNamjestajaDTO, BigDecimal>("sirinaCm"));
        dubinaColumn.setCellValueFactory(new PropertyValueFactory<ModelNamjestajaDTO, BigDecimal>("dubinaCm"));

        modelNamjestajaTableView.setItems(FXCollections.observableArrayList(new ModelNamjestajaDAO().dohvatiSveModeleNamjestaja()));

        idNamjestajaColumn.setReorderable(false);
        tipNamjestajaColumn.setReorderable(false);
        nazivModelaColumn.setReorderable(false);
        katalogColumn.setReorderable(false);
        prodajnaCijenaColumn.setReorderable(false);
        bojaColumn.setReorderable(false);
        visinaColumn.setReorderable(false);
        sirinaColumn.setReorderable(false);
        dubinaColumn.setReorderable(false);

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
            Stage menuStage = new Stage();
            menuStage.setScene(new Scene(root, 800, 600));
            menuStage.setResizable(false);
            menuStage.setTitle("McMilan");
            menuStage.getIcons().add(new Image(McMilan.class.getResourceAsStream("/img/icon.png")));
            menuStage.show();

            Stage stage = (Stage) backButton.getScene().getWindow();
            stage.close();

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
            Parent root = FXMLLoader.load(getClass().getResource("/org/unibl/etf/gui/dodajNamjestaj.fxml"));
            Stage newStage = new Stage();
            newStage.setScene(new Scene(root, 300, 420));
            newStage.setResizable(false);
            newStage.setTitle("McMilan");
            newStage.getIcons().add(new Image(McMilan.class.getResourceAsStream("/img/icon.png")));
            newStage.setOnHidden(p -> refreshTableView());
            newStage.setOnCloseRequest(p -> refreshTableView());
            newStage.initModality(Modality.APPLICATION_MODAL);
            newStage.show();
        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }

    @FXML
    void obrisiButtonOnAction(ActionEvent event) {
        if(selectedModel != null) {
            ButtonType da = new ButtonType("Da", ButtonBar.ButtonData.YES);
            ButtonType ne = new ButtonType("Ne", ButtonBar.ButtonData.NO);
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION,"Da li ste sigurni da želite obrisati unos?", da, ne);
            alert.setTitle("McMilan");
            ((Stage)alert.getDialogPane().getScene().getWindow()).getIcons().add(new Image("img/icon.png"));
            alert.setHeaderText("Brisanje unosa");

            Optional<ButtonType> action = alert.showAndWait();
            if(action.get() == da) {
                if(new ModelNamjestajaDAO().obrisiModelNamjestaja(selectedModel.getIdModelaNamjestaja()) == true) {
                    alertSuccess.setHeaderText("Uspješno obrisan red u tabeli");
                    alertSuccess.showAndWait();
                    selectedModel = null;
                }
            }
        } else {
            alertError.setHeaderText("Morate prvo selektovati neki red u tabeli");
            alertError.showAndWait();
        }
        refreshTableView();
        selectedModel = null;
    }
    @FXML
    void izmijeniButtonOnAction(ActionEvent event) {
        if(selectedModel != null) {
            try {
                Parent root = FXMLLoader.load(getClass().getResource("/org/unibl/etf/gui/izmijeniNamjestaj.fxml"));
                Stage newStage = new Stage();
                newStage.setScene(new Scene(root, 300, 420));
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
        List<ModelNamjestajaDTO> result = new ModelNamjestajaDAO().dohvatiPoNazivuModela(pretragaPoNazivuModelaTextField.getText());
        dataList.clear();
        dataList.addAll(result);
        modelNamjestajaTableView.setItems(dataList);
    }

    @FXML
    void mouseClickedOnAction(MouseEvent event) {
        ModelNamjestajaDTO model = modelNamjestajaTableView.getSelectionModel().getSelectedItem();
        selectedModel = model;
        if (event.getClickCount() == 2) {
            izmijeniButtonOnAction(null);
        }
    }

    @FXML
    void backgroundClickedOnAction(MouseEvent event) {
        selectedModel = null;
    }

    private void refreshTableView() {
        List <ModelNamjestajaDTO> result = new ModelNamjestajaDAO().dohvatiSveModeleNamjestaja();
        dataList.clear();
        dataList.addAll(result);
        modelNamjestajaTableView.setItems(dataList);
        modelNamjestajaTableView.refresh();
    }
}