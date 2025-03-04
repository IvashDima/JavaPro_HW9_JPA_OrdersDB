package org.example.models;

import javax.persistence.*;

@Entity
@Table(name = "Orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "myid")
    private long id;
    @Column(nullable = false)
    private int client_id;
    public Order() {}

    public Order(int client_id) {
        this.client_id = client_id;
    }

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public int getClientId() {
        return client_id;
    }
    public void setClientId(int client_id) {
        this.client_id = client_id;
    }
    @Override
    public String toString(){
        return "Order{id="+id+", " +
                "client_id="+client_id+"}";
    }
}
