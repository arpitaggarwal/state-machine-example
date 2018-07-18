package com.arpit.state.design.pattern.config;

import com.arpit.state.design.pattern.actions.*;
import com.arpit.state.design.pattern.states.State;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class StateMachineActionResolver {

    @Autowired
    private StateMachineActionResolver stateMachineActionResolver;

    @Autowired
    private StateMachine stateMachine;

    public BaseAction getAction(State state) {
        if (state == null) {
            return null;
        }
        if (State.ORDERED.name().equals(state.name())) {
            return new OrderedAction(stateMachine, stateMachineActionResolver);
        } else if (State.SHIPPED.name().equals(state.name())) {
            return new ShippedAction(stateMachine, stateMachineActionResolver);
        } else if (State.DELIVERED.name().equals(state.name())) {
            return new DeliveredAction(stateMachine, stateMachineActionResolver);
        } else if (State.PRINTED.name().equals(state.name())) {
            return new PrintAction(stateMachine, stateMachineActionResolver);
        }
        return null;
    }

}
