package com.mx.util;

import java.util.List;

public class Configure {
	//包路径
	private String entityPath;
	//url提交的action名
	private List<String> actionList;
	
	public String getEntityPath() {
		return entityPath;
	}
	public void setEntityPath(String entityPath) {
		this.entityPath = entityPath;
	}
	public List<String> getActionList() {
		return actionList;
	}
	public void setActionList(List<String> actionList) {
		this.actionList = actionList;
	}
	
	
}
