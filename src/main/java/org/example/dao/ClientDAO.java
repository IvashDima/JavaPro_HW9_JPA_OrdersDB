package org.example.dao;

import org.example.models.Client;
import static org.example.src.Enum.em;

import javax.persistence.Query;
import java.util.List;
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
            Client c = new Client(name, sPhone);
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
}
