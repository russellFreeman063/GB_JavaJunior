package ru.geekbrains.junior.homework2.task1;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        List<Animal> animals = new ArrayList<>();
        animals.add(new Cat("Bob", 5));
        animals.add(new Dog("Carl", 6));

        for (Animal animal : animals) {
            System.out.println("-------------------");
            System.out.println(animal);

            Field[] fields = animal.getClass().getDeclaredFields();
            System.out.print("Fields: ");
            for (Field field : fields){
                System.out.print(field.getName() + "; ");
            }

            Method[] methods = animal.getClass().getDeclaredMethods();
            System.out.println("\nMethods:");
            for (Method method: methods) {
                System.out.println(method);
            }

            try {
                Method makeSoundMethod = animal.getClass().getMethod("makeSound");
                makeSoundMethod.invoke(animal);
            } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException ignored) {
                System.out.println("This animal cant make sound yet");
            }
        }

    }
}
