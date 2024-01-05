package com.example.demo;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ExecutionException;

public class CompanyManager {
    private Repository repository;
    private Map<String,Company> companyOwners;
    private String filepath;

    public CompanyManager(String filepath) throws URISyntaxException {
        this.repository= Repository.getRepo();
        this.filepath=filepath;
        this.companyOwners=new HashMap<>();
        loadCompanies();
    }

    void loadCompanies() {
        try {
            URL resourceUrl = getClass().getClassLoader().getResource(filepath);
            if (resourceUrl != null) {
                Path path = Paths.get(resourceUrl.toURI());
                try (ObjectInputStream input = new ObjectInputStream(new FileInputStream(path.toFile()))) {
                    Map<String, Company> loadComanies = (Map<String, Company>) input.readObject();
                    if (loadComanies != null) {
                        companyOwners = loadComanies;
                    } else {
                        companyOwners = new HashMap<>();
                    }
                } catch (IOException | ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
            } else {
                System.err.println("Resource not found: " + filepath);
            }
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }



    public static Company getCurrentCompany(){
        User loggedInUser = UserManager.getLoggedInUser();

        if(loggedInUser!=null && loggedInUser.getTypeUser().equals(TypeUsers.PRESTADOR)){
            return (Company) loggedInUser;//passar de User para Company
        }else{
            return null;
        }
    }



}
