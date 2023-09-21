package com.merrycorner.utility;
import javax.servlet.http.HttpServletRequest;

public class common {
    public static String getSiteURL(HttpServletRequest request) {
        String siteURL = request.getRequestURL().toString();
        return siteURL.replace(request.getServletPath(), "");
    }
    public String RandomString(int lenth){
        String str="abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        StringBuffer sb=new StringBuffer();
        for(int i=0;i<lenth;i++){
            int number= (int)(Math.random()*62);
            sb.append(str.charAt(number));
        }
        return sb.toString();
    }
}
