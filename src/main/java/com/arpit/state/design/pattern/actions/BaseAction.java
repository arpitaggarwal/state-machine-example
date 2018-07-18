package com.arpit.state.design.pattern.actions;

import com.arpit.state.design.pattern.config.StateMachine;
import com.arpit.state.design.pattern.config.StateMachineActionResolver;
import com.arpit.state.design.pattern.context.StateContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

public abstract class BaseAction {

    protected StateMachine stateMachine;
    protected StateMachineActionResolver stateMachineActionResolver;

    public abstract void doAction(StateContext context);
}
