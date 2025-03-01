package org.example;

import org.example.models.Client;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;
import java.util.Scanner;

public class Main {
    static EntityManagerFactory emf;
    static EntityManager em;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        try{
            emf = Persistence.createEntityManagerFactory("JPATest");
            em = emf.createEntityManager();
            try{
                while (true) {
                    System.out.println("1: add client");
                    System.out.println("2: add random clients");
                    System.out.println("3: delete client");
                    System.out.println("4: change client");
                    System.out.println("5: view clients");
                    System.out.print("-> ");

                    String s = sc.nextLine();
                    switch (s) {
                        case "1":
                            addClient(sc);
                            break;
                        case "2":
//                            insertRandomClients(sc);
                            break;
                        case "3":
//                            deleteClient(sc);
                            break;
                        case "4":
//                            changeClient(sc);
                            break;
                        case "5":
                            viewClients();
                            break;
                        default:
                            return;
                    }
                }
            } finally {
                sc.close();
                em.close();
                emf.close();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("Hello world!");
        }
    }
    private static void addClient(Scanner sc) {
        System.out.print("Enter client name: ");
        String name = sc.nextLine();
//        System.out.print("Enter client age: ");
//        String sAge = sc.nextLine();
//        int age = Integer.parseInt(sAge);

        em.getTransaction().begin();
        try {
            Client c = new Client(name);
            em.persist(c);
            em.getTransaction().commit();

            System.out.println(c.getId()); // HQL == JPQL
        } catch (Exception ex) {
            em.getTransaction().rollback();
        }
    }
    private static void viewClients() {
        Query query = em.createQuery("SELECT c FROM Client c", Client.class);
        List<Client> list = (List<Client>) query.getResultList();

        for (Client c : list)
            System.out.println(c);
    }
}