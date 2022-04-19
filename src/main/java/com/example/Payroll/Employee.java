package com.example.Payroll;

import org.springframework.data.annotation.Id;

import java.util.ArrayList;
import java.util.Objects;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;

@Entity
    class Employee {

        @Id  Long id;
        String name ;
        boolean is_success ;
        String email;
        String roll ;
        @ElementCollection ArrayList<String> numbers;
        @ElementCollection ArrayList<String> alphabets;

        public Employee() {
            id = 1l;
            name = "BhavyaMoonat281100";
            is_success = true;
            email = "mail.google.com";
            roll = "12345";
            numbers = new ArrayList<>();
            alphabets = new ArrayList<>();
        }

        public Employee(ArrayList<String> num , ArrayList<String> alpha) {
            id = 1l;
            name = "BhavyaMoonat281100";
            is_success = true;
            email = "mail.google.com";
            roll = "12345";
            numbers = num;
            alphabets = alpha;
        }

        @Override
        public String toString() {
            return "{" + "\n\t\"is_Success\": " + is_success+"," + "\n\t\"user_id\": \"" + name +"\","  + "\n\t\"email\" : \"" + email + "\"," +"\n\t\"roll_number\": \"" + roll + "\","+ "\n\t\"numbers\": " + numbers + "," + "\n\t\"alphabets\":" + alphabets + "\n}";
        }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNumbers(ArrayList<String> numbers) {
        this.numbers = numbers;
    }

    public void setAlphabets(ArrayList<String> alphabets) {
        this.alphabets = alphabets;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setIs_success(boolean is_success) {
        this.is_success = is_success;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRoll(String roll) {
        this.roll = roll;
    }

    @javax.persistence.Id
    public Long getId() {
        return id;
    }

    public String getName(){return name;}
    public String getEmail(){return email;}
    public String getRoll(){return roll;}
    public ArrayList<String> getNumbers (){return numbers;}
    public ArrayList<String> getAlphabets(){return alphabets;}

}
