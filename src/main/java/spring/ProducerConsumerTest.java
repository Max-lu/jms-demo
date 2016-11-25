package spring;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.jms.Destination;


/**
 * Created by max.lu on 2016/3/18.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/spring-config.xml")
public class ProducerConsumerTest {

    @Autowired
    Producer producer;

    @Autowired
    @Qualifier("adapterQueue")
    Destination destination;

    @Test
    public void testSend() {
        for (int i = 0; i < 2; i++) {
            producer.send(destination, "producer message ：" + (i + 1));
        }
    }
}
