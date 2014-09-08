package com.mx.util;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.databind.ObjectMapper;

@Repository
public class Factory {

	@Autowired
	private Configure configure;
	@Autowired
	private ObjectMapper objectMapper;
	@Autowired
	private Parameter parameter;
	
	public String getMD5(String content){
		MessageDigest messageDigest = null;  
		  
        try {  
            messageDigest = MessageDigest.getInstance("MD5");  
  
            messageDigest.reset();  
  
            messageDigest.update(content.getBytes("UTF-8"));  
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {  
            e.printStackTrace();  
        }  
  
        byte[] byteArray = messageDigest.digest();  
  
        StringBuffer md5StrBuff = new StringBuffer();  
  
        for (int i = 0; i < byteArray.length; i++) {              
            if (Integer.toHexString(0xFF & byteArray[i]).length() == 1)  
                md5StrBuff.append("0").append(Integer.toHexString(0xFF & byteArray[i]));  
            else  
                md5StrBuff.append(Integer.toHexString(0xFF & byteArray[i]));  
        }  
  
        return md5StrBuff.toString();
	}
	
	public String getUrlBySort(HttpServletRequest request){
//		Parameter p = new Parameter(request);
		parameter.setRequest(request);
		return parameter.sort();
	}
	
	public Object getEntity(HttpServletRequest request) throws Exception{
		String context = "";
		String entity = request.getParameter("entity");
		Class<?> object = Class.forName(configure.getEntityPath() + "." + entity);
		Field[] f = object.getDeclaredFields();
		
		for (Field field : f) {
			String name = field.getName();
			String value = request.getParameter(name);
			if (value != null) {
				context += "\"" + name + "\"" + ":" + "\"" + value + "\",";
			}
		}
		context = context.substring(0, context.length()-1);
		String json = "{" + context + "}";
		return objectMapper.readValue(json, object);
		
	}
}
