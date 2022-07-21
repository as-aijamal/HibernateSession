package org.example;

import org.example.config.Config;
import org.example.entity.User;
import org.hibernate.HibernateException;
import org.hibernate.Session;

import java.util.List;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {

        saveUser(new User("Aijamal", "Asangazieva", 20));


//        System.out.println(getById(1L));
//        System.out.println(getAllUsers());
////        updateUser(1L,"Datka","Mamatzhanova",20);
//        deleteUser(1L);
//        deleteAllUser();
    }


    //~~~~~~~~~~~~~~~~~~  CRUD  ~~~~~~~~~~~~~~~~~~~~
    //CREATE = create, save

    public static void saveUser(User user) {
        try {
            Session session = Config.getSession().openSession();
            session.beginTransaction();
            session.save(user);
            session.getTransaction().commit();
            session.close();
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
        }
    }

    //READ = getById, getAll

    public static User getById(Long id) {

        try {
            Session session = Config.getSession().openSession();
            session.beginTransaction();
            User user = session.get(User.class, id);
            session.getTransaction().commit();
            session.close();
            return user;
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
            return null;
        }

    }

    public static List<User> getAllUsers() {
        try {
            Session session = Config.getSession().openSession();
            session.beginTransaction();
            List<User> users = session.createQuery("from User").getResultList();
            session.getTransaction().commit();
            session.close();
            return users;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    //UPDATE = update

    public static User updateUser(Long id, String name, String lastName, int age) {
        try {
            Session session = Config.getSession().openSession();
            session.beginTransaction();
            User user = session.get(User.class, id);
            user.setId(id);
            user.setFirstName(name);
            user.setLastName(lastName);
            user.setAge(age);
            session.getTransaction().commit();
            session.close();
            return user;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    //DELETE = deleteById,deleteAll,dropTable

    public static void deleteUser(Long id){
        try{
            Session session = Config.getSession().openSession();
            session.beginTransaction();
            User user=session.get(User.class,id);
            session.delete(user);
            session.getTransaction().commit();
            session.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void deleteAllUser(){
        try{
            Session session = Config.getSession().openSession();
            session.beginTransaction();
            session.createQuery("delete from User").executeUpdate();
            session.getTransaction().commit();
            session.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }





}