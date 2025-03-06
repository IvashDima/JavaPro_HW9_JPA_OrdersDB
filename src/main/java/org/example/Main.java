package org.example;

import org.example.dao.ClientDAO;
import org.example.dao.OrderDAO;
import org.example.dao.ProductDAO;
import org.example.dao.ProductInOrderDAO;

import static org.example.src.Enum.em;
import static org.example.src.Enum.emf;
import javax.persistence.Persistence;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ClientDAO clientDAO = new ClientDAO();
        OrderDAO orderDAO = new OrderDAO();
        ProductDAO productDAO = new ProductDAO();
        ProductInOrderDAO productInOrderDAO = new ProductInOrderDAO();

        Scanner sc = new Scanner(System.in);
        try{
            emf = Persistence.createEntityManagerFactory("JPATest");
            em = emf.createEntityManager();
            try{
                while (true) {
                    System.out.println("1: add client");
                    System.out.println("2: add random clients");
                    System.out.println("3: view clients");
                    System.out.println("4: add product");
                    System.out.println("5: add random products");
                    System.out.println("6: view products");
                    System.out.println("7: add order");
                    System.out.println("8: view orders");
                    System.out.print("-> ");

                    String s = sc.nextLine();
                    switch (s) {
                        case "1":
                            clientDAO.addClient(sc);
                            break;
                        case "2":
                            clientDAO.insertRandomClients(sc);
                            break;
                        case "3":
                            clientDAO.viewClients();
                            break;
                        case "4":
                            productDAO.addProduct(sc);
                            break;
                        case "5":
                            productDAO.insertRandomProducts(sc);
                            break;
                        case "6":
                            productDAO.viewProducts();
                            break;
                        case "7":
                            orderDAO.addOrder(sc);
                            break;
                        case "8":
                            orderDAO.viewOrders(); //productInOrderDAO
                            productInOrderDAO.viewProductsInOrder();
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
            System.out.println("End of program!");
        }
    }

}