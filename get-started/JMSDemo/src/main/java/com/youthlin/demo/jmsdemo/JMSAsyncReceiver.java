package com.youthlin.demo.jmsdemo;

import javax.jms.*;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * Created by lin on 2016-09-20-020.
 * 消息异步接收
 */
public class JMSAsyncReceiver {
    static boolean quit = false;

    public static void main(String[] args) throws NamingException, JMSException, InterruptedException {
        InitialContext context = JMSUtil.getInitialContext();
        ConnectionFactory factory = (ConnectionFactory) context.lookup(JMSUtil.JMS_CONNECTION_FACTORY_JNDI);
        Connection connection = factory.createConnection(JMSUtil.JMS_USERNAME, JMSUtil.JMS_PASSWORD);
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Destination destination = (Destination) context.lookup(JMSUtil.JMS_TOPIC_JNDI);

        MessageConsumer consumer = session.createConsumer(destination);
        consumer.setMessageListener(new MessageListener() {//设置监听器
            @Override
            public void onMessage(Message message) {
                TextMessage msg = (TextMessage) message;
                try {
                    System.out.println("接收到的消息内容：" + msg.getText() + ",Time:" + msg.getStringProperty("time"));
                    if (msg.getText().equals("quit")) {
                        quit = true;
                    }
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }
        });

        connection.start();
        CountDownLatch latch = new CountDownLatch(1);
        while (!quit) {
            System.out.println("等待接收消息");
            latch.await(5, TimeUnit.SECONDS);
        }
        connection.close();
        context.close();
    }
}
