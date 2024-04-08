package ru.geekbrains.junior.homework4;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import java.util.List;

public class CourseHibernate {

    SessionFactory sessionFactory = new Configuration()
            .configure("homework4/hibernate.cfg.xml")
            .addAnnotatedClass(Course.class)
            .buildSessionFactory();


    public void saveData(Course course) {
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            session.save(course);
            session.getTransaction().commit();
            System.out.println("Данные добавлены");
        }
    }

    public void readDataById(int id) {
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            Course course = session.get(Course.class, id);
            session.getTransaction().commit();
            System.out.println(course.getId() + ". " + course.getTitle() + ": " + course.getDuration());
        }
    }

    public void readAllData() {
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            List<Course> courses;
            courses = session.createQuery("FROM Course").list();
            session.getTransaction().commit();
            for (Course course : courses) {
                System.out.println(course.getId() + ". " + course.getTitle() + ": " + course.getDuration());
            }
        }
    }

    public void updateData(int id, String title, int duration) {
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            Course course = session.get(Course.class, id);
            course.setTitle(title);
            course.setDuration(duration);
            session.update(course);
            session.getTransaction().commit();
            System.out.println("Данные обновлены");
        }
    }

    public void deleteData(int id) {
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            Course course = session.get(Course.class, id);
            session.delete(course);
            session.getTransaction().commit();
            System.out.println("Данные удалены");
        }
    }

}
