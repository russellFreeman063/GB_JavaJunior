package ru.geekbrains.junior.homework2.task1;

import java.awt.*;
import java.util.Random;

public class Dog extends Animal{

    private final String[] colorVariable = new String[]{"White", "Black", "Gray", "Orange"};
    private String color;
    private final String[] statusVariable = new String[]{"Sleep", "Play", "Eat", "Drink"};
    private String status;
    private static final Random random = new Random();
    private final List commands = new List();

    public Dog(String name, int age) {
        super(name, age);
        setRandomColor();
        setRandomStatus();
    }

    public Dog(String name, int age, String color) {
        super(name, age);
        this.color = color;
        setRandomStatus();
    }


    public String getColor() {
        return color;
    }

    private void setColor(String color) {
        this.color = color;
    }

    private void setRandomColor() {
        this.color = colorVariable[random.nextInt(colorVariable.length)];
    }

    public String getStatus() {
        return status;
    }

    private void setStatus(String status) {
        this.status = status;
    }

    private void setRandomStatus() {
        this.status = statusVariable[random.nextInt(statusVariable.length)];
    }

    public List getCommands() {
        return commands;
    }

    public void addCommand(String command) {
        commands.add(command);
    }

    @Override
    public String toString() {
        return  getClass().getSimpleName() + ": "
                + getName() +
                " , age = " + getAge() +
                " , color = " + color +
                " , status = " + status;
    }
}
