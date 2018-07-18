package com.arpit.statemachine.interceptors;

import com.arpit.statemachine.events.Events;
import com.arpit.statemachine.states.States;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.state.State;
import org.springframework.statemachine.support.StateMachineInterceptorAdapter;
import org.springframework.statemachine.transition.Transition;

public class StateInterceptor extends StateMachineInterceptorAdapter<States, Events> {

    @Autowired
    private StateMachine<States, Events> stateMachine;

    @Override
    public Message<Events> preEvent(Message<Events> message, StateMachine<States, Events> stateMachine) {
        return message;
    }

    @Override
    public void preStateChange(State<States, Events> state, Message<Events> message, Transition<States, Events> transition,
                               StateMachine<States, Events> stateMachine) {
    }

    @Override
    public void postStateChange(State<States, Events> state, Message<Events> message, Transition<States, Events> transition,
                                StateMachine<States, Events> stateMachine) {
    }

    @Override
    public StateContext<States, Events> preTransition(StateContext<States, Events> stateContext) {
        return stateContext;

    }
    @Override
    public StateContext<States, Events> postTransition(StateContext<States, Events> stateContext) {
        State currentState = stateContext.getStateMachine().getState();
        System.out.println("Current State is :: "+ currentState.getId());
        return stateContext;
    }

    @Override
    public Exception stateMachineError(StateMachine<States, Events> stateMachine, Exception exception) {
        State currentState = stateMachine.getState();
        System.out.println("Failed State is :: "+ currentState.getId());
        stateMachine.stop();
        return exception;
    }

}
