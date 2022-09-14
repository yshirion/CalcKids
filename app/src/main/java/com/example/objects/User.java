package com.example.objects;

import java.util.UUID;

public class User {
    String firstName,lastName, userName, password;
    UUID id, familyId;
    boolean parent;

    public User(String first, String last, String user, String _password, UUID family){
        firstName = first;
        lastName = last;
        userName = user;
        password = _password;
        familyId = family;
        parent = false;
    }
    public User(String user, String _password){
        userName = user;
        password = _password;
    }
    public User(String first, String last, String user, String _password){
        firstName = first;
        lastName = last;
        userName = user;
        password = _password;
        parent = true;
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

    public UUID getId() {
        return id;
    }

    public UUID getFamilyId() {
        return familyId;
    }

    public boolean isParent() {
        return parent;
    }
}
