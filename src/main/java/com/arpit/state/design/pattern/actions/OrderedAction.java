package com.arpit.state.design.pattern.actions;

import java.util.Map;

import com.arpit.state.design.pattern.context.StateContext;
import com.arpit.state.design.pattern.model.BaseModel;
import com.arpit.state.design.pattern.model.Order;

public class OrderedAction implements Action {

	public void doAction(StateContext context) {
		System.out.println("Player is in start state");
		Map<String, BaseModel> map = context.getData();
		map.put("order", new Order(1, 100));
		context.setAction(this);
	}

	public String toString() {
		return "Start State";
	}
}
