package replay;

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
public class MessageSendReceiveAndReply {
    public static void main(String[] args) throws JMSException {
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory("vm://localhost");
        Connection connection = connectionFactory.createConnection();
        connection.start();
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

        Queue queue = session.createQueue("queueTest");
        Queue replayQueue = session.createQueue("replayQueueTest");

        MessageProducer producer = session.createProducer(queue);
        TextMessage textMessage = session.createTextMessage("hi");
        producer.send(textMessage);


        MessageConsumer consumer1 = session.createConsumer(queue);
        consumer1.setMessageListener(msg -> {
            try {
                System.out.println("consumer1 get message :" + ((TextMessage) msg).getText());
                MessageProducer replayProducer = session.createProducer(replayQueue);
                replayProducer.send(session.createTextMessage("hello"));
            } catch (JMSException e) {
                e.printStackTrace();
            }
        });

        MessageConsumer consumer2 = session.createConsumer(replayQueue);
        consumer2.setMessageListener(msg -> {
            try {
                System.out.println("consumer2 get replay message:" + ((TextMessage) msg).getText());
            } catch (JMSException e) {
                e.printStackTrace();
            }
        });


    }
}
