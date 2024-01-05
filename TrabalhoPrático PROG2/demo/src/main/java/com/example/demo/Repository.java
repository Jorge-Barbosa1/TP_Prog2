package com.example.demo;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Repository implements Serializable {
    private static Repository repo = null;//Singleton
    private static final String filePath = "src\\main\\resources\\repo\\repositorio.dat";
    private Map<Integer, User> users;
    private Map<Integer,Employee> employees;
    private Map<String,Company> companies;
    private Map<Location,Company> locations;
    private ArrayList<Services> services;
    private ArrayList<Appointment> appointments;

    //------------------------------Contrutor--------------------------
    public Repository(){
        this.users= new HashMap<>();
        this.employees= new HashMap<>();
        this.services= new ArrayList<>();
        this.companies= new HashMap<>();
        this.locations= new HashMap<>();
        this.appointments= new ArrayList<>();
    }

    //---------------------------GETTERS & SETTERS---------------------
    public static Repository getRepo() {
        if (repo == null) {
            repo = new Repository();
            try {
                repo = Repository.desirialize(filePath);
                System.out.println("Ficheiro lido com sucesso!");
            } catch (Exception ex) {
                System.out.println("Erros ao ler o ficheiro" + ex.getMessage());
            }
        }
        return repo;
    }


    public Map<Integer, User> getUsers() {
        return users;
    }

    public Map<Integer, Employee> getEmployees() {
        return employees;
    }

    public Map<String, Company> getCompanies() {
        return companies;
    }

    public void setCompanies(Map<String, Company> companies) {
        this.companies = companies;
    }

    public ArrayList<Services> getServices() {
        return services;
    }

    public void setServices(ArrayList<Services> services) {
        this.services = services;
    }

    public Map<Location, Company> getLocations() {
        return locations;
    }

    public void setLocations(Map<Location, Company> locations) {
        this.locations = locations;
    }

    public ArrayList<Appointment> getAppointments() {
        return appointments;
    }

    public void setAppointments(ArrayList<Appointment> appointments) {
        this.appointments = appointments;
    }

    //---------------------Serialize & Deserializ------------------
    public void serialize(String filename) {
        try {
            FileOutputStream fileout = new FileOutputStream(filePath);
            ObjectOutputStream output = new ObjectOutputStream(fileout);
            output.writeObject(this);
            output.close();
            fileout.close();
            System.out.println("\nDados guardados no ficheiro: " + filePath);
        } catch (IOException ex) {
            System.out.println("Error Serialize: " + ex.getMessage());
        }
    }

    public static Repository desirialize(String filename){
        try{
            FileInputStream fileIn= new FileInputStream(filename);
            ObjectInputStream input = new ObjectInputStream(fileIn);
            repo= (Repository) input.readObject();
            if (repo.users == null) {
                repo.users = new HashMap<>();
            }
            input.close();
            fileIn.close();
        }catch (IOException ex){
            System.out.println("Error deserialize: "+ ex.getMessage());
        }catch (ClassNotFoundException ex){
            System.out.println("Repositorio nao encontrado"+ ex.getMessage());
        }
        return repo;
    }

}
