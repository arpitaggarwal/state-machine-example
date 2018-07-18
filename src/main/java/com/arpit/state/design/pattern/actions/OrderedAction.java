package com.arpit.state.design.pattern.actions;

import java.util.Map;

import com.arpit.state.design.pattern.aspects.LogExecutionTime;
import com.arpit.state.design.pattern.config.StateMachine;
import com.arpit.state.design.pattern.config.StateMachineActionResolver;
import com.arpit.state.design.pattern.context.StateContext;
import com.arpit.state.design.pattern.events.Event;
import com.arpit.state.design.pattern.model.BaseModel;
import com.arpit.state.design.pattern.model.Order;
import com.arpit.state.design.pattern.states.State;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

public class OrderedAction extends BaseAction implements Action {

    public OrderedAction(final StateMachine stateMachine, StateMachineActionResolver stateMachineActionResolver) {
        this.stateMachine = stateMachine;
        this.stateMachineActionResolver = stateMachineActionResolver;
    }

    @Override
    @LogExecutionTime
    public void doAction(StateContext context) {
        System.out.println("Ordered Action called..");
        Map<String, BaseModel> map = context.getData();
        map.put("order", new Order(1, 100));
        context.setAction(this);

        State shippedState = stateMachine.getNextState(State.ORDERED, Event.SHIP);
        System.out.println("Next State called which is -> " + shippedState.name());
        stateMachineActionResolver.getAction(shippedState).doAction(context);
    }
}
