package com.example.demo;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Map;
import java.util.SimpleTimeZone;

public class User implements Serializable {
    private String name;
    private int n_CC;
    private int nif;
    private int phone;
    private String house;
    private String location;
    private LocalDate dateBorn;
    private TypeUsers typeUser;
    private String username;
    private String password;

    private ArrayList<User> users;

    //------------------------Constructor----------------------------
    public User(){
        users= new ArrayList<>();
    }
    //-------------------GETTERS & SETTERS---------------------------

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getN_CC() {
        return n_CC;
    }

    public void setN_CC(int n_CC) {
        this.n_CC = n_CC;
    }

    public int getNif() {
        return nif;
    }

    public void setNif(int nif) {
        this.nif = nif;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public String getHouse() {
        return house;
    }

    public void setHouse(String house) {
        this.house = house;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public LocalDate getDateBorn() {
        return dateBorn;
    }

    public void setDateBorn(LocalDate dateBorn) {
        this.dateBorn = dateBorn;
    }

    public TypeUsers getTypeUser() {
        return typeUser;
    }

    public void setTypeUser(TypeUsers typeUser) {
        this.typeUser = typeUser;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    public void setUsers(ArrayList<User> users) {
        this.users = users;
    }


    //-----------------------------Create User--------------------------

    public static void createUser(User user){
        try{
            Repository repo= Repository.getRepo();
            Map<Integer,User> users= repo.getUsers();

            if(users.containsKey(user.getNif())){
                System.out.println("Já existe esse utilizador.");
            }else{
                users.put(user.getNif(),user);
                System.out.println("\nUtilizador criado com sucesso.");

                repo.serialize("src\\main\\resources\\repo\\repositorio.dat");
            }
        }catch (Exception ex){
            System.out.println("Erro ao criar o Utilizador." +ex.getMessage());
        }
    }





    //-------------------------ToString------------------------
    @Override
    public String toString(){
        return "\nUsername: "+ this.getUsername()+
                "\nPassword: "+this.getPassword()+
                "\nNome: "+ this.getName()+
                "\nNumero Cartão Cidadão: "+ this.getN_CC() +
                "\nNumero Contribuinte(NIF): " +this.getNif()+
                "\nTelefone: "+this.getPhone()+
                "\nMorada: "+ this.getHouse()+
                "\nLocalidade: "+this.getLocation()+
                "\nData Nascimento: "+this.getDateBorn()+
                "\nTipo Utilizador:"+this.getTypeUser();
    }


}
