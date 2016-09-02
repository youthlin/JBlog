package com.youthlin.jblog.util;

import com.youthlin.jblog.controller.UserBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.Hashtable;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by lin on 2016-09-02-002.
 * 工具类
 */
public class EJBUtil {
    private static final Logger log = LoggerFactory.getLogger(UserBean.class);
    private static ConcurrentHashMap<Class, Object> map = new ConcurrentHashMap<>();

    /**
     * 获取远程对象,第一次获取后会缓存起来，之后获取的将是缓存的对象
     *
     * @param clazz 远程对象的类型
     * @return 获取的远程对象
     */
    @SuppressWarnings("unchecked")
    public static <T> T getBean(Class<T> clazz) {
        return getBean(clazz, false);
    }

    /**
     * 获取远程对象
     *
     * @param clazz 远程对象的类型
     * @param force true表示强制每次都从远程获取而不使用缓存
     * @return 获取的远程对象
     */
    @SuppressWarnings("unchecked")
    public static <T> T getBean(Class<T> clazz, boolean force) {
        if (!force && map.containsKey(clazz)) {
            log.trace("直接返回已缓存的对象：{}", clazz);
            return (T) map.get(clazz);
        }
        boolean hasException = false;
        Object result = null;
        Hashtable<String, String> jndiProperties = new Hashtable<>();
        jndiProperties.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
        try {
            Context context = new InitialContext(jndiProperties);
            //java:global/JBlog_EJB/UserDaoImpl!com.youthlin.jblog.dao.UserDao
            final String moduleName = "JBlog_EJB";
            final String fullName = "ejb:/" + moduleName + "/"
                    + clazz.getSimpleName() + "Impl" + "!" + clazz.getName();
            log.debug("EJB 全名 =" + fullName);
            result = context.lookup(fullName);
            log.debug("result={},class={}", result, result.getClass());
            return (T) result;
        } catch (NamingException e) {
            e.printStackTrace();
            hasException = true;
        } catch (Exception e) {
            hasException = true;
        } finally {
            log.trace(" 远程对象获取完毕 ");
            if (!hasException && result != null) {
                map.put(clazz, result);
            }
        }
        log.warn(" 获取远程对象失败 ");
        return null;
    }
}
