package com.youthlin.demo.jmsdemo;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Properties;

/**
 * Created by lin on 2016-09-17-017.
 * 发送方。无论是消息队列的Queue还是Pub/Sub模式的 Topic,
 * 编程方式均一致，Java 中不需要管细节，至于究竟是队列还是主题，那是服务器的事，
 * 因为我们客户端只根据 JNDI 名称编码，不对消息的处理方式负责。
 * <p>
 * 一般步骤：
 * 1. 获得一个 JBoss 上下文引用
 * 2. 创建连接工厂
 * 3. 使用连接工厂创建连接
 * 4. 使用连接创建会话
 * 5. 通过 JNDI 查找 Queue 或 Topic 作为目的地
 * 6. 使用会话和目的地创建消息生产者
 * 7. 使用会话创建消息
 * 8. 发送消息
 */
public class JMSSender {

    /*发送消息,返回发送成功发送的条数,代码来源：课本*/
    private static int sendTextMessage(String... message) {
        int count = 0;
        InitialContext context = null;
        Connection connection = null;
        try {
            context = JMSUtil.getInitialContext();
            ConnectionFactory factory = (ConnectionFactory) context.lookup(JMSUtil.JMS_CONNECTION_FACTORY_JNDI);
            connection = factory.createConnection(JMSUtil.JMS_USERNAME, JMSUtil.JMS_PASSWORD);
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            Destination destination = (Destination) context.lookup(JMSUtil.JMS_TOPIC_JNDI);

            //6. 使用会话和目的地创建消息生产者
            MessageProducer producer = session.createProducer(destination);
            //连接开始
            connection.start();

            TextMessage textMessage;
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.SIMPLIFIED_CHINESE);
            for (String msg : message) {
                //7. 使用会话创建消息
                textMessage = session.createTextMessage(msg);
                textMessage.setStringProperty("time", sdf.format(new Date()));
                //8. 发送消息
                producer.send(textMessage);
                count++;
            }
            System.out.println("已发送" + count + "条消息");
        } catch (NamingException | JMSException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }
            if (context != null) {
                try {
                    context.close();
                } catch (NamingException e) {
                    e.printStackTrace();
                }
            }

        }
        return count;
    }

    public static void main(String[] args) {
        String[] msgs = new String[5];
        for (int i = 0; i < 5; i++) {
            msgs[i] = "TestMessage" + i;
        }
        JMSSender.sendTextMessage(msgs);
        JMSSender.sendTextMessage("quit");
    }
}
