package com.example.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Objects;
import java.util.ResourceBundle;

public class ControllerListServices implements Initializable {
    @FXML
    TableView<Services> servicesView;
    @FXML
    Button goBackButton;
    @FXML
    private TableColumn<Services, TypeServices> typeServiceColumn;
    @FXML
    private TableColumn<Services, Float> priceColumn;
    @FXML
    private TableColumn<Services, String> productColumn;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //System.out.println("Check");
        Repository repo = Repository.getRepo();
        Company currentCompany = (Company) UserManager.getLoggedInUser();

        if(currentCompany!=null){

            for(Services s: repo.getServices()){
                servicesView.getItems().addAll(s);
                //servicesView.getItems().addAll(s.getTypeService().toString());
            }
            typeServiceColumn.setCellValueFactory(new PropertyValueFactory<>("typeService"));
            priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
            productColumn.setCellValueFactory(new PropertyValueFactory<>("product"));
        }
    }


    @FXML
    void goBack(ActionEvent event){
        try{
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("CompanyMenu.fxml")));
            Scene scene= new Scene(root);
            Stage stage= (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("Menu Empresa");
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
