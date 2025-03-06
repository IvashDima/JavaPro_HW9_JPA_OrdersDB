package org.example.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;
    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;

    @Column(nullable = true)
    private double totalPrice;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<ProductInOrder> items = new ArrayList<>();
    public Order() {}

    public Order(Client client) { //, double totalPrice
        this.client = client;
//        this.totalPrice = totalPrice;
    }

    public void addProductInOrder(ProductInOrder item) {
        if ( ! items.contains(item)) {
            items.add(item);
            item.setOrder(this);
        }
    }
    public long getId() {
        return id;
    }
    public Client getClient() {
        return client;
    }
    public void setClient(Client client) {
        this.client = client;
    }
    public double getTotalPrice() { return totalPrice; }
    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    @Override
    public String toString(){
        return "Order{id="+id+", " +
                "client="+client.getName()+", " +
                "totalPrice="+totalPrice+
                "}";
    }
}
