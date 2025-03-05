package org.example.dao;

import org.example.models.Product;

import javax.persistence.Query;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import static org.example.src.Enum.em;

public class ProductDAO {
    public void addProduct(Scanner sc) {
        System.out.print("Enter product name: ");
        String name = sc.nextLine();
        System.out.print("Enter product price: ");
        String sPrice = sc.nextLine();
        int price = Integer.parseInt(sPrice);

        em.getTransaction().begin();
        try {
            Product c = new Product(name, price);
            em.persist(c);
            em.getTransaction().commit();

            System.out.println("Product was added, ID = " + c.getId()); // HQL == JPQL
        } catch (Exception ex) {
            em.getTransaction().rollback();
        }
    }
    public void viewProducts() {
        Query query = em.createQuery("SELECT p FROM Product p", Product.class);
        List<Product> list = (List<Product>) query.getResultList();

        for (Product p : list)
            System.out.println(p);
    }
    public void insertRandomProducts(Scanner sc) {
        System.out.print("Enter Products count: ");
        String sCount = sc.nextLine();
        int count = Integer.parseInt(sCount);

        em.getTransaction().begin();
        try {
            for (int i = 0; i < count; i++) {
                Product p = new Product(randomName(), RND.nextInt(10,1000));
                em.persist(p);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            em.getTransaction().rollback();
        }
    }
    static final String[] NAMES = {"TV", "Phone", "CD player", "Laptop", "Headset"};
    static final Random RND = new Random();

    static String randomName() {
        return NAMES[RND.nextInt(NAMES.length)];
    }
}
