package org.example.dao;

import org.example.models.Client;
import org.example.models.Order;

import javax.persistence.Query;
import java.util.List;
import java.util.Scanner;

import static org.example.src.Enum.em;

public class OrderDAO {
    public void addOrder(Scanner sc) {
//        System.out.print("Enter client id: ");
//        String name = sc.nextLine();
        System.out.print("Enter client id: ");
        String sClient_id = sc.nextLine();
        long clientId = Long.parseLong(sClient_id);

        em.getTransaction().begin();
        Client client = em.find(Client.class,clientId);
        try {
            Order order = new Order(client);
            em.persist(order);
            em.getTransaction().commit();

            System.out.println("Order was added, ID = " + order.getId()); // HQL == JPQL
        } catch (Exception ex) {
            em.getTransaction().rollback();
        }
    }
    public void viewOrders() {
        Query query = em.createQuery("SELECT o FROM Order o", Order.class);
        List<Order> list = (List<Order>) query.getResultList();

        for (Order order : list)
            System.out.println(order);
    }
}
