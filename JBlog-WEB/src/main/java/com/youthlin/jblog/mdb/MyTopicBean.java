package com.youthlin.jblog.mdb;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**
 * Created by lin on 2016-09-12-012.
 * Messsage Driven Bean
 */
@MessageDriven(activationConfig = {
        @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Topic"),
        @ActivationConfigProperty(propertyName = "destination", propertyValue = "jms/topic/MyTopic"),
        @ActivationConfigProperty(propertyName = "subscriptionDurability", propertyValue = "Durable"),
        @ActivationConfigProperty(propertyName = "subscriptionName", propertyValue = "myTopic"),
        @ActivationConfigProperty(propertyName = "clientID", propertyValue = "consumer0"),
})
public class MyTopicBean implements MessageListener {
    private final Logger log = LoggerFactory.getLogger(MyTopicBean.class);

    @Override
    public void onMessage(Message message) {
        String text;
        try {
            if (message instanceof TextMessage) {
                text = ((TextMessage) message).getText();
            } else {
                text = message.toString();
            }
            log.debug("message = {}", text);
        } catch (JMSException e) {
            e.printStackTrace();
            log.debug("exception occurred");
        }
    }
}
