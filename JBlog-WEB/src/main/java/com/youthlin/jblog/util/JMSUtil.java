package com.youthlin.jblog.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
 * Created by lin on 2016-09-12-012.
 * JMS 相关工具类
 * <p>
 * WildFly 配置ActiveMQ 见下方链接
 *
 * @link http://middlewaremagic.com/jboss/?p=2739
 */
public class JMSUtil {
    private final static String JMS_CONNECTION_FACTORY_JNDI = "jms/RemoteConnectionFactory";
    private final static String JMS_Topic_JNDI = "jms/topic/MyTopic";
    private final static String JMS_USERNAME = "jmsuser";//  The role for this user is "guest" in ApplicationRealm
    private final static String JMS_PASSWORD = "jmsuser@123";
    private final static String WILDFLY_REMOTING_URL = "http-remoting://localhost:8080";
    private final static Logger log = LoggerFactory.getLogger(JMSUtil.class);

    /*获取上下文*/
    private static InitialContext getInitialContext() throws NamingException {
        InitialContext context = null;
        try {
            Properties props = new Properties();
            props.put(Context.INITIAL_CONTEXT_FACTORY, "org.jboss.naming.remote.client.InitialContextFactory");
            props.put(Context.PROVIDER_URL, WILDFLY_REMOTING_URL);   // NOTICE: "http-remoting" and port "8080"
            props.put(Context.SECURITY_PRINCIPAL, JMS_USERNAME);
            props.put(Context.SECURITY_CREDENTIALS, JMS_PASSWORD);
            //props.put("jboss.naming.client.ejb.context", true);
            context = new InitialContext(props);
            log.debug("Got initial Context: " + context);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return context;
    }

    /*发送消息,返回发送成功发送的条数,代码来源：课本*/
    public static int sendTextMessage(String... message) {
        int count = 0;
        InitialContext context = null;
        Connection connection = null;
        try {
            context = getInitialContext();
            ConnectionFactory factory = (ConnectionFactory) context.lookup(JMS_CONNECTION_FACTORY_JNDI);
            Destination destination = (Destination) context.lookup(JMS_Topic_JNDI);
            connection = factory.createConnection(JMS_USERNAME, JMS_PASSWORD);
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            MessageProducer producer = session.createProducer(destination);
            connection.start();
            TextMessage textMessage;
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.SIMPLIFIED_CHINESE);
            for (String msg : message) {
                textMessage = session.createTextMessage(msg);
                textMessage.setStringProperty("time", sdf.format(new Date()));
                producer.send(textMessage);
                log.trace("text msg has been send:{}", msg);
                count++;
            }
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
}
