package com.example.objects;

public class User {
    Long id;
    String firstName;
    String lastName;
    String userName;
    String password;
    Long familyId;
    boolean parent;
    double balance;

    public User(Long id, String firstName, String lastName, String userName, String password, Long familyId, boolean parent, double balance) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.password = password;
        this.familyId = familyId;
        this.parent = parent;
        this.balance = balance;
    }

    public User(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public User(String firstName, String lastName, String userName, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.password = password;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setFamilyId(Long familyId) {
        this.familyId = familyId;
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
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public Long getId() {
        return id;
    }

    public Long getFamilyId() {
        return familyId;
    }

    public boolean isParent() {
        return parent;
    }
}
