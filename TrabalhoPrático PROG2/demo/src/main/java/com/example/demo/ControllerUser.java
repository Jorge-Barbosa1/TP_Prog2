package com.example.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class ControllerUser implements Initializable {
    private boolean nifStatus;
    private boolean telefoneStatus;
    private boolean ccStatus;

    @FXML
    PasswordField pwdfieldPassword;
    @FXML
    TextField txtfieldUsername;
    @FXML
    Button registerButton;
    @FXML
    Button backSceneButton;
    @FXML
    TextField txtfieldName;
    @FXML
    TextField txtfieldNumCC;
    @FXML
    TextField txtfieldNif;
    @FXML
    TextField txtfieldPhone;
    @FXML
    TextField txtfieldHouse;
    @FXML
    TextField txtfieldLocation;
    @FXML
    DatePicker dpickerDateBorn;
    @FXML
    Text nifError;
    @FXML
    Text phoneError;
    @FXML
    Text ccError;

    //Conseguir por e selecionar texto da choice box
    @FXML
    ChoiceBox<String> choiceBoxTypeUsers;
    private final String[] typeUsers= {"CLIENTE", "PRESTADOR"};

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        choiceBoxTypeUsers.getItems().addAll(typeUsers);
        choiceBoxTypeUsers.setOnAction(this::getTypeUsers);//O "::" é um "method reference operator"
        // basicamente faz uma referencia para o getTiposUtilizador e linkar para a choiseBox
    }

    public void getTypeUsers(ActionEvent event){
        choiceBoxTypeUsers.getValue();
    }

    @FXML
    void switchToLogin(ActionEvent event){
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Login.fxml")));
            Scene login = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(login);
            stage.setTitle("Login");
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    @FXML
    void verifyNif(KeyEvent keyEvent){

        /*
        String nifText= txtfieldNif.getText();
        if(nifText.matches("[0-9]*")){//verificar se o input são apenas digitos
            nifStatus= true;
            nifErro.setVisible(false);
            if(nifText.length() != 9){//Se o input nao tiver exatamente 9 digitos dá erro
                nifStatus= false;
                nifErro.setVisible(true);
            }
        }else{
            nifStatus=false;
            nifErro.setVisible(true);
        }

         */
    }
    @FXML
    void verifyTelefone(KeyEvent keyEvent){
        /*
        String telefoneText= txtfieldTelefone.getText().trim();//Trim elimina espaços em branco
        telefoneStatus= telefoneText.length() == 9 && telefoneText.matches("\\d+");//O \\d+ verifica se só tem digitos

        telefoneErro.setVisible(!telefoneStatus);
        telefoneErro.setText(telefoneStatus ? "" : " O numero de telefone deve ter 9 digitos.");
        System.out.println("Erro no telefone");

         */
    }
    @FXML
    void verifyCC(KeyEvent keyEvent){
        /*
        String CCText= txtfieldNumCC.getText();
        if(CCText.length()!= 8){
            ccStatus= false;
            ccErro.setVisible(true);
        }else{
            ccStatus=true;
            ccErro.setVisible(false);
        }

         */
    }
    private static User loggedInUser;

    @FXML
    void register(ActionEvent event) {
        String selectedType = choiceBoxTypeUsers.getValue();

        if (selectedType != null) {

            if (choiceBoxTypeUsers.getValue().equals("CLIENTE")) {
                User user = new User();
                user.setName(txtfieldName.getText());
                user.setN_CC(Integer.parseInt(txtfieldNumCC.getText()));
                user.setPhone(Integer.parseInt(txtfieldPhone.getText()));
                user.setNif(Integer.parseInt(txtfieldNif.getText()));
                user.setHouse(txtfieldHouse.getText());
                user.setLocation(txtfieldLocation.getText());
                user.setDateBorn(dpickerDateBorn.getValue());
                user.setTypeUser(TypeUsers.valueOf(choiceBoxTypeUsers.getValue()));
                user.setUsername(txtfieldUsername.getText());
                user.setPassword(pwdfieldPassword.getText());

                User.createUser(user);
                System.out.println("Cliente criado com sucesso.");
            } else if (choiceBoxTypeUsers.getValue().equals("PRESTADOR")) {
                loggedInUser = new Company();

                loggedInUser.setName(txtfieldName.getText());
                loggedInUser.setN_CC(Integer.parseInt(txtfieldNumCC.getText()));
                loggedInUser.setPhone(Integer.parseInt(txtfieldPhone.getText()));
                loggedInUser.setNif(Integer.parseInt(txtfieldNif.getText()));
                loggedInUser.setHouse(txtfieldHouse.getText());
                loggedInUser.setLocation(txtfieldLocation.getText());
                loggedInUser.setDateBorn(dpickerDateBorn.getValue());
                loggedInUser.setTypeUser(TypeUsers.valueOf(choiceBoxTypeUsers.getValue()));
                loggedInUser.setUsername(txtfieldUsername.getText());
                loggedInUser.setPassword(pwdfieldPassword.getText());

                Company.createCompany((Company) loggedInUser);
                User.createUser(loggedInUser);
                System.out.println("Prestador/Empresa criado com sucesso.");
            }


            try {
                Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Login.fxml")));
                Scene scene = new Scene(root);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.setTitle("Login");
                stage.show();

            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }
    }

}

