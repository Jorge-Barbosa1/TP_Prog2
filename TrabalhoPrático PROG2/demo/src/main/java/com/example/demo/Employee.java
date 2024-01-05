package com.example.demo;

import java.util.HashMap;
import java.util.Map;

public class Employee extends User{
    private int profNumber;
    private TypeEmployee typeEmployee ;
    private Company company;


//------------------------Constructor--------------------------------
    public Employee(){
        super();
    }

    //-----------------------GETTERS & SETTERS-----------------

    public TypeEmployee getTypeEmployee() {
        return typeEmployee;
    }

    public void setTypeEmployee(TypeEmployee typeEmployee) {
        this.typeEmployee = typeEmployee;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public int getProfNumber() {
        return profNumber;
    }

    public void setProfNumber(int profNumber) {
        this.profNumber = profNumber;
    }
    //-----------------------------------------------------------------------------------------

    public static void createEmployee(Employee employee,Company company){
        try{
            Repository repo = Repository.getRepo();
            Map<Integer,Employee> employees = repo.getEmployees();
            if(employees==null){
                employees= new HashMap<>();
            }

            if(employees.containsKey(employee.getNif())){
                System.out.println("\nJá existe um funcionário com o NIF:" + employee.getNif());
            }else{
                employees.put(employee.getNif(),employee);
                employee.setCompany(company);//relacionar o funcionario com a empresa
                company.addEmployee(employee);
                System.out.println("\nFuncionário Criado com sucesso.");

                repo.serialize("src\\main\\resources\\repo\\repositorio.dat");
            }
        }catch (Exception ex){
            System.out.println("Erro ao criar o funcionário." + ex.getMessage());
        }
    }
}
