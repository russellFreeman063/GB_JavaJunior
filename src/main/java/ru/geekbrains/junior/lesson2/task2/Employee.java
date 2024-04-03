package ru.geekbrains.junior.lesson2.task2;

import java.util.UUID;


@Table(name = "employees")
public class Employee {

    @Column(name = "id", primaryKey = true)
    private UUID id;


    @Column(name = "username")
    private String username;

    @Column(name = "email")
    private String email;

    public Employee(String username, String email) {
        this.id = UUID.randomUUID();
        this.username = username;
        this.email = email;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public UUID getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }
}
