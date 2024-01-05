package com.example.demo;

import java.io.Serializable;

import java.util.ArrayList;

public class Services implements Serializable {
    private TypeServices typeService;
    private float price;
    private String product;
    private Company company;
    private ArrayList<Services> services;

    public Services() {
        this.services = new ArrayList<>();
    }


    //----------------------------Getters & Setters ------------------------------


    public TypeServices getTypeService() {
        return typeService;
    }

    public void setTypeService(TypeServices typeService) {
        this.typeService = typeService;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public ArrayList<Services> getServices() {
        return services;
    }

    public void setServices(ArrayList<Services> services) {
        this.services = services;
    }
    //-------------------------------------------------------------

    public static void createService(Services service, Company company) {
        try {
            Repository repo = Repository.getRepo();
            ArrayList<Services> services = repo.getServices();

            //Verificar se a lista está vazia
            if (services == null) {
                services = new ArrayList<>();
                services.add(service);
                company.addService(service);
                repo.setServices(services);//atualizar na classe repository
            }

            services.add(service);
            System.out.println("Serviço de " + service.getTypeService() + "criado com sucesso.");
            repo.serialize("src\\main\\resources\\repo\\repositorio.dat");

        } catch (Exception ex) {
            System.out.println("Erro ao criar Serviço." + ex.getMessage());
        }
    }

    @Override
    public String toString() {
        return "Type: " + typeService +
                ", Price: " + price +
                ", Product: " + product;
    }
}
