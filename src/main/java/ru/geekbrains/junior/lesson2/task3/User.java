package ru.geekbrains.junior.lesson2.task3;

public class User {

    @NotNull(message = "Имя должно быть определено")
    @MaxLength(message = "Имя должно быть длиной не более 200 символов")
    private final String name;

    @MinLength(value = 6, message = "Пароль должен быть длиной не менее 6 символов")
    private final String password;

    @Range(min = 18, max = 100, message = "Возраст должен быть от 18 до 100")
    private final int age;

    private User(String name, String password, int age) {
        this.name = name;
        this.password = password;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public int getAge() {
        return age;
    }

}
