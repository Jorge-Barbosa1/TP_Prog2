package com.example.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class ControllerCreateAdmin {
    @FXML
    TextField txtfieldName;
    @FXML
    TextField txtfieldNif;
    @FXML
    TextField txtfieldCC;
    @FXML
    TextField txtfieldHouse;
    @FXML
    TextField txtfieldPhone;
    @FXML
    TextField txtfieldLocation;
    @FXML
    TextField txtfieldUsername;
    @FXML
    PasswordField pwdfieldPassword;
    @FXML
    DatePicker dpickerDateBorn;
    @FXML
    Button registerAdminButton;
    @FXML
    Button goBackButton;

    @FXML//Mudar para a Scene de Login
    void backScene(ActionEvent event){
        try{
            Parent root= FXMLLoader.load(Objects.requireNonNull(getClass().getResource("AdminMenu.fxml")));
            Scene login= new Scene(root);
            Stage stage= (Stage)((Node)event.getSource()).getScene().getWindow();
            stage.setScene(login);
            stage.setTitle("Menu Administrador");
            stage.show();
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void registerAdmin(ActionEvent event){
        User user= new User();
        user.setName(txtfieldName.getText());
        user.setN_CC(Integer.parseInt(txtfieldCC.getText()));
        user.setNif(Integer.parseInt(txtfieldNif.getText()));
        user.setPhone(Integer.parseInt(txtfieldPhone.getText()));
        user.setHouse(txtfieldHouse.getText());
        user.setLocation(txtfieldLocation.getText());
        user.setDateBorn(dpickerDateBorn.getValue());
        user.setUsername(txtfieldUsername.getText());
        user.setPassword(pwdfieldPassword.getText());
        user.setTypeUser(TypeUsers.ADMIN);

        User.createUser(user);
        System.out.println("Admin "+ user.getName() + " criado com sucesso.");

        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("AdminMenu.fxml")));
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("Menu Administrador");
            stage.show();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

}
