package com.example.demo;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class ControllerCreateEmployee implements Initializable {
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
    Button registerEmployeeButton;
    @FXML
    Button goBackButton;
    @FXML
    ChoiceBox<String> choiceBoxTypeEmployees;
    @FXML
    TextField txtfieldProfNumber;
    @FXML
    Label labelProfNumber;



    private String[] typeEmployees= {"AUXILIAR", "EDUCADOR", "SECRETARIO", "VETERINARIO"};

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        choiceBoxTypeEmployees.getItems().addAll(typeEmployees) ;
        choiceBoxTypeEmployees.setOnAction(this::getTypeEmployees);

    }

    private void getTypeEmployees(ActionEvent event) {
        String selectedType = choiceBoxTypeEmployees.getValue();

        if("VETERINARIO".equals(selectedType)){
            txtfieldProfNumber.setVisible(true);
            labelProfNumber.setVisible(true);
        }else{
            txtfieldProfNumber.setVisible(false);
            labelProfNumber.setVisible(false);
        }
    }



    @FXML
    void registerEmployee(ActionEvent event) {
        String selectedType = choiceBoxTypeEmployees.getValue();

        if (selectedType != null) {
            User loggedInUser = UserManager.getLoggedInUser();
            System.out.println("Tipo User: " + loggedInUser.getTypeUser());//Verificar o tipo do user
            Company currentCompany = null;

            if (loggedInUser instanceof Company) {
                currentCompany = (Company) loggedInUser;


                Employee emp = new Employee();
                emp.setName(txtfieldName.getText());
                emp.setN_CC(Integer.parseInt(txtfieldCC.getText()));
                emp.setNif(Integer.parseInt(txtfieldNif.getText()));
                emp.setPhone(Integer.parseInt(txtfieldPhone.getText()));
                emp.setHouse(txtfieldHouse.getText());
                emp.setLocation(txtfieldLocation.getText());
                emp.setDateBorn(dpickerDateBorn.getValue());
                emp.setTypeEmployee(TypeEmployee.valueOf(choiceBoxTypeEmployees.getValue()));
                emp.setUsername(txtfieldUsername.getText());
                emp.setPassword(pwdfieldPassword.getText());

                if(emp.getTypeEmployee()==TypeEmployee.VETERINARIO){
                    try {
                        if(!txtfieldProfNumber.getText().isEmpty()){
                            emp.setProfNumber(Integer.parseInt(txtfieldProfNumber.getText()));
                        }else{
                            System.out.println("O campo não pode estar vazio");
                            return;
                        }
                    }catch (NumberFormatException ex){
                        System.out.println("o campo TxtfieldProfNumber tem de estar preenchido");
                        return;
                    }
                }


                emp.setCompany(currentCompany);
                currentCompany.addEmployee(emp);
                Employee.createEmployee(emp, currentCompany);//criar o funcionario

                System.out.println(emp.getTypeEmployee() + " criado com sucesso.");
                try{
                    Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("CompanyMenu.fxml")));
                    Scene scene = new Scene(root);
                    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    stage.setScene(scene);
                    stage.setTitle("Menu Empresa");
                    stage.show();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }


            } else {
                System.out.println("Usuário nao é uma instancia da classe Company.");
            }
        }
    }

    @FXML
    void gobackScene(ActionEvent event){
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("CompanyMenu.fxml")));
            Scene login = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(login);
            stage.setTitle("Menu Empresa");
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }





}
