package com.arpit.statemachine.actions;

import com.arpit.statemachine.events.Events;
import com.arpit.statemachine.states.States;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.action.Action;

public class ActionS2 implements Action<States, Events> {

    @Override
    public void execute(StateContext<States, Events> context) {
        System.out.println("hello ActionS2");
    }
}