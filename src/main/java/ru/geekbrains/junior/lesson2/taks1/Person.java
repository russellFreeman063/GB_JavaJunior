package ru.geekbrains.junior.lesson2.taks1;

public class Person {

    public String name;
    private final int age;
    public Person() {
        name = "Name";
        age = 25;
    }

    public void displayInfo(){
        System.out.printf("Имя: %s; Возраст: %d\n%n", name, age);
    }

}
