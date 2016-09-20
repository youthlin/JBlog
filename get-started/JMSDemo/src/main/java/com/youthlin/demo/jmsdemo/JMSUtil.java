package com.youthlin.demo.jmsdemo;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.Session;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.Properties;

/**
 * Created by lin on 2016-09-17-017.
 * 相同的代码提取出来放在工具类中
 */
public class JMSUtil {
    final static String JMS_CONNECTION_FACTORY_JNDI = "jms/RemoteConnectionFactory";
    final static String JMS_TOPIC_JNDI = "jms/topic/MyTopic";//新建的消息队列/Topic JNDI 发布名称
    final static String JMS_USERNAME = "jmsuser";//添加的用户名，角色必须是"guest"/ApplicationRealm
    final static String JMS_PASSWORD = "jmsuser@123";//添加的用户的密码
    private final static String WILDFLY_REMOTING_URL = "http-remoting://localhost:8080";//注意前缀格式和端口，不是4447哦

    static InitialContext getInitialContext() throws NamingException {
        InitialContext context = null;
        try {
            Properties props = new Properties();
            props.put(Context.INITIAL_CONTEXT_FACTORY, "org.jboss.naming.remote.client.InitialContextFactory");
            props.put(Context.PROVIDER_URL, WILDFLY_REMOTING_URL);// NOTICE: "http-remoting" and port "8080"
            props.put(Context.SECURITY_PRINCIPAL, JMS_USERNAME);
            props.put(Context.SECURITY_CREDENTIALS, JMS_PASSWORD);
            //props.put("jboss.naming.client.ejb.context", true);
            context = new InitialContext(props);
            System.out.println("\n\tGot initial Context: " + context);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return context;
    }
}
