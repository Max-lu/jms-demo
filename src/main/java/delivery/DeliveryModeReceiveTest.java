package delivery;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;

import javax.jms.Connection;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;

/**
 * Created by max.lu on 2016/3/18.
 */
public class DeliveryModeReceiveTest {
    public static void main(String[] args) throws JMSException {
        ActiveMQConnectionFactory factory=new ActiveMQConnectionFactory("vm://localhost");
        Connection connection=factory.createConnection();
        connection.start();

        Queue queue=new ActiveMQQueue("testQueue");
        Session session=connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        //创建消息的接收者，来接受DeliveryModeSendTest中发送的消息
        MessageConsumer consumer=session.createConsumer(queue);
        consumer.setMessageListener(m -> {
            try {
                System.out.println("Consumer get: "+((TextMessage)m).getText());
            } catch (JMSException e) {
                e.printStackTrace();
            }
        });
    }
}
