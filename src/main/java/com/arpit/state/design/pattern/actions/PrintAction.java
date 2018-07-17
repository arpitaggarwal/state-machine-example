package com.arpit.state.design.pattern.actions;

import java.util.Map;

import com.arpit.state.design.pattern.context.StateContext;
import com.arpit.state.design.pattern.model.BaseModel;
import com.arpit.state.design.pattern.model.Order;

public class PrintAction implements Action {

	public void doAction(StateContext context) {
		System.out.println("Player is in stop state");
		context.setAction(this);
		Map<String, BaseModel> data = context.getData();
		for (Map.Entry<String, BaseModel> pair : data.entrySet()) {
			System.out.println(pair.getKey());
			Order baseModel = (Order) pair.getValue();
			System.out.println(baseModel.getAmount());
		}
	}

}
