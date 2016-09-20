package com.youthlin.demo.jmsdemo;

import javax.jms.*;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * Created by lin on 2016-09-17-017.
 * 消息同步接收
 * <p>
 * 一般步骤：
 * 0. 确保 JBoss 服务器开启，因为我们需要分局服务器发布的 JNDI 名称获取相关对象
 * 1. 获得一个 JBoss 上下文引用
 * 2. 创建连接工厂
 * 3. 使用连接工厂创建连接
 * 4. 使用连接创建会话
 * 5. 通过 JNDI 查找 Queue 或 Topic 作为目的地
 * 6. 使用会话和目的地创建消息消费者
 * 7. 接收消息
 * 8. 处理消息
 */
public class JMSSyncReceiver {

    public static void main(String[] args) throws NamingException, JMSException, InterruptedException {
        InitialContext context = JMSUtil.getInitialContext();
        ConnectionFactory factory = (ConnectionFactory) context.lookup(JMSUtil.JMS_CONNECTION_FACTORY_JNDI);
        Connection connection = factory.createConnection(JMSUtil.JMS_USERNAME, JMSUtil.JMS_PASSWORD);
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Destination destination = (Destination) context.lookup(JMSUtil.JMS_TOPIC_JNDI);

        //6. 创建消费者
        MessageConsumer consumer = session.createConsumer(destination);
        //连接开始
        connection.start();

        TextMessage msg = null;
        //CountDownLatch latch = new CountDownLatch(1);//同步计数器
        while (msg == null) {
            System.out.println("等待接收消息");
            //7. 等待接收消息
            //   参数时超时毫秒，为0或不带参数则表示一直阻塞在这里。超时无消息将返回null
            msg = (TextMessage) consumer.receive(5000);
            //latch.await(1, TimeUnit.SECONDS);//等待（相当于休眠）特定时间
            if (msg != null) {
                //8. 处理消息
                System.out.println("接收到的消息内容：" + msg.getText() + ",Time:" + msg.getStringProperty("time"));
                if (!msg.getText().equals("quit")) {
                    msg = null;//继续等待
                }
            }
        }
        connection.close();
        context.close();
    }


}
