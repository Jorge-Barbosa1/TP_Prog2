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

public class ControllerClientMenu {
    @FXML
    Button logoutButton;
    @FXML
    Button createAppointmentButton;
    @FXML
    Button paymentsButton;

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
    void addAppointment(ActionEvent event){
        try{
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("CreateAppointment.fxml")));
            Scene scene= new Scene(root);
            Stage stage= (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("Marcação");
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
