package ru.geekbrains.junior.homework3;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import java.io.*;

public class StudentService {

    private static final ObjectMapper objectMapper = new ObjectMapper();
    private static final XmlMapper xmlMapper = new XmlMapper();

    static {
        objectMapper.configure(SerializationFeature.INDENT_OUTPUT, true);
        xmlMapper.configure(SerializationFeature.INDENT_OUTPUT, true);
    }

    public void serialize(Student student, String fileName) {
        File file = new File(fileName);
        try {
            if (fileName.endsWith(".json")) {
                objectMapper.writeValue(file, student);
                System.out.println("Объект Student сериализован в файл " + fileName);
            } else if (fileName.endsWith(".bin")) {
                ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(fileName));
                outputStream.writeObject(student);
                outputStream.close();
                System.out.println("Объект Student сериализован в файл " + fileName);
            } else if (fileName.endsWith(".xml")) {
                xmlMapper.writeValue(file, student);
                System.out.println("Объект Student сериализован в файл " + fileName);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Student deserialize(String fileName) {
        File file = new File(fileName);
        if (file.exists()) {
            try {
                if (fileName.endsWith(".json")) {
                    try {
                        return objectMapper.readValue(file, Student.class);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else if (fileName.endsWith(".bin")) {
                    try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
                        return (Student) ois.readObject();
                    }
                } else if (fileName.endsWith(".xml")) {
                    return xmlMapper.readValue(file, Student.class);
                }
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Файл " + fileName + " не существует.");
        }
        return null;
    }

}
