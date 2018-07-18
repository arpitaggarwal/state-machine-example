package com.arpit.state.design.pattern.actions;

import java.util.Map;

import com.arpit.state.design.pattern.config.StateMachine;
import com.arpit.state.design.pattern.config.StateMachineActionResolver;
import com.arpit.state.design.pattern.context.StateContext;
import com.arpit.state.design.pattern.events.Event;
import com.arpit.state.design.pattern.model.BaseModel;
import com.arpit.state.design.pattern.model.Order;
import com.arpit.state.design.pattern.states.State;
import com.fasterxml.jackson.databind.ser.Serializers;

public class DeliveredAction extends BaseAction implements Action {

    public DeliveredAction(final StateMachine stateMachine, StateMachineActionResolver stateMachineActionResolver) {
        this.stateMachine = stateMachine;
        this.stateMachineActionResolver = stateMachineActionResolver;
    }

    @Override
    public void doAction(StateContext context) {
        System.out.println("Delivered Action called..");
        context.setAction(this);
        Map<String, BaseModel> data = context.getData();
        for (Map.Entry<String, BaseModel> pair : data.entrySet()) {
            System.out.println(pair.getKey());
            Order baseModel = (Order) pair.getValue();
            System.out.println(baseModel.getAmount());
        }

        State printState = stateMachine.getNextState(State.DELIVERED, Event.PRINT);
        System.out.println("Next State called which is -> " + printState.name());
        stateMachineActionResolver.getAction(printState).doAction(context);
    }
}
