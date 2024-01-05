package com.example.demo;

import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleStringProperty;
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
import java.util.Objects;
import java.util.ResourceBundle;

public class ControllerListAllServices implements Initializable {
    @FXML
    TableView<Services> servicesView;
    @FXML
    Button goBackButton;
    @FXML
    TableColumn<Services, TypeServices> typeServiceColumn;
    @FXML
    TableColumn<Services, Float> priceColumn;
    @FXML
    TableColumn<Services, String> productColumn;
    @FXML
    TableColumn<Services,String> companyColumn;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Repository repo = Repository.getRepo();

        for(Services s: repo.getServices()){
            servicesView.getItems().addAll(s);
            //servicesView.getItems().addAll(s.getTypeService().toString());
        }
        typeServiceColumn.setCellValueFactory(new PropertyValueFactory<>("typeService"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        productColumn.setCellValueFactory(new PropertyValueFactory<>("product"));
        companyColumn.setCellValueFactory(cellData ->{//Colocar apenas o nome da empresa na tabela
           Services service = cellData.getValue();
           Company company = service.getCompany();
           if(company!= null){
               return new SimpleStringProperty(company.getName());
           } else {
               return new SimpleStringProperty("");
           }
        });
    }


    @FXML
    void goBack(ActionEvent event){
        try{
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("AdminMenu.fxml")));
            Scene scene= new Scene(root);
            Stage stage= (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("Menu Admin");
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
