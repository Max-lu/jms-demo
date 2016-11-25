package p2p;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.Connection;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;

/**
 * Created by max.lu on 2016/3/18.
 */
public class QueueTest {
    public static void main(String[] args) throws JMSException {
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory("vm://localhost");
        Connection connection = connectionFactory.createConnection();
        connection.start();
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Queue queue = session.createQueue("queueTest");

        MessageConsumer consumer1 = session.createConsumer(queue);
        consumer1.setMessageListener(msg -> {
            try {
                System.out.println("consumer1:" + ((TextMessage) msg).getText());
            } catch (JMSException e) {
                e.printStackTrace();
            }
        });

        MessageConsumer consumer2 = session.createConsumer(queue);
        consumer2.setMessageListener(msg -> {
            try {
                System.out.println("consumer2:" + ((TextMessage) msg).getText());
            } catch (JMSException e) {
                e.printStackTrace();
            }
        });

        MessageProducer producer = session.createProducer(queue);
        for (int i = 0; i < 10; i++) {
            TextMessage textMessage = session.createTextMessage("message" + i);
            producer.send(textMessage);
        }
    }
}
