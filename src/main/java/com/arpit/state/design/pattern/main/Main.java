package com.arpit.state.design.pattern.main;

import java.util.ArrayList;
import java.util.List;

import com.arpit.state.design.pattern.actions.BaseAction;
import com.arpit.state.design.pattern.config.StateMachine;
import com.arpit.state.design.pattern.config.StateMachineActionResolver;
import com.arpit.state.design.pattern.context.StateContext;
import com.arpit.state.design.pattern.events.Event;
import com.arpit.state.design.pattern.states.State;
import com.arpit.state.design.pattern.transition.Transition;
import com.arpit.statemachine.events.Events;
import com.arpit.statemachine.main.StateMachineApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy
@ComponentScan({"com.arpit.state.design.pattern"})
public class Main implements CommandLineRunner {

    @Autowired
    private StateMachineActionResolver stateMachineActionResolver;

    @Autowired
    private StateMachine stateMachine;

    @Override
    public void run(String... args) throws Exception {

        for(int i =0; i <5; i++){
            State shippedState = stateMachine.getNextState(State.INITIALIZED, Event.ORDER);

            stateMachineActionResolver.getAction(shippedState).doAction(new StateContext());
            System.out.println("-----------------------------------------" + i);
        }


       /* State deliveredState = stateMachine.getNextState(State.SHIPPED, Event.DELIVER);
        stateMachineActionResolver.getAction(deliveredState).doAction(new StateContext());*/
    }

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }
}