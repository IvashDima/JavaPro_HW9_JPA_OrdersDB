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
    private int number;
//    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

//    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
//    private List<ProductInOrder> items = new ArrayList<>();
    public Order() {}

    public Order(int number, Client client) {
        this.client = client;
        this.number = number;
    }

    public long getId() {
        return id;
    }
    public int getNumber() {
        return number;
    }
    public void setNumber(int number) {
        this.number = number;
    }
    public Client getClient() {
        return client;
    }
    public void setClient(Client client) {
        this.client = client;
    }
    @Override
    public String toString(){
        return "Order{id="+id+", " +
                "number="+number+"}";
    }
}
