package org.example.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Clients")
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;

    @Column(name = "client_name")
    private String name;

    @Column(nullable = false)
    private int phone;

//    @OneToOne(mappedBy = "client")
//    private Order order;
    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
    private List<Order> orders = new ArrayList<>();

    public Client() {
    }

    public Client(String name, int phone) {
        this.name = name;
        this.phone = phone;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

//    public void addOrder(Order order) {
//        if ( ! orders.contains(order)) {
//            orders.add(order);
//            orders.setClient(this);
//        }
//    }
    public Order getOrder(int index) {
        return orders.get(index);
    }
    public void clearOrders() {
        orders.clear();
    }
    public List<Order> getOrders() {
        return orders;
    }
    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    @Override
    public String toString(){
        return "Client{id="+id+", " +
                "name='"+name+"', " +
                "phone='"+phone+"'" +
                "}";
    }
}
