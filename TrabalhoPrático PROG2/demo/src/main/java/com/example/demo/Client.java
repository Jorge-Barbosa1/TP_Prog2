package com.example.demo;

import java.util.ArrayList;
import java.util.Date;
import java.util.Map;


public class Client extends User{
    private ArrayList<Client> clients;
    private ArrayList<Appointment> appointments;

    public Client(){
        super();
        this.appointments= new ArrayList<>();
        this.clients= new ArrayList<>();
    }
    //--------------------GETTERS & SETTERS-----------------------


    public ArrayList<Client> getClients() {
        return clients;
    }

    public void setClients(ArrayList<Client> clients) {
        this.clients = clients;
    }

    public ArrayList<Appointment> getAppointments() {
        return appointments;
    }

    public void setAppointments(ArrayList<Appointment> appointments) {
        this.appointments = appointments;
    }
    //------------------------------------------------------------

    public void createAppointment(Client client, Appointment appointment){
        try {
            Repository repo = Repository.getRepo();
            ArrayList<Appointment> appointments= repo.getAppointments();

            if(appointments==null){
                appointments= new ArrayList<>();
                appointments.add(appointment);
                clients.add(client);
                appointment.addClient(client);
                repo.setAppointments(appointments);
            }

            appointments.add(appointment);
            System.out.println("Marcação Completa.");
            repo.serialize("src\\main\\resources\\repo\\repositorio.dat");
        }catch (Exception ex){
            System.out.println("Erro ao fazer Marcação. "+ ex.getMessage());
        }
    }
}
