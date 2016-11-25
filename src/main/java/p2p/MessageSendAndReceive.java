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
public class MessageSendAndReceive {

    public static void main(String[] args) throws JMSException {
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory("vm://localhost");
        Connection connection = connectionFactory.createConnection();
        connection.start();

        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Queue queue = session.createQueue("testQueue");
        TextMessage message = session.createTextMessage("Hello JMS!");
        MessageProducer producer = session.createProducer(queue);
        producer.send(message);
        System.out.println("producer send message...");

        MessageConsumer consumer = session.createConsumer(queue);
        consumer.setMessageListener(msg -> {
            try {
                System.out.println("consumer receive message...");
                System.out.println(((TextMessage) msg).getText());
            } catch (JMSException e) {
                e.printStackTrace();
            }
        });
    }
}
