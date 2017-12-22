package com.iemes.util;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;

import com.iemes.entity.UserFormMap;

/**
 * 2017-08-11 11:06:36
 * @author huahao
 *
 */
public class ShiroSecurityHelper {
	
    /** 
     * 获得当前用户名
     *  
     * @return 
     */  
    public static String getCurrentUsername() {  
        Subject subject = SecurityUtils.getSubject();  
        PrincipalCollection collection = subject.getPrincipals();  
        if (null != collection && !collection.isEmpty()) {  
        	return (String) collection.iterator().next();
        }  
        return null;  
    }
	
    /**
     * 获得当前sessionId
     * @return
     */
    public static String getSessionId() {  
        Session session = getSession();
        if (null == session) {  
            return null;  
        }  
        return getSession().getId().toString();  
    }
    
    /**
     * 获得当前session
     * @return
     */
    public static Session getSession() {  
        return SecurityUtils.getSubject().getSession();  
    }
    
    /**
     * 获取当前站点
     * @return
     */
    public static String getSite() {  
        Session session = SecurityUtils.getSubject().getSession();
        if (session!=null) {
        	return (String) session.getAttribute("site");
        }
        return null;
    }
    
}
