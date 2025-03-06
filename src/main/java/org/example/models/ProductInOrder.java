package org.example.models;

import javax.persistence.*;

@Entity
@Table(name = "ProductsInOrder")
public class ProductInOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;
    @Column(nullable = false)
    private int count;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
    public ProductInOrder() {}

    public ProductInOrder(int count) {
        this.count = count;
    }

    public long getId() {
        return id;
    }
    public int getCount() {
        return count;
    }
    public void setCount(int count) {
        this.count = count;
    }
    public Order getOrder() {
        return order;
    }
    public void setOrder(Order order) {
        this.order = order;
    }
    public Product getProduct() {
        return product;
    }
    public void setProduct(Product product) {
        this.product = product;
    }
    @Override
    public String toString(){
        return "Product{id="+id+", " +
                "count="+count+", " +
                "order="+order+", " +
                "product="+product+"}";
    }
}