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
import java.util.Objects;

public class ControllerAdminMenu {
    @FXML
    Button logoutButton;
    @FXML
    Button buttonRegisterAdmin;
    @FXML
    Button buttonListServices;
    @FXML
    Button buttonListCompanys;
    @FXML
    Button buttonListPayments;
    @FXML
    Button buttonListAppointments;


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
    void createAdmin(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("CreateAdmin.fxml"));
        Parent root = loader.load();

        Scene scene= new Scene(root);
        Stage createAdminStage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        createAdminStage.setScene(scene);
        createAdminStage.setTitle("Criar Administrador");
        createAdminStage.show();

    }

    @FXML
    void listCompanies(ActionEvent event){
        try{
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("ListCompanys.fxml")));
            Scene scene= new Scene(root);
            Stage stage= (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("Lista de Empresas");
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void listAllServices(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("ListAllServices.fxml")));
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("Lista de Todos os Serviços");
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
