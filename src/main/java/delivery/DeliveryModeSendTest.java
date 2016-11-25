package delivery;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;

import javax.jms.Connection;
import javax.jms.DeliveryMode;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;

/**
 * Created by max.lu on 2016/3/18.
 */
public class DeliveryModeSendTest {

    public static void main(String[] args) throws JMSException{
        ActiveMQConnectionFactory factory=new ActiveMQConnectionFactory("vm://localhost");
        Connection connection=factory.createConnection();
        connection.start();

        Queue queue=new ActiveMQQueue("testQueue");
        Session session=connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        MessageProducer producer=session.createProducer(queue);

        //设置消息的DeliveryMode为Persistent
        producer.setDeliveryMode(DeliveryMode.PERSISTENT);
        producer.send(session.createTextMessage("A persistent Message"));

        //设置消息的DeliveryMode为Non-Persistent
        producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
        producer.send(session.createTextMessage("A non persistent Message"));

        System.out.println("Send messages successfully!");
    }
}
