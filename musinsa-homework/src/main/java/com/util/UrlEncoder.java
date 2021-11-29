package com.util;

import org.springframework.stereotype.Component;

/**
 * URL Encoding
 * @author hwkim
 *
 */
@Component
public class UrlEncoder {
    
	public static String encoding(int val)
    {
		String BASE62_STR = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
		
        StringBuffer sb = new StringBuffer();
        
        while(val > 0)
        {
            sb.append(BASE62_STR.charAt(val % 62));
            
            val /= 62;
        }
        
        return "http://localhost/" + sb.toString();
    }
}
