package com.arpit.state.design.pattern.context;

import java.util.HashMap;
import java.util.Map;

import com.arpit.state.design.pattern.actions.Action;
import com.arpit.state.design.pattern.model.BaseModel;

public class StateContext {

	private Action action;
	private Map<String, BaseModel> data;

	public Map<String, BaseModel> getData() {
		if (data == null) {
			data = new HashMap<>();
		}
		return data;
	}

	public void setData(Map<String, BaseModel> data) {
		if (data == null) {
			data = new HashMap<>();
		}
		this.data = data;
	}

	public Action getAction() {
		return action;
	}

	public void setAction(Action action) {
		this.action = action;
	}
}
