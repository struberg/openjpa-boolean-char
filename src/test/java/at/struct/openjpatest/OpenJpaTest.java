package at.struct.openjpatest;

import java.util.List;
import java.util.logging.Logger;

import at.struct.openjpatest.entities.Customer;
import junit.framework.Assert;
import org.testng.annotations.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

public class OpenJpaTest {
    private static final Logger log = Logger.getLogger(OpenJpaTest.class.getName());

    @Test
    public void testOpenJpa() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("MYAPP");
        EntityManager em = emf.createEntityManager();

        // first get rid of any possible leftover
        em.getTransaction().begin();
        em.createQuery("delete from Customer").executeUpdate();
        em.getTransaction().commit();

        log.info("select customer parameters true, false");
        List<Customer> resultList = getCustomers(em, true, false);

        Assert.assertNotNull(resultList);
        Assert.assertEquals(0, resultList.size());

        // this test might pass but if you look at the log then you will see that the select uses (int) 1
        // output in the log:... [params=(int) 1, (int) 0]
        // but it should finally be [params= "1", "0"]

        createCustomer(em, "Karl", true, false);
        createCustomer(em, "Hans", true, false);
        createCustomer(em, "Nils", true, true);

        // our ladies are all inactive
        createCustomer(em, "Nina", false, false);
        createCustomer(em, "Ilsa", false, true);

        log.info("2nd select customer parameters true, false");
        resultList = getCustomers(em, true, false);
        Assert.assertNotNull(resultList);
        Assert.assertEquals(2, resultList.size());


        // now up to the fixed list of boolean parameters inside the JPQL query
        log.info("select customer with fixed JPQL parameters true, false");
        TypedQuery<Customer> query = em.createQuery("select c from Customer AS c where c.active = true and c.specialCustomer = false", Customer.class);
        resultList = query.getResultList();
        Assert.assertNotNull(resultList);
        Assert.assertEquals(2, resultList.size());

    }

    private List<Customer> getCustomers(EntityManager em, boolean active, boolean special) {
        TypedQuery<Customer> query = em.createQuery("select c from Customer AS c where c.active = :active and c.specialCustomer = :special", Customer.class);
        query.setParameter("active", active);
        query.setParameter("special", special);

        return query.getResultList();
    }

    private Customer createCustomer(EntityManager em, String name, boolean active, boolean special) {
        em.getTransaction().begin();
        Customer c = new Customer();
        c.setName(name);
        c.setActive(active);
        c.setSpecialCustomer(special);
        em.persist(c);
        em.getTransaction().commit();
        return c;
    }
}
