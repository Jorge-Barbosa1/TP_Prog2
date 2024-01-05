package com.example.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.CookiePolicy;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class ControllerCreateLocation implements Initializable {
    @FXML
    Button gobackButton;
    @FXML
    Button createLocationButton;
    @FXML
    TextField txtfieldCity;
    @FXML
    TextField txtfieldStreet;
    @FXML
    TextField txtfieldPostCode;
    @FXML
    ChoiceBox<String> choiceboxTypeLocation;

    private final String[] typeLocations= {"RECOLHA", "ENTREGA"};
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        choiceboxTypeLocation.getItems().addAll(typeLocations);
        choiceboxTypeLocation.setOnAction(this::getTypeLocations);//O "::" é um "method reference operator"
        // basicamente faz uma referencia para o getTiposUtilizador e linkar para a choiseBox
    }

    public void getTypeLocations(ActionEvent event){
        choiceboxTypeLocation.getValue();
    }

    @FXML
    void goBackScene(ActionEvent event){
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("CompanyMenu.fxml")));
            Scene login = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(login);
            stage.setTitle("Menu Empresa");
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void createLocation(ActionEvent event){
        String selectedType = choiceboxTypeLocation.getValue();

        if(selectedType != null){
            User loggedInUser = UserManager.getLoggedInUser();
            Company currentCompany = null;
            if(loggedInUser instanceof Company) {
                currentCompany=(Company) loggedInUser;

                Location local = new Location();
                local.setCity(txtfieldCity.getText());
                local.setStreet(txtfieldStreet.getText());
                local.setPostcode(Integer.parseInt(txtfieldPostCode.getText()));
                local.setTypeLocation(TypeLocation.valueOf(choiceboxTypeLocation.getValue()));
                local.setCompany(currentCompany);
                currentCompany.setLocations(local.getLocations());

                Location.createLocation(local, currentCompany);
                System.out.println("\nLocal criado com sucesso.");
            }
        }

        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("CompanyMenu.fxml")));
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("Menu Empresa");
            stage.show();

        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }


    }
}
