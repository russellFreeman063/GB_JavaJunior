package ru.geekbrains.junior.lesson2.taks1;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Program {

    /**
     * Задача 1: Основы Reflection API
     * ===============================
     * <p>
     * Получите информацию о классе "Person" с использованием Reflection API:
     * выведите на экран все поля и методы класса.
     * Создайте экземпляр класса "Person" с использованием Reflection API,
     * установите значения полей и вызовите методы.
     * Реализуйте обработку исключений для обеспечения корректного взаимодействия
     * с Reflection API.
     */

    public static void main(String[] args) throws ClassNotFoundException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchFieldException, NoSuchMethodException {

        Class<?> personClass = Person.class;
        Class<?> personClass2 = Class.forName("ru.geekbrains.junior.lesson2.taks1.Person");
        //Person person1 = new Person();
        //Class<?> personClass3 = person1.getClass();

        // Получить список всех полей
        Field[] fields = personClass.getDeclaredFields();
        for (Field field : fields){
            System.out.println("Поле: " + field.getName());
        }

        // Получить список всех конструкторов
        Constructor[] constructors
                = personClass.getConstructors();

        Object personInstance = constructors[0].newInstance(null);

        //Устанавливаем значения полей
        Field nameField = personClass.getDeclaredField("name");
        nameField.set(personInstance, "Alice");

        Field ageField = personClass.getDeclaredField("age");
        ageField.setAccessible(true); // Разрешаем доступ к закрытому полю
        ageField.set(personInstance, 30);

        // Вызов метода
        Method displayInfoMethod = personClass.getDeclaredMethod("displayInfo");
        displayInfoMethod.invoke(personInstance);
    }

}
