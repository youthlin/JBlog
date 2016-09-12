package com.youthlin.jblog.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

/**
 * Created by lin on 2016-09-12-012.
 * Http Util
 */
public class HTTPUtil {
    private static final Logger log = LoggerFactory.getLogger(HTTPUtil.class);

    public static HttpSession getSession() {
        //getCurrentInstance返回null：只有进入了Faces Servlet(web.xml)才能获取到，因此在Filter里不能调这里的方法
        //http://blog.csdn.net/duankaige/article/details/5977227
        return (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
    }
}
