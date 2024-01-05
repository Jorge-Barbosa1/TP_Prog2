package com.example.demo;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("Login.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        stage.setTitle("Login!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {

        //Criar o primeiro Admin



        User j = new User();
        j.setUsername("admin");
        j.setPassword("admin");
        j.setNif(1);
        j.setTypeUser(TypeUsers.ADMIN);
        User.createUser(j);


        /*Testes Para fazer login
        User j = new User();
        User j2= new User();
        j.setUsername("jorge");
        j.setNif(123);
        j.setPassword("123");
        j2.setPassword("1234");
        j2.setUsername("ze");
        j2.setNif(2134);
        User.createUser(j2);
        User.createUser(j);

         */
        launch();
    }
}