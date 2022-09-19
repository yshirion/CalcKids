package com.example.objects;

import java.io.Serializable;

public class User implements Serializable {
    long id;
    String first_name;
    String last_name;
    String user_name;
    String password;
    long family_id;
    boolean parent;
    double balance;

    public User(Long id, String firstName, String lastName, String userName, String password, Long family_id, boolean parent, double balance) {
        this.id = id;
        this.first_name = firstName;
        this.last_name = lastName;
        this.user_name = userName;
        this.password = password;
        this.family_id = family_id;
        this.parent = parent;
        this.balance = balance;
    }

    //Constructor for log in.
    public User(String userName, String password) {
        this.user_name = userName;
        this.password = password;
        id = 0;
        first_name = null;
        last_name = null;
        family_id = 0;
        balance = 0;
        parent = false;
    }

    //Constructor for registration of family
    public User(String first_name, String last_name, String user_name, String password) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.user_name = user_name;
        this.password = password;
        id = 0;
        family_id = 0;
        parent = true;



    }

    //Constructor for registration of user
    public User(String firstName, String userName, String password, String last_name, long family_id) {
        this.first_name = firstName;
        this.family_id = family_id;
        this.user_name = userName;
        this.password = password;
        this.last_name = last_name;
        id = 0;
        parent = false;
    }

    public void setFirstName(String firstName) {
        this.first_name = firstName;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setFamily_id(Long family_id) {
        this.family_id = family_id;
    }

    public void setParent(boolean parent) {
        this.parent = parent;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getFirstName() {
        return first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public String getUser_name() {
        return user_name;
    }

    public String getPassword() {
        return password;
    }

    public Long getId() {
        return id;
    }

    public Long getFamily_id() {
        return family_id;
    }

    public boolean isParent() {
        return parent;
    }

    @Override
    public String toString() {
        return first_name;
    }
}
