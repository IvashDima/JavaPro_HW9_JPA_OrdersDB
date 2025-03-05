package org.example.tests;

import org.example.models.Client;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.concurrent.Callable;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ClientTest extends BaseTest {

    private Client saveTestClient(final String name, final int phone) {
        Callable<Client> c = new Callable<Client>() {
            public Client call() throws Exception {
                Client client = new Client(name, phone);
                em.persist(client);
                return client;
            }
        };
        return performTransaction(c);
    }
    @Test
    public void testPersistAndFind() {
        Client client = saveTestClient("Dmytro", 0661234567);
        long id = client.getId();
        assertTrue(id > 0);
        // find existing
        Client other = em.find(Client.class, id);
        assertNotNull(other);
        assertEquals(client.getName(), other.getName());
        assertEquals(client.getPhone(), other.getPhone());
        // clear context
        em.clear();
        // entity was already loaded by find()
        other = em.getReference(Client.class, id);
        assertNotNull(other);
        assertEquals(client.getName(), other.getName());
        assertEquals(client.getPhone(), other.getPhone());
    }
    @Test(expected = RuntimeException.class)
    public void testNullable() {
        saveTestClient("Nikolay", 0);
    }

    @Test
    public void testMerge() {
        final Client client = saveTestClient("Ivan", 0501111111);
        long id = client.getId();
        performTransaction(() -> {
            client.setPhone(0502222222);
            return null;
        });
        em.clear();
        Client other = em.find(Client.class, id);
        assertEquals("0507654321", other.getPhone());
    }

    @Test
    public void testRemove() {
        final Client client = saveTestClient("Ivan", 0501111111);
        final long id = client.getId();

        performTransaction(() -> {
            Client other = em.getReference(Client.class, id);
            em.remove(other);
            return null;
        });
        Client another = em.find(Client.class, id);
        assertNull(another);
    }

    @Test
    public void testSelect() {
        performTransaction(() -> {
            for (int i = 0; i < 10; i++) {
                Client client = new Client("Name" + i, 050111111 + i);
                em.persist(client);
            }
            return null;
        });

        List<Client> resultList;

        Query query = em.createQuery("SELECT c FROM Client c WHERE c.phone >= :phone");
        query.setParameter("phone", "050111111");
        resultList = (List<Client>) query.getResultList(); // type cast!!!
        assertEquals(10, resultList.size());

        TypedQuery<Client> otherQuery = em.createQuery(
                "SELECT c FROM Client c WHERE c.phone >= :phone", Client.class);
        otherQuery.setParameter("phone", "050111111");
        resultList = otherQuery.getResultList(); // no type cast
        assertEquals(10, resultList.size());

        TypedQuery<Long> countQuery = em.createQuery(
                "SELECT COUNT(c) FROM Client c WHERE c.phone >= :phone", Long.class);
        countQuery.setParameter("phone", "050111111");
        long count = countQuery.getSingleResult();
        assertEquals(10, count);

        // select properties
//        TypedQuery<CustomClient> propQuery = em
//                .createQuery("SELECT NEW academy.prog.simple.CustomClient(c.name, c.age) FROM Client c WHERE c.id = 1",
//                        CustomClient.class);
//        CustomClient res = propQuery.getSingleResult();
//        assertNotNull(res);

        Query propQuery2 = em
                .createQuery("SELECT c.name, c.phone FROM Client c WHERE c.id = 1");
        Object[] res2 = (Object[]) propQuery2.getSingleResult();

        assertEquals(2, res2.length);
        assertTrue(res2[0] instanceof String);
        assertTrue(res2[1] instanceof Integer);
    }
}
