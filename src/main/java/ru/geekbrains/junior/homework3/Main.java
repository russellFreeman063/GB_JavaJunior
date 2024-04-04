package ru.geekbrains.junior.homework3;

public class Main {

    public static void main(String[] args) {

        Student student = new Student("Alex", 18, 4);
        StudentService studentService = new StudentService();

        studentService.serialize(student, "student.json");
        studentService.serialize(student, "student.xml");
        studentService.serialize(student, "student.bin");

        Student studentJson = studentService.deserialize("student.json");
        Student studentXml = studentService.deserialize("student.xml");
        Student studentBin = studentService.deserialize("student.bin");

        System.out.println("Json: " + studentJson);
        System.out.println("Xml: " + studentXml);
        System.out.println("Bin: " + studentBin);

    }

}
