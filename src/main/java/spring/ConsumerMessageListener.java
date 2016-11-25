package spring;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**
 * Created by max.lu on 2016/3/18.
 */
public class ConsumerMessageListener implements MessageListener {
    @Override
    public void onMessage(Message message) {
        TextMessage textMessage = (TextMessage) message;
        try {
            System.out.println("receive message:" + textMessage.getText());
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
