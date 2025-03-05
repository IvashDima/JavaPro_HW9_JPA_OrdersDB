package org.example.models;

import javax.persistence.*;

@Entity
@Table(name = "ProductInOrders")
public class ProductInOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;

    @Column(nullable = false)
    private int count;
    public ProductInOrder() {}

//    public Product(String name, int count) {
//        this.name = name;
//        this.count = count;
//    }
//
//    public long getId() {
//        return id;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public double getCount() {
//        return count;
//    }
//
//    public void setCount(int count) {
//        this.count = count;
//    }
//    @Override
//    public String toString(){
//        return "Product{id="+id+", " +
//                "name='"+name+"', " +
//                "count="+count+"}";
//    }
}