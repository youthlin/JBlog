package com.youthlin.jblog.mdb;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by lin on 2016-09-12-012.
 * Messsage Driven Bean
 */
@ManagedBean
@SessionScoped
@MessageDriven(activationConfig = {
        @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Topic"),
        @ActivationConfigProperty(propertyName = "destination", propertyValue = "jms/topic/MyTopic"),
        @ActivationConfigProperty(propertyName = "subscriptionDurability", propertyValue = "Durable"),
        @ActivationConfigProperty(propertyName = "subscriptionName", propertyValue = "myTopic"),
        @ActivationConfigProperty(propertyName = "clientID", propertyValue = "consumer0"),
})
public class MyTopicBean implements MessageListener {
    private final Logger log = LoggerFactory.getLogger(MyTopicBean.class);
    private static final int MAX_COUNT = 5;
    private static int last = 0;
    private static TextMessage[] messages = new TextMessage[MAX_COUNT];
    private boolean showTopic = true;

    @Override
    public void onMessage(Message message) {
        String text;
        try {
            if (message instanceof TextMessage) {
                TextMessage message1 = (TextMessage) message;
                messages[last++] = message1;
                if (last > MAX_COUNT) {
                    last -= MAX_COUNT;
                }
                text = message1.getText();
                //log.debug("message = {},last={},array={}", text, last, Arrays.toString(messages));
            } else {
                text = message.toString();
                log.debug("message.toString = {}", text);
            }
        } catch (JMSException e) {
            e.printStackTrace();
            log.debug("exception occurred");
        }
    }

    public List<TextMessage> getMessages() {
        //log.debug("array={}", Arrays.toString(messages));
        List<TextMessage> list = new ArrayList<>(MAX_COUNT);
        int i;
        for (i = last; i >= 0; i--) {
            if (messages[i] != null) {
                list.add(messages[i]);
            }
        }
        for (i = MAX_COUNT - 1; i > last; i--) {
            if (messages[i] != null) {
                list.add(messages[i]);
            }
        }
        return list;
    }

    public boolean isShowTopic() {
        return showTopic;
    }

    public void setShowTopic(boolean showTopic) {
        this.showTopic = showTopic;
    }
}
