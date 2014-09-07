package com.mx.util;

import java.util.Properties;

public class SqlUtil {

	private Properties properties;

	public Properties getProperties() {
		return properties;
	}

	public void setProperties(Properties properties) {
		this.properties = properties;
	}
	
	public String getSentence(String key, String where){
		String sql = properties.getProperty(key);
		if (where != null) {
			String[] w = where.split(",");
			for (int i = 0 ; i < w.length; i++) {
				sql = sql.replaceFirst("[?]", w[i]);
			}
		}
		return sql;
	}
	
}
