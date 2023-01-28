/**
 * 
 */
package com.loeaf.common.misc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Enumeration;

/**
 * @author gravity
 * @since 2020. 8. 17.
 *
 */
public class SessionUtils {
    public static final String PREFIX = "";
    /**
     * 세션에 값 저장
     * @date : 2018. 2. 9.
     * @param request
     * @param key
     * @param val
     */
    public static void set(HttpServletRequest request, String key, Object val) {
        if(null == request || null == request.getSession()) {
            return;
        }

        if(Utils.isEmpty(key)) {
            return;
        }

        request.getSession().setAttribute(PREFIX+key, val);

        //
        SessionUtils.debug(request);
    }



    /**
     * 세션에 저장된 값을 Object 타입으로 리턴
     * @date : 2018. 2. 9.
     * @param request
     * @param key
     * @return
     */
    public static Object get(HttpServletRequest request, String key) {
        if(null == request || null == request.getSession()) {
            return null;
        }

        if(Utils.isEmpty(PREFIX+key)) {
            return null;
        }

        return (Object)request.getSession().getAttribute(PREFIX+key);
    }



    /**
     * @title
     * @date : 2018. 2. 13.
     * @param request
     */
    public static void debug(HttpServletRequest request) {
        if(null == request || null == request.getSession()) {
//			log.debug("request or session is null");
        }

        Enumeration<String> en = request.getSession().getAttributeNames();
        String k;
        while(en.hasMoreElements()) {
            k = en.nextElement();

//			log.debug("{}\t{}", k, request.getSession().getAttribute(k));
        }
    }



    /**
     * 항목 제거
     * @param request
     * @param key
     */
    public static void remove(HttpServletRequest request, String key) {
        HttpSession ss = request.getSession();
        ss.removeAttribute(PREFIX + key);
    }

    /**
     * 세션에 저장된 값을 string 타입으로 리턴
     * @date : 2018. 2. 9.
     * @param request
     * @param key
     * @return {null|String}
     */
    public static String getAsString(HttpServletRequest request, String key) {
        if(null == request || null == request.getSession()) {
            return null;
        }

        if(Utils.isEmpty(PREFIX+key)) {
            return null;
        }

        return (String)request.getSession().getAttribute(PREFIX+key);
    }
}
