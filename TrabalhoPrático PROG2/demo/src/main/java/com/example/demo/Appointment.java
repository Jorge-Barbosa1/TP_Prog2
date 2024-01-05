package com.example.demo;

import java.util.ArrayList;
import java.util.Date;

public class Appointment {
    private TypeStatus status;
    private Date date;
    private Employee employee;
    private Location local;
    private int hour;

    private ArrayList<Client> clients;
    private ArrayList<Company> companies;

    public Appointment(){
        this.clients=new ArrayList<>();
        this.companies= new ArrayList<>();
    }

    //--------------------------GETTERS & SETTERS-----------------------------


    public ArrayList<Client> getClients() {
        return clients;
    }

    public void setClients(ArrayList<Client> clients) {
        this.clients = clients;
    }

    public ArrayList<Company> getCompanies() {
        return companies;
    }

    public void setCompanies(ArrayList<Company> companies) {
        this.companies = companies;
    }

    public TypeStatus getStatus() {
        return status;
    }

    public void setStatus(TypeStatus status) {
        this.status = status;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Location getLocal() {
        return local;
    }

    public void setLocal(Location local) {
        this.local = local;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }
    //--------------------------------------------------------------
    public void addClient(Client client){
        clients.add(client);
    }

    public void addCompany(Company company){
        companies.add(company);
    }
}
