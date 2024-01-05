package com.example.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.nio.file.attribute.UserDefinedFileAttributeView;
import java.util.Objects;
import java.util.ResourceBundle;

public class ControllerListCompanys implements Initializable {
    @FXML
    TableView<Company> companiesView;
    @FXML
    Button goBackButton;
    @FXML
    Button eliminateButton;
    @FXML
    TableColumn<Company, String> nameColumn;
    @FXML
    TableColumn<Company,Integer> phoneColumn;
    @FXML
    TableColumn<Company,Integer> nifColumn;

    @Override
    public void initialize(URL url, ResourceBundle rb){
        Repository repo= Repository.getRepo();

        for(Company c: repo.getCompanies().values()){
            companiesView.getItems().addAll(c);
        }
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        phoneColumn.setCellValueFactory(new PropertyValueFactory<>("phone"));
        nifColumn.setCellValueFactory(new PropertyValueFactory<>("nif"));

        companiesView.getSelectionModel().selectedItemProperty().addListener((obs,oldSelection, newSelection)-> {
            if (newSelection != null) {
                eliminateButton.setDisable(false);
            }else{
                eliminateButton.setDisable(true);
            }
        });
    }

    @FXML
    void eliminateCompany(ActionEvent event){
        Company selectedCompany= companiesView.getSelectionModel().getSelectedItem();
        if(selectedCompany!=null){
            boolean removed = Repository.getRepo().getCompanies().remove(selectedCompany.getName(),selectedCompany);
            //Eliminar a empresa selecionada
            if(removed){
                companiesView.getItems().remove(selectedCompany);
                System.out.println("Empresa "+ selectedCompany.getName() + " foi excluida.");

                Repository updatedRepo= new Repository();
                updatedRepo.serialize("src\\main\\resources\\repo\\repositorio.dat");
            }else{
                System.out.println("Erro ao eliminar a empresa "+ selectedCompany.getName());
            }
        }
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
