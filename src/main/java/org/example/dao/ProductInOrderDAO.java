package org.example.dao;

import org.example.models.Order;
import org.example.models.ProductInOrder;

import javax.persistence.Query;
import java.util.List;

import static org.example.src.Enum.em;

public class ProductInOrderDAO {

    public void viewProductsInOrder() {
        Query query = em.createQuery("SELECT p FROM ProductInOrder p ", ProductInOrder.class);
        List<ProductInOrder> list = (List<ProductInOrder>) query.getResultList();

        for (ProductInOrder p : list)
            System.out.println(p);
    }
}
