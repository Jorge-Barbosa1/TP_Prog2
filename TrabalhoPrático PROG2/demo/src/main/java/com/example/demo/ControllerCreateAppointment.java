package com.example.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class ControllerCreateAppointment  implements Initializable {
    @FXML
    TableView<Company> viewCompanies;
    @FXML
    Label labelList;
    @FXML
    TableColumn<Company, String> nameColumn;
    @FXML
    TableColumn<Company,Integer> phoneColumn;
    @FXML
    TableView<Location> viewLocals;
    @FXML
    TableColumn<Location, String> cityColumn;
    @FXML
    TableColumn<Location, String> streetColumn;
    @FXML
    Label labelListLocals;
    @FXML
    Button goBackButton;


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //Adicionar dados à tabela de Empresas
        Repository repo = Repository.getRepo();

        for (Company c : repo.getCompanies().values()) {
            viewCompanies.getItems().addAll(c);
        }
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        phoneColumn.setCellValueFactory(new PropertyValueFactory<>("phone"));

        viewCompanies.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                showLocal(newSelection);
            }else {
                hideLocals();
            }
        });

        cityColumn.setCellValueFactory(new PropertyValueFactory<>("city"));
        streetColumn.setCellValueFactory(new PropertyValueFactory<>("street" ));
    }

        private void showLocal(Company selectedCompany){
            //desaparecer as empresas
            viewCompanies.setVisible(false);
            labelList.setVisible(false);

            //Aparecer os locais da empresa escolhida
            viewLocals.setVisible(true);
            labelListLocals.setVisible(true);

            Repository repo= Repository.getRepo();
            for(Location l: repo.getLocations().keySet()){
                if(l.getCompany().equals(selectedCompany)){
                    //selecionar apenas os locais de Recolha
                    if(l.getTypeLocation()==TypeLocation.RECOLHA) {
                        viewLocals.getItems().addAll(l);
                    }
                }
            }
        }

        private void hideLocals(){
            //aparecer as empresas
            viewCompanies.setVisible(true);
            labelList.setVisible(true);

            //desparecer os locais
            viewLocals.setVisible(false);
            labelListLocals.setVisible(false);
        }

    @FXML
    void goBack(ActionEvent event){
        try{
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("ClientMenu.fxml")));
            Scene scene= new Scene(root);
            Stage stage= (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("Menu Cliente");
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    }