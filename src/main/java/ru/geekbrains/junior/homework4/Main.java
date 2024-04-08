package ru.geekbrains.junior.homework4;

import java.util.List;

public class Main {
    public static void main(String[] args) {

        CourseHibernate hibernate = new CourseHibernate();

        List<Course> courses = List.of(
                    new Course("Математика", 50),
                    new Course("Английский", 40),
                    new Course("Физика", 45),
                    new Course("Химия", 50),
                    new Course("История", 40));

        System.out.println("Добавление данных:");
        for (Course course: courses) {
                hibernate.saveData(course);
        }

        System.out.println("Чтение данных по ID:");
        hibernate.readDataById(1);

        System.out.println("Чтение всех данных:");
        hibernate.readAllData();

        System.out.println("Обновление данных:");
        hibernate.updateData(1, "Геометрия", 40);

        System.out.println("Удаление данных:");
        hibernate.deleteData(2);

    }
}
