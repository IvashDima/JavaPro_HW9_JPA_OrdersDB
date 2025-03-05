package org.example;

import org.example.dao.ClientDAO;
import org.example.dao.OrderDAO;

import static org.example.src.Enum.em;
import static org.example.src.Enum.emf;
import javax.persistence.Persistence;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ClientDAO clientDAO = new ClientDAO();
        OrderDAO orderDAO = new OrderDAO();
        Scanner sc = new Scanner(System.in);
        try{
            emf = Persistence.createEntityManagerFactory("JPATest");
            em = emf.createEntityManager();
            try{
                while (true) {
                    System.out.println("1: add client");
                    System.out.println("2: add random clients");
                    System.out.println("3: view clients");
                    System.out.println("4: add order");  //delete client
                    System.out.println("5: view orders");  //change client
                    System.out.println("6: ");
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
                            orderDAO.addOrder(sc);
//                            deleteClient(sc);
                            break;
                        case "5":
                            orderDAO.viewOrders();
//                            changeClient(sc);
                            break;
                        case "6":

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

}