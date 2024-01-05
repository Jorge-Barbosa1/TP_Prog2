package com.example.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Objects;

public class ControllerLogin {
    @FXML
    Button entrarButton;
    @FXML
    TextField txtfieldUsername;
    @FXML
    PasswordField pwdfieldPassword;
    @FXML
    Button criarContabutton;
    @FXML
    ImageView imgLogo;

    @FXML//Mostrar a imagem do "Logo" na Scene do Login
    public void displayLogo(DragEvent event) {
        String imagepath = "images\\imglogo.png";
        Image image = new Image(Objects.requireNonNull(getClass().getResourceAsStream(imagepath)));
        imgLogo.setImage(image);
    }


    @FXML
//Mudar para a Scene de Criar conta
    void switchToCriarConta(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("RegisterUser.fxml")));
            Scene criarContaScene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(criarContaScene);
            stage.setTitle("Criar Conta");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void login(ActionEvent event) {
        String username = txtfieldUsername.getText();
        String password = pwdfieldPassword.getText();

        try{
            UserManager userManager= new UserManager("src\\main\\resources\\repo\\repositorio.dat");
            //O UserManager carrega os users no construtor

            if(userManager.authenticate(username,password)){
                User loggedInUser = userManager.getUserByUsername(username);
                UserManager.setLoggedInUser(loggedInUser);

                if(loggedInUser.getTypeUser()==TypeUsers.ADMIN){

                    FXMLLoader loader = new FXMLLoader(getClass().getResource("AdminMenu.fxml"));
                    Parent root = loader.load();

                    Scene scene = new Scene(root);
                    Stage adminStage = (Stage) ((Node) event.getSource()).getScene().getWindow();

                    adminStage.setScene(scene);
                    adminStage.setTitle("Menu Administrador");
                    adminStage.show();
                }else if(loggedInUser.getTypeUser()==TypeUsers.CLIENTE){

                    FXMLLoader loader = new FXMLLoader(getClass().getResource("ClientMenu.fxml"));
                    Parent root = loader.load();

                    Scene scene = new Scene(root);
                    Stage adminStage = (Stage) ((Node) event.getSource()).getScene().getWindow();

                    adminStage.setScene(scene);
                    adminStage.setTitle("Menu Cliente");
                    adminStage.show();

                }else if(loggedInUser.getTypeUser()==TypeUsers.PRESTADOR){

                    FXMLLoader loader = new FXMLLoader(getClass().getResource("CompanyMenu.fxml"));
                    Parent root = loader.load();

                    Scene scene = new Scene(root);
                    Stage adminStage = (Stage) ((Node) event.getSource()).getScene().getWindow();

                    adminStage.setScene(scene);
                    adminStage.setTitle("Menu Empresa");
                    adminStage.show();
                }
                System.out.println("Login bem sucedido");

            }else{
                System.out.println("Login falhou");
            }
        } catch (RuntimeException | IOException ex) {
            throw new RuntimeException(ex);
        }

    }
}
