package mk.finki.ukim.mk.lab3.model;

import java.io.Serializable;

public class UserFullname implements Serializable {
    private String name;
    private String surname;

    public UserFullname(String name, String surname) {
        this.name=name;
        this.surname=surname;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public UserFullname() {
    }
}
