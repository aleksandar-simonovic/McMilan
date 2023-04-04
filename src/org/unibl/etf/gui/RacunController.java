package org.unibl.etf.gui;

import javafx.fxml.FXML;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.unibl.etf.app.McMilan;
import org.unibl.etf.db.dao.FiskalniRacunDAO;
import org.unibl.etf.db.dao.FiskalniRacunStavkaDAO;
import org.unibl.etf.db.dao.ModelNamjestajaDAO;
import org.unibl.etf.db.dao.SalonNamjestajaDAO;
import org.unibl.etf.db.dto.FiskalniRacunDTO;
import org.unibl.etf.db.dto.FiskalniRacunStavkaDTO;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.Date;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class RacunController implements Initializable {
    private Alert alertError;
    private Alert alertSuccess;

    private static FiskalniRacunDTO selection;
    public static FiskalniRacunDTO getSelection() { return selection; }
    public static void setSelection(FiskalniRacunDTO selection) { RacunController.selection = selection; }

    private final ObservableList<FiskalniRacunDTO> dataList = FXCollections.observableArrayList();

    @FXML
    private Button backButton;
    @FXML
    private ImageView backImageView;
    @FXML
    private DatePicker datePicker;
    @FXML
    private Label imeTrgovcaLabel;
    @FXML
    private TableColumn<FiskalniRacunDTO, Integer> idRacunaColumn;
    @FXML
    private TableColumn<FiskalniRacunDTO, Date> datumColumn;
    @FXML
    private TableColumn<FiskalniRacunDTO, BigDecimal> iznosColumn;
    @FXML
    private TableColumn<FiskalniRacunDTO, String> racunKreiraoColumn;
    @FXML
    private TableColumn<FiskalniRacunDTO, String> izdatColumn;
    @FXML
    private TableView<FiskalniRacunDTO> racunTableView;
    @FXML
    private ImageView racunImageView;
    @FXML
    private TextField pretragaPoIznosu;
    @FXML
    private Label usernameLabel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        racunImageView.setImage(new Image(new File("src/img/trade.png").toURI().toString()));
        backImageView.setImage(new Image(new File("src/img/previous.png").toURI().toString()));

        imeTrgovcaLabel.setText(MenuController.getTrgovac().getIme() + " " + MenuController.getTrgovac().getPrezime());
        usernameLabel.setText(MenuController.getTrgovac().getUsername());

        idRacunaColumn.setCellValueFactory(new PropertyValueFactory<FiskalniRacunDTO, Integer>("idRacuna"));
        datumColumn.setCellValueFactory(new PropertyValueFactory<FiskalniRacunDTO, Date>("datumRacuna"));
        iznosColumn.setCellValueFactory(new PropertyValueFactory<FiskalniRacunDTO, BigDecimal>("ukupanIznos"));
        racunKreiraoColumn.setCellValueFactory(new PropertyValueFactory<FiskalniRacunDTO, String>("prodavac"));
        izdatColumn.setCellValueFactory(new PropertyValueFactory<FiskalniRacunDTO, String>("izdatString"));

        racunTableView.setItems(FXCollections.observableArrayList(new FiskalniRacunDAO().racuniIzSalonaNamjestaja(MenuController.getTrgovac().getIdSalonaNamjestaja())));

        idRacunaColumn.setReorderable(false);
        datumColumn.setReorderable(false);
        iznosColumn.setReorderable(false);
        racunKreiraoColumn.setReorderable(false);
        izdatColumn.setReorderable(false);

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

            Stage oldStage = (Stage) backButton.getScene().getWindow();
            oldStage.close();
        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }

    @FXML
    void pretragaPoDatumuOnAction(ActionEvent event) {
        List<FiskalniRacunDTO> result = new FiskalniRacunDAO().dohvatiPoDatumu(MenuController.getTrgovac().getIdSalonaNamjestaja(), datePicker.getValue());
        dataList.clear();
        dataList.addAll(result);
        racunTableView.setItems(dataList);
        racunTableView.refresh();
    }

    @FXML
    void izdajRacunButtonOnAction(ActionEvent event) {
        if(selection != null) {
            if(!selection.getIzdat()) {
                if(selection.getJmbg().equals(MenuController.getTrgovac().getJmbg())) {
                    if(selection.getUkupanIznos().doubleValue() != 0) {
                        ButtonType da = new ButtonType("Da", ButtonBar.ButtonData.YES);
                        ButtonType ne = new ButtonType("Ne", ButtonBar.ButtonData.NO);
                        Alert alert = new Alert(Alert.AlertType.CONFIRMATION,"Da li ste sigurni da želite izdati fiskalni račun?", da, ne);
                        ((Stage)alert.getDialogPane().getScene().getWindow()).getIcons().add(new Image("img/icon.png"));
                        alert.setHeaderText("Izdavanje računa");

                        Optional<ButtonType> action = alert.showAndWait();
                        if(action.get() == da) {
                            if(new FiskalniRacunDAO().izdajFiskalniRacun(selection.getIdRacuna()) == true) {
                                alertSuccess.setHeaderText("Uspješno ste izdali fiskalni račun");
                                alertSuccess.showAndWait();
                                selection = null;
                            }
                        }
                    } else {
                        alertError.setHeaderText("Ne možete izdati račun na kojem nema stavki");
                        alertError.showAndWait();
                    }
                } else {
                    alertError.setHeaderText("Ne možete izdati račun od drugog trgovca");
                    alertError.showAndWait();
                }
            } else {
                alertError.setHeaderText("Ne možete izdati račun koji je već izdat");
                alertError.showAndWait();
            }
        } else {
            alertError.setHeaderText("Morate prvo selektovati neki red u tabeli");
            alertError.showAndWait();
        }
        refreshTableView();
        selection = null;
    }

    @FXML
    void izmijeniRacunButtonOnAction(ActionEvent event) {
        if(selection != null) {
            if(!selection.getIzdat()) {
                try {
                    Parent root = FXMLLoader.load(getClass().getResource("/org/unibl/etf/gui/izmjeniRacun.fxml"));
                    Stage newStage = new Stage();
                    newStage.setScene(new Scene(root, 550, 500));
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
                alertError.setHeaderText("Ne možete mijenjati račun koji je već izdat");
                alertError.showAndWait();
            }
        } else {
            alertError.setHeaderText("Morate prvo selektovati neki red u tabeli");
            alertError.showAndWait();
        }
    }

    @FXML
    void noviRacunButtonOnAction(ActionEvent event) {
        new FiskalniRacunDAO().noviFiskalniRacun(MenuController.getTrgovac().getJmbg(),MenuController.getTrgovac().getIdSalonaNamjestaja());
        refreshTableView();
    }

    @FXML
    void obrisiRacunButtonOnAction(ActionEvent event) {
        if(selection != null) {
            if(selection.getJmbg().equals(MenuController.getTrgovac().getJmbg())) {
                ButtonType da = new ButtonType("Da", ButtonBar.ButtonData.YES);
                ButtonType ne = new ButtonType("Ne", ButtonBar.ButtonData.NO);
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION,"Da li ste sigurni da želite obrisati unos?", da, ne);
                ((Stage)alert.getDialogPane().getScene().getWindow()).getIcons().add(new Image("img/icon.png"));
                alert.setHeaderText("Brisanje unosa");
                alert.setTitle("McMilan");

                Optional<ButtonType> action = alert.showAndWait();
                if(action.get() == da) {
                    if(new FiskalniRacunDAO().obrisiFiskalniRacun(selection.getIdRacuna())) {
                        alertSuccess.setHeaderText("Uspješno obrisan red u tabeli");
                        alertSuccess.showAndWait();
                        selection = null;
                    }
                }
            } else {
                alertError.setHeaderText("Ne možete obrisati račun od drugog trgovca");
                alertError.showAndWait();
            }
        } else {
            alertError.setHeaderText("Morate prvo selektovati neki red u tabeli");
            alertError.showAndWait();
        }
        refreshTableView();
        selection = null;
    }

    @FXML
    void pretragaPoIznosuOnAction(ActionEvent event) {
        List<FiskalniRacunDTO> result = new FiskalniRacunDAO().dohvatiPoIznosu(pretragaPoIznosu.getText(), MenuController.getTrgovac().getIdSalonaNamjestaja());
        dataList.clear();
        dataList.addAll(result);
        racunTableView.setItems(dataList);
        racunTableView.refresh();
    }

    @FXML
    void prikaziSveButtonOnAction(ActionEvent event) {
        refreshTableView();
    }

    @FXML
    void racunDetaljnoButtonOnAction(ActionEvent event) {
        if(selection != null) {
            if(selection.getIzdat()) {
                kreirajRacun();
            } else {
                alertError.setHeaderText("Morate prvo izdati račun");
                alertError.showAndWait();
                return;
            }
        } else {
            alertError.setHeaderText("Morate prvo selektovati neki red u tabeli");
            alertError.showAndWait();
            return;
        }
    }

    @FXML
    void mouseClickedOnAction(MouseEvent event) {
        FiskalniRacunDTO clicked = racunTableView.getSelectionModel().getSelectedItem();
        selection = clicked;
        if (event.getClickCount() == 2) {
            System.out.println("Pritisnuli ste 2 puta");
            izmijeniRacunButtonOnAction(null);
        }
    }

    private void refreshTableView() {
        List<FiskalniRacunDTO> result = new FiskalniRacunDAO().racuniIzSalonaNamjestaja(MenuController.getTrgovac().getIdSalonaNamjestaja());
        dataList.clear();
        dataList.addAll(result);
        racunTableView.setItems(dataList);
        racunTableView.refresh();
    }

    @FXML
    void backgroundClickedOnAction(MouseEvent event) {
        datePicker.getEditor().clear();
        selection = null;
    }

    private void kreirajRacun() {
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
        fileChooser.getExtensionFilters().add(extFilter);
        File file = fileChooser.showSaveDialog((Stage) backButton.getScene().getWindow());
        String racunText = racunAsText();
        if(file != null) {
            saveTextToFile(racunText, file);
        } else {
            alertError.setHeaderText("Račun nije sačuvan u fajl");
            alertError.showAndWait();
        }
    }

    private void saveTextToFile(String content, File file) {
        try {
            PrintWriter writer;
            writer = new PrintWriter(file);
            writer.println(content);
            writer.close();
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }

    private String racunAsText() {
        StringBuilder str = new StringBuilder(1000);
        str.append("POSLOVNICA: ");
        str.append(MenuController.getTrgovac().getNazivSalona());
        str.append(System.getProperty("line.separator"));
        str.append("JIB: ");
        str.append(new SalonNamjestajaDAO().dohvatiSalonPoIdentifikatoru(MenuController.getTrgovac().getIdSalonaNamjestaja()).getJib());
        str.append(System.getProperty("line.separator"));
        str.append("TRGOVAC: ");
        str.append(MenuController.getTrgovac().getIme() + " " + MenuController.getTrgovac().getPrezime());
        str.append(System.getProperty("line.separator"));
        str.append(System.getProperty("line.separator"));
        str.append("DATUM: ");
        str.append(selection.getDatumRacuna());
        str.append(System.getProperty("line.separator"));
        str.append("==============================================");
        str.append(System.getProperty("line.separator"));
        str.append("                   RAČUN");
        str.append(System.getProperty("line.separator"));
        str.append("==============================================");
        str.append(System.getProperty("line.separator"));
        str.append("NAMJEŠTAJ\tKOLIČINA\t\tCIJENA");
        str.append(System.getProperty("line.separator"));
        str.append("==============================================");
        str.append(System.getProperty("line.separator"));
        List<FiskalniRacunStavkaDTO> stavke = new FiskalniRacunStavkaDAO().dohvatiStavkeZaRacunPoIdentifikatoru(selection.getIdRacuna());

        for(FiskalniRacunStavkaDTO x : stavke) {
            str.append(String.format("%-16s%8d%22f", new ModelNamjestajaDAO().dohvatiModelNamjestajaPoIdentifikatoru(x.getIdModelaNamjestaja()).getNazivModela(),x.getKolicina(),x.getCijena()));
            str.append(System.getProperty("line.separator"));
            str.append("----------------------------------------------");
            str.append(System.getProperty("line.separator"));
        }
        str.append(String.format("%46s","Total: " + selection.getUkupanIznos()));

        str.append(System.getProperty("line.separator"));
        str.append("==============================================");
        str.append(System.getProperty("line.separator"));
        str.append("                   HVALA VAM");
        str.append(System.getProperty("line.separator"));
        str.append("==============================================");
        return str.toString();
    }
}