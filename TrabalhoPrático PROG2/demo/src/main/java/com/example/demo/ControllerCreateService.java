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
import java.net.URL;

import java.util.Objects;
import java.util.ResourceBundle;

public class ControllerCreateService implements Initializable {
    @FXML
    TextField txtfieldPrice;
    @FXML
    TextField txtfieldProduct;
    @FXML
    Button goBackButton;
    @FXML
    Button addButton;
    @FXML
    ChoiceBox<String> choiceboxTypeService;

    private final String[] typeServices={"TOSQUIA", "BANHO", "PASSEIO", "CONSULTA", "EDUCAÇAO"};

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        choiceboxTypeService.getItems().addAll(typeServices);
        choiceboxTypeService.setOnAction(this::getTypeServices);
    }

    public void getTypeServices(ActionEvent event){choiceboxTypeService.getValue();}

    @FXML
    void add(ActionEvent event) {
        String selectedType = choiceboxTypeService.getValue();
        if (selectedType != null) {
            User loggedInUser = UserManager.getLoggedInUser();
            Company currentCompany = null;

            if(loggedInUser instanceof  Company){
                currentCompany=(Company) loggedInUser;

                Services service = new Services();
                service.setTypeService(TypeServices.valueOf(choiceboxTypeService.getValue()));
                service.setPrice(Float.parseFloat(txtfieldPrice.getText()));
                service.setProduct(txtfieldProduct.getText());
                service.setCompany(currentCompany);
                currentCompany.setServices(service.getServices());
                Services.createService(service,currentCompany);
                System.out.println("Serviço criado com sucesso");

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

    @FXML
    void backScene(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("CompanyMenu.fxml")));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Menu Empresa");
        stage.show();
    }

}


