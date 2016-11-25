package spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import javax.jms.Destination;

/**
 * Created by max.lu on 2016/3/18.
 */
@Component
public class Producer {
    @Autowired
    JmsTemplate jmsTemplate;

    public void send(Destination destination, String message) {
        System.out.println("send message...");
        jmsTemplate.send(destination, session -> session.createTextMessage(message));
    }

}
