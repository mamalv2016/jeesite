package com.renjie120.common.utils;

import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.servlet.http.HttpServletRequest;

public class IpUtils {
	
	public static String getIpAddr(HttpServletRequest request) {
	    
		try{
			String ip = request.getHeader("x-forwarded-for");  
		    if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
		        ip = request.getHeader("Proxy-Client-IP");  
		    }  
		    if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
		        ip = request.getHeader("WL-Proxy-Client-IP");  
		    }  
		    if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
		        ip = request.getRemoteAddr();  
		    }
		    
		    if(ip.equals("127.0.0.1") || ip.equals("0:0:0:0:0:0:0:1")){
				//根据网卡取本机配置的IP	
				InetAddress inet=null;
				try {
					inet = InetAddress.getLocalHost();
				} catch (UnknownHostException e) {
					e.printStackTrace();
				}
				ip= inet.getHostAddress();
			}
		    
		    for(String ips:ip.split(",")){
		    	if(!"unknown".equalsIgnoreCase(ips)){
		    		return ips;
		    	}
		    };
		    
		    
		    
		}catch(Exception ex){
		}
		return null;
	}  

}
