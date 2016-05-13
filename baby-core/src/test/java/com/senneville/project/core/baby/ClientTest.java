package com.senneville.project.core.baby;

import static org.junit.Assert.assertTrue;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
// OLD @ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = TestJpaConf.class)
@SpringApplicationConfiguration(TestJpaConf.class)
public class ClientTest {

    @Inject
    private ClientCrudRepository clientCrudRepository;
    
    @PersistenceContext
    private EntityManager entityManager;

    @Test
    public void test1() {
        Client client = new Client();
        client.setFirstName("x");
        client.setLastName("y");
        client = this.clientCrudRepository.save(client);
        
        client = this.clientCrudRepository.findOne(client.getId());
        assertTrue(client != null);
    }
    
}
