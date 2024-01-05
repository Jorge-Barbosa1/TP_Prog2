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
import java.util.Objects;
import java.util.ResourceBundle;

public class ControllerListEmployees implements Initializable {


    @FXML
    TableView<Employee> employeesView;
    @FXML
    Button goBackButton;
    @FXML
    TableColumn<Employee, TypeLocation> typeColumn;
    @FXML
    TableColumn<Employee,String> nameColumn;
    @FXML
    TableColumn<Employee, Integer> nifColumn;


    @Override
    public void initialize(URL url, ResourceBundle rb){
        Repository repo= Repository.getRepo();
        Company currentCompany = (Company) UserManager.getLoggedInUser();

        for(Employee e :repo.getEmployees().values()){
            employeesView.getItems().addAll(e);
        }
        typeColumn.setCellValueFactory(new PropertyValueFactory<>("typeEmployee"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        nifColumn.setCellValueFactory(new PropertyValueFactory<>("nif"));

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
