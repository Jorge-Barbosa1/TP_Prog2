package com.example.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Map;
import java.util.Objects;

public class ControllerCompanyMenu {
    @FXML
    Button logoutButton;
    @FXML
    Button createEmployeeButton;
    @FXML
    Button createLocationButton;
    @FXML
    Button createServicesButton;
    @FXML
    Button listEmployeeButton;
    @FXML
    Button listServicesButton;
    @FXML
    Button listAppointmentsButton;




    @FXML
    void logout(ActionEvent event){
        try{
            Parent root= FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Login.fxml")));
            Scene login= new Scene(root);
            Stage stage= (Stage)((Node)event.getSource()).getScene().getWindow();
            stage.setScene(login);
            stage.setTitle("Login");
            stage.show();
        }catch (IOException e) {
            e.printStackTrace();
        }
    }


    @FXML
    private void addEmplyoee(ActionEvent event){
        try{
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("CreateEmployee.fxml")));
            Scene scene= new Scene(root);
            Stage stage= (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("Criar Funcionário");
            stage.show();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    private void addService(ActionEvent event){
        try{
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("CreateService.fxml")));
            Scene scene= new Scene(root);
            Stage stage= (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("Criar serviço");
            stage.show();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void addLocal(ActionEvent event){
        try{
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("CreateLocation.fxml")));
            Scene scene= new Scene(root);
            Stage stage= (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("Criar Local");
            stage.show();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void listServices(ActionEvent event){
        try{
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("ListServices.fxml")));
            Scene scene= new Scene(root);
            Stage stage= (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("Lista de Serviços");
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML
    void listEmployees(ActionEvent event){
        try{
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("ListEmployees.fxml")));
            Scene scene= new Scene(root);
            Stage stage= (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("Lista de Funcionários");
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }



}
