package com.arpit.statemachine.main;

import com.arpit.statemachine.events.Events;
import com.arpit.statemachine.interceptors.StateInterceptor;
import com.arpit.statemachine.states.States;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.statemachine.StateMachine;

@SpringBootApplication
@ComponentScan({"com.arpit.statemachine"})
public class StateMachineApplication implements CommandLineRunner {

    @Autowired
    private StateMachine<States, Events> stateMachine;

    @Override
    public void run(String... args) throws Exception {
        stateMachine.sendEvent(Events.ORDERED);

       /* System.out.println("############################################1111111111111111111111111");
        stateMachine.sendEvent(Events.ORDERED);
        System.out.println("############################################");
        stateMachine.sendEvent(Events.SHIPPED);*/
       /*

        stateMachine.sendEvent(Events.E1);*/
    }

    public static void main(String[] args) {
        SpringApplication.run(StateMachineApplication.class, args);
    }
}
