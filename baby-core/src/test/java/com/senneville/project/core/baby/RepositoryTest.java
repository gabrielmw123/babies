package com.senneville.project.core.baby;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.inject.Inject;

import static org.junit.Assert.assertTrue;

/**
 * Created by moralesg on 05/05/16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {RepositoryConfiguration.class})
public class RepositoryTest {

    @Inject
    private ClientCrudRepository clientCrudRepository;

    @Test
    public void test() {
        Client client = new Client();
        client.setFirstName("x");
        client.setLastName("y");
        client = this.clientCrudRepository.save(client);

        client = this.clientCrudRepository.findOne(client.getId());
        assertTrue(client != null);
    }
}
