package com.example.demo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Company extends  User {

    private ArrayList<Employee> employees;
    private ArrayList<Location> locations;
    private ArrayList<Services> services;
    //----------------Constructor--------------------
    public Company(){
        this.employees = new ArrayList<>();
        this.services = new ArrayList<>();
        this.locations= new ArrayList<>();
    }
    //-------------------Getters & Setters--------------------

    public ArrayList<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(ArrayList<Employee> employees) {
        this.employees = employees;
    }

    public ArrayList<Location> getLocations() {
        return locations;
    }

    public void setLocations(ArrayList<Location> locations) {
        this.locations = locations;
    }

    public ArrayList<Services> getServices() {
        return services;
    }

    public void setServices(ArrayList<Services> services) {
        this.services = services;
    }


    //--------------------------------------------------------------------


    public void addEmployee(Employee employee){
        employees.add(employee);
    }

    public void addLocal(Location local){
        locations.add(local);
    }

    public void addService(Services service){services.add(service);}

    public static void createCompany(Company company){
        try{
            Repository repo= Repository.getRepo();
            Map<String,Company> companies= repo.getCompanies();
            if(companies==null){
                companies= new HashMap<>();
                repo.setCompanies(companies);
            }


            if(companies.containsValue(company.getName())){
                System.out.println("Já existe uma empresa com esse dono.");
            }else{
                companies.put(company.getName(),company);
                System.out.println("\nEmpresa criada com sucesso.");

                repo.serialize("src\\main\\resources\\repo\\repositorio.dat");
            }
        }catch (Exception ex){
            System.out.println("Erro ao criar Empresa." + ex.getMessage());
        }
    }

}
