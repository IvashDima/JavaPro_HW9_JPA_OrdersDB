package org.example.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private double price;
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<ProductInOrder> items = new ArrayList<>();
    public Product() {}

    public Product(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public void addProductInOrder(ProductInOrder item) {
        if ( ! items.contains(item)) {
            items.add(item);
            item.setProduct(this);
        }
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
    @Override
    public String toString(){
        return "Product{id="+id+", " +
                "name='"+name+"', " +
                "price="+price+"}";
    }
}