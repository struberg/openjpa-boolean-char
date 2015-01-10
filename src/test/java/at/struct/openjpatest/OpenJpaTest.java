package at.struct.openjpatest;

import java.util.List;

import at.struct.openjpatest.entities.Customer;
import junit.framework.Assert;
import org.testng.annotations.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class OpenJpaTest {

    @Test
    public void testOpenJpa() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("MYAPP");
        EntityManager em = emf.createEntityManager();

        List<Customer> resultList = em.createQuery("select c from Customer AS c where c.active = :active", Customer.class).setParameter("active", true).getResultList();
        Assert.assertNotNull(resultList);
    }
}
