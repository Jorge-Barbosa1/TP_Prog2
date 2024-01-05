package com.example.demo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Location {
    private Company company;
    private String city;
    private String street;
    private int postcode;
    private TypeLocation typeLocation;

    private ArrayList<Location> locations;

    //----------------------------------------Constructor--------------------------------------
    public Location(){
        this.locations= new ArrayList<>();
    }

    //--------------------------------------------GETTERS & SETTERS----------------------------------------------------

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public int getPostcode() {
        return postcode;
    }

    public void setPostcode(int postcode) {
        this.postcode = postcode;
    }

    public TypeLocation getTypeLocation() {
        return typeLocation;
    }

    public void setTypeLocation(TypeLocation typeLocation) {
        this.typeLocation = typeLocation;
    }

    public ArrayList<Location> getLocations() {
        return locations;
    }

    public void setLocations(ArrayList<Location> locations) {
        this.locations = locations;
    }
    //----------------------------------------------------------------------------------------
    public static void createLocation(Location location,Company company) {
        try {
            Repository repo = Repository.getRepo();
            Map<Location,Company> locations = repo.getLocations();

            if (locations == null) {
                locations = new HashMap<>();
                repo.setLocations(locations);
            }

            locations.put(location,company);
            location.setCompany(company);
            company.setLocations(location.getLocations());
            System.out.println("Local de " + location.getTypeLocation());
            repo.serialize("src\\main\\resources\\repo\\repositorio.dat");
        } catch (Exception ex) {
            System.out.println("Erro ao criar Serviço." + ex.getMessage());
        }
    }


}
