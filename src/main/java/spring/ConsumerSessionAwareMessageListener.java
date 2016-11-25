package spring;

import org.springframework.jms.listener.SessionAwareMessageListener;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

/**
 * Created by max.lu on 2016/3/18.
 */
public class ConsumerSessionAwareMessageListener implements SessionAwareMessageListener {

    Destination destination;

    @Override
    public void onMessage(Message message, Session session) throws JMSException {
        TextMessage textMessage = (TextMessage) message;
        System.out.println("receive message:" + textMessage.getText());
        MessageProducer producer = session.createProducer(destination);
        producer.send(session.createTextMessage("replay message"));
    }

    public Destination getDestination() {
        return destination;
    }

    public void setDestination(Destination destination) {
        this.destination = destination;
    }
}
