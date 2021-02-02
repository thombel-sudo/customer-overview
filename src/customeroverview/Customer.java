/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package customeroverview;

/**
 *
 * @author User
 */
public class Customer {

    private int id;
    private String name;
    private String surname;
    private String number;
    private String city;
    private String address;
    private String payment;

    public Customer(int id, String name, String surname,String number, String city, String address, String payment) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.number = number;
        this.city = city;
        this.address = address;
        this.payment = payment;
        
    }

    //public Customer() {
    //}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public String getSurname(){
        return surname;
    }
    
    public void setSurname(String surname) {
        this.surname = surname;
    }
    
    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
    
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
    
    public String getAddress(){
        return address;
    }
    
    public void setAddress(String address) {
        this.address = address;
    }
    
    public String getPayment() {
        return payment;
    }

    public void setPayment(String payment) {
        this.payment = payment;
    }
}
