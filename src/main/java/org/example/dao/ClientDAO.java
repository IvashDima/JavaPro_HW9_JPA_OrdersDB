package org.example.dao;

import org.example.models.Client;
import static org.example.src.Enum.em;

import javax.persistence.Query;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class ClientDAO {

    public void addClient(Scanner sc) {
        System.out.print("Enter client name: ");
        String name = sc.nextLine();
        System.out.print("Enter client phone: ");
        String sPhone = sc.nextLine();
        int phone = Integer.parseInt(sPhone);

        em.getTransaction().begin();
        try {
            Client c = new Client(name, phone);
            em.persist(c);
            em.getTransaction().commit();

            System.out.println("Client was added, ID = " + c.getId()); // HQL == JPQL
        } catch (Exception ex) {
            em.getTransaction().rollback();
        }
    }
    public void viewClients() {
        Query query = em.createQuery("SELECT c FROM Client c", Client.class);
        List<Client> list = (List<Client>) query.getResultList();

        for (Client c : list)
            System.out.println(c);
    }
    public void insertRandomClients(Scanner sc) {
        System.out.print("Enter Clients count: ");
        String sCount = sc.nextLine();
        int count = Integer.parseInt(sCount);

        em.getTransaction().begin();
        try {
            for (int i = 0; i < count; i++) {
                Client c = new Client(randomName(), RND.nextInt(1111111,9999999));
                em.persist(c);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            em.getTransaction().rollback();
        }
    }
    static final String[] NAMES = {"Dima", "Alex", "Ivan", "Petro", "John", "Martin"};
    static final Random RND = new Random();

    static String randomName() {
        return NAMES[RND.nextInt(NAMES.length)];
    }
}
