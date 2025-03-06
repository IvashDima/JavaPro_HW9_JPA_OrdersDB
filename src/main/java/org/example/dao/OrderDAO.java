package org.example.dao;

import org.example.models.Client;
import org.example.models.Order;
import org.example.models.Product;
import org.example.models.ProductInOrder;

import javax.persistence.Query;
import java.util.List;
import java.util.Scanner;

import static org.example.src.Enum.em;

public class OrderDAO {
    public void addOrder(Scanner sc) {
        System.out.print("Enter client id: ");
        String sClientId = sc.nextLine();
        long clientId = Long.parseLong(sClientId);
        System.out.print("Enter product id: ");
        String sProduct_id = sc.nextLine();
        long productId = Long.parseLong(sProduct_id);
        System.out.print("Enter count items: ");
        String sItemCount = sc.nextLine();
        int itemCount = Integer.parseInt(sItemCount);

        em.getTransaction().begin();
        Client client = em.find(Client.class,clientId);
        Product product = em.find(Product.class,productId);
        try {
            Order order = new Order(client);
            ProductInOrder item = new ProductInOrder(itemCount);

            product.addProductInOrder(item);
            order.addProductInOrder(item);

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
