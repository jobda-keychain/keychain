package com.jobda.keychain.utils;

import javax.servlet.http.HttpServletRequest;

public class LogUtil {

    public static String getClientIp(HttpServletRequest request) {
        String ip = request.getHeader("X-Forwarded-For");

        boolean b = ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip);
        if(b) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if(b) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if(b) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if(b) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if(b) {
            ip = request.getRemoteAddr();
        }

        return ip;
    }

}
