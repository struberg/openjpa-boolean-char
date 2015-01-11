package at.struct.openjpatest;

import java.util.List;

import at.struct.openjpatest.entities.Customer;
import junit.framework.Assert;
import org.testng.annotations.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

public class OpenJpaTest {

    @Test
    public void testOpenJpa() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("MYAPP");
        EntityManager em = emf.createEntityManager();

        TypedQuery<Customer> query = em.createQuery("select c from Customer AS c where c.active = :active and c.specialCustomer = :special", Customer.class);
        query.setParameter("active", true);
        query.setParameter("special", false);

        List<Customer> resultList = query.getResultList();
        Assert.assertNotNull(resultList);

        // this test might pass but if you look at the log then you will see that the select uses (int) 1
        // output in the log:... [params=(int) 1, (int) 0]
        // but it should finally be [params= "1", "0"]
    }
}
